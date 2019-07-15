package org.apache.camel.example.kafka.avro;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.example.kafkatutorials.Employee;

import java.util.Collections;
import java.util.Properties;
public class KafkaAvroJavaConsumerDemo {

	public static void main(String[] args) {
		 Properties properties = new Properties();
	        // normal consumer
	        properties.setProperty("bootstrap.servers","localhost:9092,localhost:9093,localhost:9094");
	        properties.put("group.id", "customer-consumer-group-v1");
	        properties.put("auto.commit.enable", "false");
	        properties.put("auto.offset.reset", "earliest");

	        // avro part (deserializer)
	        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
	        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
	        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");
	        properties.setProperty("specific.avro.reader", "true");

	        KafkaConsumer<String, Employee> kafkaConsumer = new KafkaConsumer<String,Employee>(properties);
	        String topic = "employees-avro";
	        kafkaConsumer.subscribe(Collections.singleton(topic));

	        System.out.println("Waiting for data...");

	     //   while (true){
	            System.out.println("Polling");
	            ConsumerRecords<String, Employee> records = kafkaConsumer.poll(1000);
	            System.out.println("records size:" +records.count());

	            for (ConsumerRecord<String, Employee> record : records){
	            	Employee emp = record.value();
	                System.out.println("Emp first name:" +emp.getFirstName());
	            }

	            kafkaConsumer.commitSync();
	       // }
	    }

	}


