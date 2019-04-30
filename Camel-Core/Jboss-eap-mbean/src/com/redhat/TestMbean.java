package com.redhat;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.*;

public class TestMbean {
	
	 public static void main(String[] args) throws Exception 
     {
            String host = "127.0.0.1";  // Your JBoss Bind Address, default is localhost
            int port = 9999; 
            String url ="service:jmx:remoting-jmx://" + host + ":" + port;
            System.out.println("  \n\n\t**  url : "+url +"  ** ");
            JMXServiceURL serviceURL = new JMXServiceURL(url);
            JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
            MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
            //get camel mbean
            ObjectName objectName1=new ObjectName("org.apache.camel:context=camel-1,type=routes,name=\"helloRoute\"");
            String serverName1=(String)connection.getAttribute(objectName1, "RouteId");
            System.out.println("\n routeid           = \t"+serverName1);
            //  Invoke operation start
                 connection.invoke(objectName1,"start()" , null, null); 
            //    connection.invoke(objectName1,"stop()" , null, null);
                String status=(String)connection.getAttribute(objectName1, "State");
                System.out.println("\n            = \t"+status);
            jmxConnector.close();
     }

}
