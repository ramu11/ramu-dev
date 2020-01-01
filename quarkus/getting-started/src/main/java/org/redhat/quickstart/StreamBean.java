package org.redhat.quickstart;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;

@ApplicationScoped   
public class StreamBean {
    
    AtomicInteger counter = new AtomicInteger();
    
    public Publisher<String> stream() {
        return Flowable.interval(500, TimeUnit.MILLISECONDS)
                .map(i -> counter.incrementAndGet())
                .map(i ->i.toString());
    }

}
