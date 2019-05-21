package com.redhat.kafkademo;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProcessor implements Processor{

	@Override
	public void process(Exchange exc) throws Exception {
		
		List<RecordMetadata> recordMetaData1 = (List<RecordMetadata>) exc.getIn().getHeader(KafkaConstants.KAFKA_RECORDMETA);
		
		for(RecordMetadata rd: recordMetaData1) {
			
			System.out.println("producer partiotion is:" +rd.partition());
		}
		
	}
	
}

