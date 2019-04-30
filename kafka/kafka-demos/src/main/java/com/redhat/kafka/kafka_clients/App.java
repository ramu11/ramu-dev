/**
The above snippet explains how to produce and consume messages from a Kafka broker. 
If you want to run a producer then call the runProducer function from the main function.
If you want to run a consumeer, then call the runConsumer function from the main function.

The offset of records can be committed to the broker in both asynchronous and synchronous ways. 
Using the synchronous way, the thread will be blocked until an offset has not been written to the broker.*/

package com.redhat.kafka.kafka_clients;

import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.redhat.kafka.kafka_clients.constants.IKafkaConstants;
import com.redhat.kafka.kafka_clients.consumer.ConsumerCreator;
import com.redhat.kafka.kafka_clients.producer.ProducerCreator;

public class App {
    public static void main(String[] args) {
       // runProducer();
         runConsumer();
    }

    static void runConsumer() {
        Consumer<String, String> consumer = ConsumerCreator.createConsumer();
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
            // 1000 is the time in milliseconds consumer will wait if no record is found at
            // broker.
            if (consumerRecords.count() == 0) {
                noMessageFound++;
                if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    // If no message found count is reached to threshold exit loop.
                    break;
                else
                    continue;
            }
            // print each record.
            consumerRecords.forEach(record -> {
                System.out.println("Record Key " + record.key());
                System.out.println("Record value " + record.value());
                System.out.println("Record partition " + record.partition());
                System.out.println("Record offset " + record.offset());
            });
            // commits the offset of record to broker.
            consumer.commitAsync();
        }
        consumer.close();
    }

    static void runProducer() {
        Producer<String, String> producer = ProducerCreator.createProducer();
        ProducerRecord<String, String> record;
        for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {

            record = new ProducerRecord<String, String>(IKafkaConstants.TOPIC_NAME, "USA: First order ");

            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}
