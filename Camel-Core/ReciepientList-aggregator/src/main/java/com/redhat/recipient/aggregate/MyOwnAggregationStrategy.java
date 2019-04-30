package com.redhat.recipient.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyOwnAggregationStrategy implements  AggregationStrategy{
	
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        String body = oldExchange.getIn().getBody(String.class);
        
        oldExchange.getIn().setBody(body + newExchange.getIn().getBody(String.class));
        return oldExchange;
    }

}
