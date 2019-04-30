package com.mycompany.cxf.soap.endpoint;

import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.apache.log4j.Logger;



public class TestRequest {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public String personRequest() {
		
		return "1";
	}
	
		
	  public void getPersonResponse(final Exchange exchange) throws Exception {

		  com.mycompany.cxf.soap.model.Person response = null;
    	  
    	MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
    	  
    	
    	response = (com.mycompany.cxf.soap.model.Person) msgList.get(0);
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append(response.getId());
    	sb.append(response.getName());
    	sb.append(response.getAge());
        
        
       exchange.getOut().setBody(sb.toString());
    }
}
