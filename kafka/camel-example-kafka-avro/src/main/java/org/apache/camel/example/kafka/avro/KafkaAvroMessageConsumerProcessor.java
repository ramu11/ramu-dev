package org.apache.camel.example.kafka.avro;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaAvroMessageConsumerProcessor  implements Processor {
	 private static final Logger LOG = LoggerFactory.getLogger(KafkaAvroMessageConsumerProcessor.class);
	
	 public void process(Exchange exchange) throws Exception {
		 
		      String body = exchange.getIn().getBody(String.class);
		      LOG.info("KafkaAvroMessageConsumerProcessor:" +body);
			
	 }

}
