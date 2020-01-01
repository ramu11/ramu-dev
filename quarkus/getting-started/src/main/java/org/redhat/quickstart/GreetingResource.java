package org.redhat.quickstart;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

@Path("/hello")
public class GreetingResource {
    
    
    @Inject
    @ConfigProperty(name="greeting")
    String greeting;
    
    @Inject
    StreamBean bean;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeting;
    }
    
    @GET
    @Path("/async")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> helloAsync() {
        return ReactiveStreams.of("h","e","l","l","o")
                .map(s->s.toUpperCase())
                .toList().run().thenApply(l->l.toString());
    }
    
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> helloStream() {
        return bean.stream();
    }
    
    
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person helloPerson() {
        return new Person("Ramu kakarla",25);
    }
        
}