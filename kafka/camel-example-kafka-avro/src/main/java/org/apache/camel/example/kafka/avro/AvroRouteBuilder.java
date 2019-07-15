package org.apache.camel.example.kafka.avro;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.language.SimpleExpression;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.kafkatutorials.*;

public class AvroRouteBuilder extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		from("timer://foo?period={{period}}")
	    .setBody(constant("Hi This is Avro example"))
	    .process(new KafkaAvroMessageProcessor())
	    .to("kafka:{{producer.topic}}?brokers={{kafka.bootstrap.url}}&keySerializerClass=org.apache.kafka.common.serialization.StringSerializer&serializerClass=org.apache.camel.example.kafka.avro.CustomKafkaAvroSerializer");
	       		
		from("timer://foo?period={{period}}")
		.from("kafka:{{consumer.topic}}?brokers={{kafka.bootstrap.url}}&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer&valueDeserializer=org.apache.camel.example.kafka.avro.CustomKafkaAvroDeserializer")
		 .process(new KafkaAvroMessageConsumerProcessor())
		.log("${body}");
		
	}
	  

}
