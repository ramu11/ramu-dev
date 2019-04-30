package com.redhat.custom.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A bean which we use in the route
 */
public class HelloBean  {
	
	 private static Logger log = LoggerFactory.getLogger(HelloBean.class);

    private String customerName = "Google";
    

    public void hello() throws CustomerNotFoundException {
        
    	  if(customerName.equalsIgnoreCase("Google")){
    		  
    		  throw new CustomerNotFoundException("Customer Not Found"); 
    	  }
    	  log.info(">> Customer find !");
    }

    public String getCustomerName() {
		return customerName;
	}
    public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
