package com.redhat.kafka.kafka_clients.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;


public class ManualConsumerPartitionAssign {

	public static void main(String[] args) {
		
		String topicName1 = "simple-topic";
        String topicName2 = "test-topic";
       // String groupName = "TestTopicGroup";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
       // props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");
        props.put("kafka.offset", "none");
       
       

        KafkaConsumer<String, String> consumer = null;
        TopicPartition partition0 = new TopicPartition(topicName1, 0);
        TopicPartition partition1 = new TopicPartition(topicName1, 1);
        TopicPartition partition2 = new TopicPartition(topicName2, 2);
        
        List<TopicPartition> topics = Arrays.asList(partition1);
      
        try {
            consumer = new KafkaConsumer<>(props);
            consumer.assign(topics);
           // consumer.seekToBeginning(topics);
           // consumer.seekToEnd(topics);
              consumer.seek(partition1, 343);
           
           

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
            consumer.commitSync();
            consumer.close();
        }
    }
	

	}


