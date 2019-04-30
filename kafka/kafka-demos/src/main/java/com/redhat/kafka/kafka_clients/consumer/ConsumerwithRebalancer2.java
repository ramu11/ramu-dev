package com.redhat.kafka.kafka_clients.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerwithRebalancer2 {
	
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
        props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RoundRobinAssignor");
       

        KafkaConsumer<String, String> consumer = null;
        consumer = new KafkaConsumer<>(props);
        ConsumerRebalancerListener rebalanceListner = new ConsumerRebalancerListener(consumer);
        consumer.subscribe(Arrays.asList(topicName1,topicName2),rebalanceListner);

        try {
           
            while (true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    System.out.println("Record value is = "+ record.value() +"key " + record.key() + " to partition " + record.partition()
                            + " with offset " + record.offset() );
                    
                    rebalanceListner.addOffset(record.topic(), record.partition(),record.offset()+1);
                }
                consumer.commitAsync(rebalanceListner.getCurrentOffsets(),null);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        	try {
                consumer.commitSync(rebalanceListner.getCurrentOffsets()); 
            } finally {
                consumer.close();
            }
    }
	

	}
}


