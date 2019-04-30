package com.redhat.kafka.kafka_clients.producer;

import java.util.*;
import org.apache.kafka.clients.producer.*;

import com.redhat.kafka.kafka_clients.constants.IKafkaConstants;

public class SynchronousProducer {

	private static long startTime = System.currentTimeMillis();
	public static void main(String[] args) throws Exception {
      
		String topicName = "test-topic";
	   //	String topicName = "simple-topic";
		String key = "Key1";
		String value = "test-topic message before rebalance";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("acks", "all");
		//props.put("batch.size", 1024);
		//props.put("linger.ms", 1);
		

		Producer<String, String> producer = new KafkaProducer<>(props);

		ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
        try {
		  for (int index = 1; index <= 1; index++) {

				RecordMetadata metadata = producer.send(record).get();
				System.out.println(
						"Message is sent to Partition no " + metadata.partition() + " and offset " + metadata.offset());
				System.out.println("SynchronousProducer Completed with success.");
				// At the end
		        long endTime = System.currentTimeMillis();
		        System.out.println("It took " + (endTime - startTime) + " milliseconds");
			} 
         }catch (Exception exception) {
				exception.printStackTrace();
				System.out.println("SynchronousProducer failed with an exception");
			} finally {
				producer.flush();
				producer.close();
				
			}
		
	}
}
