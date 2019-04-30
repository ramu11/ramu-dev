/**
 *  Kafka producer with some properties.

BOOTSTRAP_SERVERS_CONFIG: The Kafka broker's address. If Kafka is running in a cluster then you can provide comma (,) seperated addresses. 
                          For example:localhost:9091,localhost:9092 

CLIENT_ID_CONFIG: Id of the producer so that the broker can determine the source of the request.

KEY_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the key object. In our example, our key is Long, 
                              so we can use the LongSerializer class to serialize the key. If in your use case you are using 
                              some other object as the key then you can create your custom serializer class 
                              by implementing the Serializer interface of Kafka and overriding the serialize method.

VALUE_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the value object. In our example, our value is String, 
                               so we can use the StringSerializer class to serialize the key. If your value is some other object 
                               then you create your custom serializer class. For example:
PARTITIONER_CLASS_CONFIG: The class that will be used to determine the partition in which the record will go. In the demo topic, 
                            there is only one partition, so I have commented this property. 
                            You can create your custom partitioner by implementing the CustomPartitioner interface. For example                               
 */

package com.redhat.kafka.kafka_clients.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.redhat.kafka.kafka_clients.constants.IKafkaConstants;
import com.redhat.kafka.kafka_clients.partitioner.CustomPartitioner;

public class ProducerCreator {
    public static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CustomPartitioner.class.getCanonicalName());
        props.put("partitions.0","USA");
        props.put("partitions.1","India");
        return new KafkaProducer<String, String>(props);
    }

}
