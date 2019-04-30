package com.test;
import java.io.File;
import java.net.URL;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


public class fileInputTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}
	
	
	 // START SNIPPET: e1
    @Override
    protected String[] loadConfigAdminConfigurationFile() {
        // String[0] = tell Camel the path of the .cfg file to use for OSGi ConfigAdmin in the blueprint XML file
        // String[1] = tell Camel the persistence-id of the cm:property-placeholder in the blueprint XML file
        return new String[]{"src/test/resources/com.mycompany.camel.blueprint.property.cfg", "com.mycompany.camel.blueprint.property"};
    }
    // END SNIPPET: e1

    @Test
    public void testConfigAdmin() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived("Bye World");

        template.sendBody("direct:start1", "World");

        assertMockEndpointsSatisfied();
    }
}