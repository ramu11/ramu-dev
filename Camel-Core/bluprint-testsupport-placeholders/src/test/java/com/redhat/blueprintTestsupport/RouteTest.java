package com.redhat.blueprintTestsupport;

import java.util.Dictionary;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/blueprint.xml";
    }

    @Test
    public void testRoute() throws Exception {
        // the route is timer based, so every 5th second a message is send
        // we should then expect at least one message
       // getMockEndpoint("mock:original").expectedMinimumMessageCount(1);
        getMockEndpoint("mock:extra").expectedMinimumMessageCount(1);
        
       // getMockEndpoint("mock:original").expectedBodiesReceived("Bye World", " Bye World");
        
         template.sendBody("direct:start", "Hello");

        // assert expectations
        assertMockEndpointsSatisfied();
    }
    
    
    @Override
    protected String[] loadConfigAdminConfigurationFile() {
       
        return new String[]{"src/test/resources/etc/stuff.cfg", "stuff"};
    }
    
    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        // override / add extra properties
        props.put("destination", "mock:extra");
   
        // return the persistence-id to use
        return "stuff";
    }

}
