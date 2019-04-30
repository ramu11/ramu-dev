package org.example.restdsl;

import org.example.restdsl.types.TestResponse;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestDslRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /**
         * Use 'restlet', which is a very simple component for providing REST
         * services. Ensure that camel-restlet or camel-restlet-starter is
         * included as a Maven dependency first.
         */
        restConfiguration()
                .component("undertow")
               // .componentProperty("camel.component.undertow.host-options.buffer-size", "10024")
               // .componentProperty("matchOnUriPrefix", "true")
                //.endpointProperty("matchOnUriPrefix", "true")
                .host("0.0.0.0").port("9090")
                .bindingMode(RestBindingMode.auto);

        /**
         * Configure the REST API (POST, GET, etc.)
         */
         rest().path("/foo").consumes("application/json")
                      // .post().type(TestResponse.class)
                    .get()
                    .to("bean:postBean");
         
         
       /*  from("timer:hello?period=3000")
         .setHeader("id", simple("${random(1,3)}"))
         .to("rest:get:pets/{id}")
         .log("${body}");
         */
         
                    
    }
}
