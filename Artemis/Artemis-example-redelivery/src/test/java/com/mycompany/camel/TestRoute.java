/*package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class TestRoute extends CamelTestSupport {

    @Test
    public void testQueue() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceivedInAnyOrder("Hello World");

        template.sendBody("direct:exampleQueueRoute", "Hello World");
        template.sendBody("direct:foo", "Bye World");
        template.sendBody("direct:bar", "Bar");
    }

   @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:exampleQueueRoute").to("mock:result");
                
            }
        };
    }
}
*/