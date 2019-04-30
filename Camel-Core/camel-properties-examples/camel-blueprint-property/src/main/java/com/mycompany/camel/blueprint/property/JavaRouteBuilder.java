package com.mycompany.camel.blueprint.property;

import org.apache.camel.builder.RouteBuilder;

public class JavaRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("timer:dsl?period={{period}}")
		.setBody(method("helloBean","hello"))
		.log("Every {{period}} ms print from Java DSL ${body}")
		.to("mock:result"); 
	}
}
