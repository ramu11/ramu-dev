package com.redhat.kafka.kafka_clients.consumer;
import java.util.*;
import java.io.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
public class ManualConsumer2 {
	
	public static void main(String[] args) throws Exception{

        String topicName1 = "simple-topic";
        String topicName2 = "test-topic";
        String groupName = "TestTopicGroup";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");
       // props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RoundRobinAssignor");
       

        KafkaConsumer<String, String> consumer = null;

        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(topicName1,topicName2));

            while (true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    System.out.println("Record value is = "+ record.value() +"key " + record.key() + " to partition " + record.partition()
                            + " with offset " + record.offset() );
                }
                consumer.commitAsync();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        	try {
                consumer.commitSync(); 
            } finally {
                consumer.close();
            }
    }
	

	}
}