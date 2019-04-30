package com.redhat.recipient.aggregate;

import org.apache.camel.Exchange;

public class MessageRouter {
	
	 public String getEndpointsToRouteMessageTo(Exchange exchange) {
	        String orderType = "direct:order.normal,direct:billing";
	            return orderType;
	        }
	    

}
