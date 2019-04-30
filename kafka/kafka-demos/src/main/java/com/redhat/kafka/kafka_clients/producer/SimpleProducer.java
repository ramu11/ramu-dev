package com.redhat.kafka.kafka_clients.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	public static void main(String[] args) throws Exception {

		//String topicName = "test-topic";
		String topicName = "simple-topic";
		String key = "batch-key-3";
		String value = "batchproducer";
		int partition = 0;

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("batch.size", 1024);
		props.put("linger.ms", 10);
		props.put("max.in.flight.requests.per.connection", 2);

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		ProducerRecord<String, String> record;
		for (int i = 0; i <= 100; i++) {
			record = new ProducerRecord<String, String>(topicName, key, Integer.toString(i));
			producer.send(record);
			System.out.println("  : " +i);
		}
		producer.close();
		
	}

}
