package com.redhat.kafkademo;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerApp {

    public static void main(String[] args) throws Exception {

        final Properties props = new Properties();
        props.put("bootstrap.servers", "my-cluster-kafka-bootstrap-my-project.apps.kakarlaamqstreams.lab.pnq2.cee.redhat.com:443");
        props.put("group.id", "sample-consumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("security.protocol", "SSL");
		props.put("ssl.keystore.location", "src/main/resources/client.truststore.jks");
		props.put("ssl.keystore.password", "amqstreams123");
		props.put("ssl.truststore.location", "src/main/resources/client.truststore.jks");
		props.put("ssl.truststore.password", "amqstreams123");

        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("my-topic"));
            while (true) {
                final ConsumerRecords<String, String> records = consumer.poll(1000);
                for (final ConsumerRecord<String, String> record : records) {
                    System.out.println("Receiving message: " + record.value());
                }
            }
        }
    }


}