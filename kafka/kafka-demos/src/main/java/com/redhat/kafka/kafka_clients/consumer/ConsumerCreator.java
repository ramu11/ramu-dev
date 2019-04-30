
/**The below snippet creates a Kafka consumer with some properties.

BOOTSTRAP_SERVERS_CONFIG: The Kafka broker's address. If Kafka is running in a cluster then you can provide comma (,) seperated addresses.
 For example: localhost:9091,localhost:9092.

GROUP_ID_CONFIG: The consumer group id used to identify to which group this consumer belongs.

KEY_DESERIALIZER_CLASS_CONFIG: The class name to deserialize the key object. We have used Long as the key so we will be using LongDeserializer
                               as the deserializer class. You can create your custom deserializer by implementing the Deserializer interface
                               provided by Kafka.

VALUE_DESERIALIZER_CLASS_CONFIG: The class name to deserialize the value object. We have used String as the value 
                                 so we will be using StringDeserializer as the deserializer class. You can create your custom deserializer.
                                   For example:
                                   
MAX_POLL_RECORDS_CONFIG: The max count of records that the consumer will fetch in one iteration.

ENABLE_AUTO_COMMIT_CONFIG: When the consumer from a group receives a message it must commit the offset of that record. If this configuration is set to be true then, 
                           periodically, offsets will be committed, but, for the production level, this should be false 
                           and an offset should be committed manually.

AUTO_OFFSET_RESET_CONFIG: For each consumer group, the last committed offset value is stored. 
                          This configuration comes handy if no offset is committed for that group, i.e. it is the new group created.

Setting this value to earliest will cause the consumer to fetch records from the beginning of offset i.e from zero.

Setting this value to latest will cause the consumer to fetch records from the new records. 
By new records mean those created after the consumer group became active.*/   

package com.redhat.kafka.kafka_clients.consumer;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.redhat.kafka.kafka_clients.constants.IKafkaConstants;

public class ConsumerCreator {
    
    public static Consumer<String, String> createConsumer() {
        
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));
        
        return consumer;
    }
}
