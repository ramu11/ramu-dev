# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel with Spring Boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every 5th second, and routes the message to a log.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

The example can be built and run on OpenShift using a single goal:

    mvn fabric8:deploy

When the example runs in OpenShift, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods
    
To Run the example as external client,first extract/import the openshift kafka cluster cert

   oc extract secret/my-cluster-cluster-ca-cert --keys=ca.crt --to=- > src/main/resources/ca.crt
   keytool -keystore src/main/resources/client.truststore.jks -alias CARoot -import -file src/main/resources/ca.crt
   keytool -import -trustcacerts -alias root -file src/main/resources/ca.crt -keystore src/main/resources/client.truststore.jks -storepass  amqstreams123 -noprompt


steps:

   oc login -u system:admin

   oc adm policy add-cluster-role-to-user cluster-admin quicklab

   oc new-project my-project

   sed -i 's/namespace: .*/namespace: my-project/' install/cluster-operator/*RoleBinding*.yaml

   oc project my-project

   oc apply -f examples/templates/cluster-operator -n my-project

   oc adm policy add-cluster-role-to-user strimzi-cluster-operator-namespaced --serviceaccount strimzi-cluster-operator -n my-project

   oc adm policy add-cluster-role-to-user strimzi-entity-operator --serviceaccount strimzi-cluster-operator -n my-project

   oc adm policy add-cluster-role-to-user strimzi-topic-operator --serviceaccount strimzi-cluster-operator -n my-project

   oc apply -f install/cluster-operator -n my-project

   oc apply -f examples/kafka/kafka-persistent.yaml (or) oc apply -f examples/kafka/kafka-ephemeral.yaml

   oc apply -f kafkaTopic.yaml

   oc get pods

   oc get pods -w

   ./kafka-topics.sh --zookeeper localhost:2181 --describe --topic my-topic-abc

Scaling up a Kafka cluster Reassign partiotions:

   cat topic-reassign.json | oc rsh -c kafka my-cluster-kafka-1 /bin/bash -c 'cat >/tmp/topic-reassign.json'

   oc rsh -c kafka my-cluster-kafka-1  bin/kafka-reassign-partitions.sh --zookeeper localhost:2181  --reassignment-json-file /tmp/topic-reassign.json --execute


