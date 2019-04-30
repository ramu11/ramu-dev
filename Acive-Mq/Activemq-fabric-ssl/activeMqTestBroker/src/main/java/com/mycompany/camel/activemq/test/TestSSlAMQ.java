package com.mycompany.camel.activemq.test;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.apache.log4j.BasicConfigurator;

public class TestSSlAMQ  {
	
	 public TestSSlAMQ() throws Exception {

		 String url ="ssl://kkakarla.pnq.csb:61616" ;// The broker URL

		// Configure the secure connection factory.
		 ActiveMQSslConnectionFactory connectionFactory = new ActiveMQSslConnectionFactory(url);
		 connectionFactory.setKeyStore("src/conf/client-keystore.jks");
		 connectionFactory.setKeyStorePassword("storepassword");
		 connectionFactory.setTrustStore("src/conf/client-truststore.jks");
		 connectionFactory.setTrustStorePassword("storepassword");
		 connectionFactory.setUserName("admin");
		 connectionFactory.setPassword("admin");
		
	 

	  // Getting JMS connection from the server and starting it

	  Connection connection = connectionFactory.createConnection();

	  try {

	   connection.start();

	   // JMS messages are sent and received using a Session. We will

	   // create here a non-transactional session object. If you want

	   // to use transactions you should set the first parameter to 'true'

	// Create the session
	  
	   Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	   Destination destination = session.createQueue("MYQUEUE");
	   // MessageProducer is used for sending messages (as opposed

	   // to MessageConsumer which is used for receiving them)

	   MessageProducer producer = session.createProducer(destination);

	   // We will send a small text message saying 'Hello World!'

	   TextMessage message = session.createTextMessage("Hello World!");

	   // Here we are sending the message!

	   producer.send(message);

	   System.out.println("Sent message '" + message.getText() + "'");

	  } finally {

	   connection.close();

	  }

	 }

	 public static void main(String[] args) throws JMSException {

	  try {

	   BasicConfigurator.configure();

	   new TestSSlAMQ();

	  } catch (Exception e) {

	   e.printStackTrace();

	  }

	 }

	}