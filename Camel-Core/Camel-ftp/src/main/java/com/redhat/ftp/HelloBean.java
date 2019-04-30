package com.redhat.ftp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;

/**
 * A bean which we use in the route
 */
public class HelloBean{

   
    
public void process(Exchange exchange) throws Exception {
		
		String fileName = (String) exchange.getIn().getHeader("CamelFileNameOnly");
		exchange.setProperty("myPropertyNew", fileName);

	}

   
}
