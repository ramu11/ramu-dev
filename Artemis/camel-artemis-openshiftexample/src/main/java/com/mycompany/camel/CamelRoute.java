package com.mycompany.camel;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
			JmsComponent component = createArtemisComponent();
			getContext().addComponent("artemis", component);
		
			/*from("timer://foo?fixedRate=true&period=60000&repeatCount=2")
				.setBody().constant("HELLO")
				.to("artemis:queue:ExampleQueue")
				.log("Sent --> ${body}"); */
			
			from("file:src/data?noop=true")
            .to("artemis:topic:ExampleTopic")
            .log("Sent --> ${body}");
				
	}

	private JmsComponent createArtemisComponent() {

		ActiveMQJMSConnectionFactory connectionFactory= new ActiveMQJMSConnectionFactory("tcp://localhost:61616");
		connectionFactory.setUser("amq");
		connectionFactory.setPassword("amq");

		JmsComponent component = new JmsComponent();
		component.setConnectionFactory(connectionFactory);
		
		return component;
	}
}
