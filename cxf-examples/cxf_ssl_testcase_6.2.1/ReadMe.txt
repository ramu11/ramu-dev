1.ssl-testcase-6.2.1-working contains 2 bundles rest,https_client

2.Build 'rest' bundle  'mvn clean install'

3.Start the Fuse 6.2.1(latest roll up patch) and install cxf feature  using command 'features:install cxf'

4.Deploy it on Fuse 6.2.1(latest roll up patch) karaf container using 'osgi:install -s mvn:org.jboss.quickstarts.fuse/cxf-rest/1.1'

5.you can hit the https service using curl 'curl -k https://localhost:9081/crm/customerservice/customers/123'
ex:curl -k https://localhost:9081/crm/customerservice/customers/123
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer xmlns="http://rest.fabric.quickstarts.fabric8.io/"><id>123</id><name>John</name></Customer>

6.Now build the client bundle  'mvn clean install' the run ' mvn -Pclient ' will display output
Sending HTTPS GET request to query customer info...
Received response from GET call:
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer xmlns="http://rest.fabric.quickstarts.fabric8.io/"><id>123</id><name>John</name></Customer>

fuse logs:
ID: 8
Address: https://localhost:9081/crm/customerservice/customers/123
Http-Method: GET
Content-Type: 
Headers: {Accept=[application/xml], connection=[keep-alive], Content-Type=[null], Host=[localhost:9081], User-Agent=[Apache-HttpClient/4.2.1 (java 1.5)]}
--------------------------------------
2019-03-28 11:55:17,006 | INFO  | tp2076003388-116 | CustomerService                  | 310 - org.jboss.quickstarts.fuse.cxf-rest - 1.1.0 | Invoking getCustomer, Customer id is: 123
2019-03-28 11:55:17,007 | INFO  | tp2076003388-116 | LoggingOutInterceptor            | 73 - org.apache.cxf.cxf-core - 3.0.4.redhat-621222 | Outbound Message
---------------------------
ID: 8
Response-Code: 200
Content-Type: application/xml
Headers: {Content-Type=[application/xml], Date=[Thu, 28 Mar 2019 06:25:17 GMT]}
Payload: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Customer xmlns="http://rest.fabric.quickstarts.fabric8.io/"><id>123</id><name>John</name></Customer>
--------------------------------------

# The below scripts show the commands used to generate the self-signed keys for this sample.
# If you use the below script to create your own keys be sure to change the passwords used here
# DO NOT USE THE SUPPLIED KEYS IN PRODUCTION--everyone has them!!
# For production recommended to use keys signed by a third-party certificate authority (CA)

# Create the combination keystore/truststore for the client and service.
# Note you can create separate keystores/truststores for both if desired
keytool -genkeypair -validity 730 -alias myservicekey -keystore serviceKeystore.jks -dname "cn=localhost" -keypass skpass -storepass sspass
keytool -genkeypair -validity 730 -alias myclientkey -keystore clientKeystore.jks -keypass ckpass -storepass cspass

# Place server public cert in client key/truststore
keytool -export -rfc -keystore serviceKeystore.jks -alias myservicekey -file MyService.cer -storepass sspass
keytool -import -noprompt -trustcacerts -file MyService.cer -alias myservicekey -keystore clientKeystore.jks -storepass cspass

# Place client public cert in service key/truststore
# Note this needs to be done only if you're requiring client authentication
# as configured in resources/ServerConfig.xml
keytool -export -rfc -keystore clientKeystore.jks -alias myclientkey -file MyClient.cer -storepass cspass
keytool -import -noprompt -trustcacerts -file MyClient.cer -alias myclientkey -keystore serviceKeystore.jks -storepass sspass



SSL debugging steps for "peer not authenticated"
1.openssl s_client -connect localhost:9081
2.keytool -list -v -keystore /home/kkakarla/Development/camel-fuse-examples/ssl-testcase-6.2.1-working/https_client/src/main/config/clientKeystore.jks
3.vi /tmp/test.cer
4.keytool -printcert -file /tmp/test.cert
5.keytool -importcert -file /tmp/test.cert -keystore /home/kkakarla/Development/camel-fuse-examples/cxf/ssl-testcase-6.2.1-working/https_client/src/main/config/clientKeystore.jks -storepass cspass -alias localhost
