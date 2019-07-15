package org.apache.camel.example.kafka.avro;

import java.util.Properties;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.example.kafkatutorials.Employee;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class KafkaAvroJavaProducerDemo {
	public static void main(String[] args) {
        Properties properties = new Properties();
        // normal producer
        properties.setProperty("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "3");
        // avro part
        properties.setProperty("key.serializer", StringSerializer.class.getName());
       // properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("value.serializer", org.apache.camel.example.kafka.avro.CustomKafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        Producer<String, Employee> producer =  new KafkaProducer<String, Employee>(properties);

        String topic = "employees-avro";

        // copied from avro examples
        Employee emp = Employee.newBuilder()
				.setFirstName("kakarla")
				.setLastName("Ramu")
				.setBirthDate(new java.util.Date().getTime())
				.build();

        ProducerRecord<String, Employee> producerRecord = new ProducerRecord<String, Employee>(topic, emp);

        System.out.println(emp);
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("offset-value is: " +metadata.offset());
                    System.out.println("serializedValueSize: " +metadata.serializedValueSize());
                } else {
                    exception.printStackTrace();
                }
            }
        });

        producer.flush();
        producer.close();

    }
}
