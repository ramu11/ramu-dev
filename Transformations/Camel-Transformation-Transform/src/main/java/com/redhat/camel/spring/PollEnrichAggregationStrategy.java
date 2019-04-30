package com.redhat.camel.spring;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class PollEnrichAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

		if (newExchange == null) {
			return oldExchange;
		}
		String http = oldExchange.getIn().getBody(String.class);
		System.out.println(http);
		String ftp = newExchange.getIn().getBody(String.class);
		System.out.println(ftp);
		String body = http + "<ramu>" + ftp;
		oldExchange.getIn().setBody(body);

		return oldExchange;
	}

}
