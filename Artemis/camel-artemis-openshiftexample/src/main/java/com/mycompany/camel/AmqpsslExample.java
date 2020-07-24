package com.mycompany.camel;
import org.apache.qpid.jms.JmsConnectionFactory;
import javax.jms.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;
public class AmqpsslExample {

    public void amqpTest() throws Exception{

        JmsConnectionFactory activeMQConnectionFactory = new JmsConnectionFactory("7nRMRFDZ","bFxUBMQJ","amqps://ex-aao-amqp-0-svc-rte-new-message-project.apps.kakarlatest.lab.upshift.rdu2.redhat.com:443?" +
                "transport.trustStoreLocation=/home/kkakarla/development/amq7/amq7-on-openshift/client.ts&transport.keyStoreLocation=/home/kkakarla/development/amq7/amq7-on-openshift/broker.ks" +
                "&transport.trustStorePassword=artemis7&transport.keyStorePassword=artemis7&transport.verifyHost=false");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(session.createQueue("test_q"));
        Message message = session.createTextMessage("this is amqp two way ssl testing");
        messageProducer.send(message);
        connection.close();
    }
    
    public void amqpTestConsumer() throws Exception{

        JmsConnectionFactory activeMQConnectionFactory = new JmsConnectionFactory("7nRMRFDZ","bFxUBMQJ","amqps://ex-aao-amqp-0-svc-rte-new-message-project.apps.kakarlatest.lab.upshift.rdu2.redhat.com:443?" +
                "transport.trustStoreLocation=/home/kkakarla/development/amq7/amq7-on-openshift/client.ts&transport.keyStoreLocation=//home/kkakarla/development/amq7/amq7-on-openshift/broker.ks" +
                "&transport.trustStorePassword=artemis7&transport.keyStorePassword=artemis7&transport.verifyHost=false");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // Step 5. create a moving receiver, this means the message will be removed from the queue
        MessageConsumer consumer = session.createConsumer(session.createQueue("test_q"));

        // Step 7. receive the simple message
        TextMessage m = (TextMessage) consumer.receive(5000);
        System.out.println("message = " + m.getText());
        connection.close();
    }
}