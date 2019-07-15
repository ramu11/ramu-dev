package org.apache.camel.example.kafka.avro;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.kafkatutorials.Employee;

public class KafkaAvroMessageProcessor  implements Processor {
	 private static final Logger LOG = LoggerFactory.getLogger(KafkaAvroProcessor.class);
	
	 public void process(Exchange exc) throws Exception {
		 
			Employee emp = Employee.newBuilder()
					.setFirstName("kakarla")
					.setLastName("Ranjith")
					.setBirthDate(new java.util.Date().getTime())
					.build();
			
			exc.getOut().setBody(emp);
	 }

}
