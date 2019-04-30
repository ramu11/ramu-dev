package com.redhat.kafka.kafka_clients.consumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumerAssighWithSpecificOffset {

	public static void main(String[] args) {

		String topicName1 = "ordertopic";
		

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("enable.auto.commit", "false");

		KafkaConsumer<String, String> consumer = null;
		TopicPartition partition0 = new TopicPartition(topicName1, 0);
		TopicPartition partition1 = new TopicPartition(topicName1, 1);
		TopicPartition partition2 = new TopicPartition(topicName1, 2);
		int recordCount;
		
		List<TopicPartition> topics = Arrays.asList(partition0, partition1, partition2);
		consumer = new KafkaConsumer<>(props);
        consumer.assign(topics);
		System.out.println("Current position p0=" + consumer.position(partition0) + " p1=" + consumer.position(partition1) + " p2="
				+ consumer.position(partition2));

		consumer.seek(partition0, getOffsetFromDB(partition0));
		consumer.seek(partition1, getOffsetFromDB(partition1));
		consumer.seek(partition2, getOffsetFromDB(partition2));
		System.out.println("New positions po=" + consumer.position(partition0) + " p1=" + consumer.position(partition1) + " p2="
				+ consumer.position(partition2));

		System.out.println("Start Fetching Now");
		try {
			do {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				System.out.println("Record polled " + records.count());
				recordCount = records.count();
				for (ConsumerRecord<String, String> record : records) {
					saveAndCommit(consumer, record);
				}
			} while (recordCount > 0);
		} catch (Exception ex) {
			System.out.println("Exception in main.");
		} finally {
			consumer.close();
		}
	}

	private static long getOffsetFromDB(TopicPartition p) {
		long offset = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka_test", "root", "admin");

			String sql = "select offset from order_offsets where topic_name='" + p.topic() + "' and partitionNo="
					+ p.partition();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				offset = rs.getInt("offset");
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception in getOffsetFromDB =" + e.getMessage());
			
		}
		return offset;
	}

	private static void saveAndCommit(KafkaConsumer<String, String> c, ConsumerRecord<String, String> rec) {
		System.out.println("Topic=" + rec.topic() + " Partition=" + rec.partition() + " Offset=" + rec.offset() + " Key="
				+ rec.key() + " Value=" + rec.value());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kafka_test", "root", "admin");
			con.setAutoCommit(false);

			String insertSQL = "insert into order_data values(?,?)";
			PreparedStatement psInsert = con.prepareStatement(insertSQL);
			psInsert.setString(1, rec.key());
			psInsert.setString(2, rec.value());

			String updateSQL = "update order_offsets set offset=? where topic_name=? and partitionNo=?";
			PreparedStatement psUpdate = con.prepareStatement(updateSQL);
			psUpdate.setLong(1, rec.offset() + 1);
			psUpdate.setString(2, rec.topic());
			psUpdate.setInt(3, rec.partition());

			psInsert.executeUpdate();
			psUpdate.executeUpdate();
			con.commit();
			con.close();
		} catch (Exception e) {
			
			System.out.println("Exception in saveAndCommit =" + e.getMessage());
		}

	}

}
