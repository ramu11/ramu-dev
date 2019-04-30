package com.redhat.kafka.kafka_clients.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleOrderProducer {

	public static void main(String[] args) {

		String topicName = "ordertopic";
		String key = "Key1";
		String value = "1,7-order";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("acks", "all");

		Producer<String, String> producer = new KafkaProducer<>(props);

		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
		try {

			RecordMetadata metadata = producer.send(record).get();
			System.out.println(
					"Message is sent to Partition no " + metadata.partition() + " and offset " + metadata.offset());
			System.out.println("SimpleOrderProducer Completed with success.");

		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("SimpleOrderProducer failed with an exception");
		} finally {
			producer.flush();
			producer.close();

		}

	}

}
