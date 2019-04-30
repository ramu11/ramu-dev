JBoss Fuse proof of concept - Using OSGi Compendium Configuration Admin Service in Camel Spring module
=========

In this example I demonstrate how to use
[OSGi Compendium Configuration Admin
Service](http://www.osgi.org/javadoc/r4v42/org/osgi/service/cm/ConfigurationAdmin.html) in Camel Spring module. The example is verified by companion Pax Exam tests.

In order to execute this proof of concept just run the following command in `fuse-pocs-camel-spring-properties`
directory:

    mvn -U clean install

To get this sample runnimg, you will have to install jboss-fuse in your local maven repo also using the command before firing test case

#####

mvn install:install-file -Dfile="/home/Downloads/Software/Red Hat/jboss-fuse-full-6.2.1.redhat-084.zip" -DgroupId=org.jboss.fuse -DartifactId=jboss-fuse-full -Dversion=6.2.1.redhat-084 -Dpackaging=zip

######
