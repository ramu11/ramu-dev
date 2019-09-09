package com.redhat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleKafkaProducer {

    public static void main(String[] args) throws Exception {
        String topicName = "irisInputTopic";
        String key = "set";
        String value = "6.4,3.2,4.5,1.5";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        
        List<String> irisList = readByJava8("iris.txt");
        irisList.forEach(System.out::println);

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;
        for (int i = 0; i <irisList.size() ; i++) {
              record = new ProducerRecord<String, String>(topicName, key+i, irisList.get(i));
      

            RecordMetadata metadata = producer.send(record).get();
            System.out.println(
                    "Message is sent to Partition no " + metadata.partition() + " and offset " + metadata.offset());
            System.out.println("SimpleOrderProducer Completed with success.");
      
        }
        producer.flush();
        producer.close();
    }
    
    private static List<String> readByJava8(String fileName) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;

    }


    }


