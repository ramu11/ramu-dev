package com.redhat.custom.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionProcessor implements Processor  {

	 private static Logger log = LoggerFactory.getLogger(ExceptionProcessor.class);
	public void process(Exchange exchange) throws Exception {
		
	 Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
	 
		
		 log.info("Exception is: " + caused.getMessage() );
		
		
		
	}
	
	

}
