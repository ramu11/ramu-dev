package com.mycompany.camel;
import javax.jms.ConnectionFactory;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

import java.util.Arrays;

/*public class JmsSelectorOptionTest extends CamelTestSupport {
    
    protected String componentName = "activemq";
    private String rep = "mock:result";

    @Test
    public void testJmsMessageWithSelector() throws Exception {
       
        
        //for Topics
        
        
        String[] nasdaqStocks = new String[]{"AAPL=350.56","ORCL=33.68","CSCO=18.85","GOOG=630.08","AAPL=350.10"};
        getMockEndpoint(rep).expectedMessageCount(5);
        getMockEndpoint(rep).expectedBodiesReceived(Arrays.asList(nasdaqStocks));
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", nasdaqStocks[0], "SE", "NASDAQ");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", "HD=38.48", "SE", "DOWJONES");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", nasdaqStocks[1], "SE", "NASDAQ");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", "HD=38.00", "SE", "DOWJONES");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", nasdaqStocks[2], "SE", "NASDAQ");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", nasdaqStocks[3], "SE", "NASDAQ");
        template.sendBodyAndHeader("activemq:topic:STOCKS.UK", nasdaqStocks[4], "SE", "NASDAQ");
    }
    
   

    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        ConnectionFactory connectionFactory = CamelJmsTestHelper.createConnectionFactory();
        camelContext.addComponent(componentName, jmsComponentAutoAcknowledge(connectionFactory));

        return camelContext;
    }

    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
               
            }
        };
    }

}*/
