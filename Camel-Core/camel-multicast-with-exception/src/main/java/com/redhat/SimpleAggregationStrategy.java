package com.redhat;

import org.apache.camel.Exchange;

public class SimpleAggregationStrategy implements org.apache.camel.processor.aggregate.AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		  
		 /* String body = newExchange.getIn().getBody(String.class);
		  System.out.println("body is :" +body) ;
		  for(Object s: newExchange.getIn().getHeaders().values())
		  {
		   if(s instanceof java.lang.Boolean) {
			  
			   Boolean h = (Boolean)s;
			   System.out.println("headers is :" +h);
		   } else if (s instanceof java.lang.Long){
			   
			   Long h1 = (Long)s;
			   System.out.println("headers is :" +h1);
		   } else {
			   
			   String h2 = (String)s;
			   System.out.println("headers is :" +h2);
		   }
		  
		  }*/
		  
	      	      
	        return oldExchange;
		
		
	}

}
