/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.camel;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.qpid.jms.JmsConnectionFactory;

/**
 * A simple JMS example that shows how to use a durable subscription.
 */
public class TopicExample {

   public static void main(final String[] args) throws Exception {
      Connection connection = null;
      ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");

      try {
       // Step 1. Create an amqp qpid 1.0 connection
          connection = connectionFactory.createConnection();
          
          connection.setClientID("Consumer.D");

          // Step 2. Create a session
          Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

          Topic topic = ActiveMQJMSClient.createTopic("VirtualTopic.Orders");    

         // Step 6. Start the connection
         connection.start();

      
         // Step 9. Create the subscription and the subscriber.
         TopicSubscriber subscriber = session.createDurableSubscriber(topic, "VirtualTopic.Orders");
         
         MessageProducer messageProducer = session.createProducer(topic);

         // Step 10. Create a text message
         TextMessage message1 = session.createTextMessage("This is a text message 1");
         
         // Step 11. Send the text message to the topic
         messageProducer.send(message1);

         System.out.println("Sent message: " + message1.getText());

         // Step 12. Consume the message from the durable subscription

          TextMessage messageReceived = (TextMessage) subscriber.receive();

          System.out.println("Received message: " + messageReceived.getText());

       
         subscriber.close();

        

         // Step 18. Delete the durable subscription
         //session.unsubscribe("VirtualTopic.Orders");
      } finally {
         if (connection != null) {
            // Step 19. Be sure to close our JMS resources!
            connection.close();
         }
        
      }
   }
}