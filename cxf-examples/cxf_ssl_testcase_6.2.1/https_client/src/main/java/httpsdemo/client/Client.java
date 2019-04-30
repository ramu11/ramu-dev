/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package httpsdemo.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;


public final class Client {

    private static final String CLIENT_CONFIG_FILE = "ClientConfig.xml";
    private static final String BASE_SERVICE_URL = 
        "https://localhost:9081/crm/customerservice/customers/123";
    
    private Client() {
    }

    public static void main(String args[]) throws Exception {       
        String keyStoreLoc = "src/main/config/clientKeystore.jks";

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keyStoreLoc), "cspass".toCharArray());

        /* 
         * Send HTTP GET request to query customer info using portable HttpClient
         * object from Apache HttpComponents
         */
        SSLSocketFactory sf = new SSLSocketFactory(keyStore, "ckpass", keyStore); 
        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme httpsScheme = new Scheme("https", 9081, sf);

        System.out.println("Sending HTTPS POST request to create customer...");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getConnectionManager().getSchemeRegistry().register(httpsScheme);
        HttpPost httpPost = new HttpPost(BASE_SERVICE_URL);
        BasicHeader bh1 = new BasicHeader("Content-Type" , "application/customer+xml");
        BasicHeader bh2 = new BasicHeader("Accept" , "application/customer+xml");
        httpPost.addHeader(bh1);
        httpPost.addHeader(bh2);
        httpPost.setEntity(new InputStreamEntity(
                new FileInputStream(new File("src/main/resources/customer.xml")), -1));
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();

       /*
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                System.out.println("received response from POST call:");
                System.out.println(fromStream(instream));
            } finally {
                instream.close();
              }
        }
        */
        System.out.println("Received response from POST call:");
        entity.writeTo(System.out);
        System.out.println("\n");

        System.out.println("Sending HTTPS GET request to query customer info...");
        //DefaultHttpClient httpclient = new DefaultHttpClient();
        //httpclient.getConnectionManager().getSchemeRegistry().register(httpsScheme);
        HttpGet httpget = new HttpGet(BASE_SERVICE_URL);
        BasicHeader bh = new BasicHeader("Accept" , "application/xml");
        httpget.addHeader(bh);
        response = httpclient.execute(httpget);
        entity = response.getEntity();
        if (entity != null) {
            System.out.println("Received response from GET call:");
            entity.writeTo(System.out);
            System.out.println("\n");
        }
        httpclient.getConnectionManager().shutdown();
        
        /*
         *  Send HTTP PUT request to update customer info, using CXF WebClient method
         *  Note: if need to use basic authentication, use the WebClient.create(baseAddress,
         *  username,password,configFile) variant, where configFile can be null if you're
         *  not using certificates.
         */
/*
        System.out.println("\n\nSending HTTPS PUT to update customer name");
        WebClient wc = WebClient.create(BASE_SERVICE_URL, CLIENT_CONFIG_FILE);
        Customer customer = new Customer();
        customer.setId(123);
        customer.setName("Mary");
        Response resp = wc.put(customer);
*/
        /*
         *  Send HTTP POST request to add customer, using JAXRSClientProxy
         *  Note: if need to use basic authentication, use the JAXRSClientFactory.create(baseAddress,
         *  username,password,configFile) variant, where configFile can be null if you're
         *  not using certificates.
         */
/*
        System.out.println("\n\nSending HTTPS POST request to add customer");
        CustomerService proxy = JAXRSClientFactory.create(BASE_SERVICE_URL, CustomerService.class,
              CLIENT_CONFIG_FILE);
        customer = new Customer();
        customer.setName("Jack");
        resp = wc.post(customer);
        
        System.out.println("\n");
        System.exit(0);
*/
    }

    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
