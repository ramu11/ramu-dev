package com.mycompany.camel;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.camel.util.FileUtil;

/**
 * A helper for unit testing with Apache ActiveMQ as embedded JMS broker.
 *
 * @version 
 */
/*public final class CamelJmsTestHelper {

    private static AtomicInteger counter = new AtomicInteger(0);

    private CamelJmsTestHelper() {
    }

   

    public static ConnectionFactory createConnectionFactory() {
        return createConnectionFactory(null, null);
    }

    public static ConnectionFactory createConnectionFactory(String options, Integer maximumRedeliveries) {
        // using a unique broker name improves testing when running the entire test suite in the same JVM
        int id = counter.incrementAndGet();
        String url = "tcp://localhost:61616";
       
        ActiveMQJMSConnectionFactory connectionFactory = new ActiveMQJMSConnectionFactory(url,"amq","amq");
       
        return connectionFactory;
    }

   

  
}
*/