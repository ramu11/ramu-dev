/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.redhat;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.client.spring.EnableJaxRsWebClient;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableJaxRsWebClient
public class SampleRestApplication {

   // @Autowired
    //private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(SampleRestApplication.class, args);
    }
    
    
    /*@Bean
    CommandLineRunner initWebClientRunner(final WebClient webClient) {
       
      return new CommandLineRunner() {
 
        @Override
        public void run(String... runArgs) throws Exception {
            System.out.println("command line response is:" +webClient.path("sayHello").get(String.class));
        }
      };
    }*/
 
    @Bean
    public WebClient rsClient() {
    	
    	
        // setup CXF-RS
        JAXRSClientFactoryBean endpoint = new JAXRSClientFactoryBean();
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus("beans.xml");
        endpoint.setBus(bus);
        endpoint.setAddress("https://localhost:9095/sayHello");
        System.out.println("client reponse is:" +endpoint.createWebClient().get(String.class) );
        return endpoint.createWebClient();
    }
}
