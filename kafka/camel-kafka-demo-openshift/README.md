# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel with Spring Boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every 5th second, and routes the message to a log.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can be built and run on OpenShift using a single goal:

    mvn fabric8:deploy

When the example runs in OpenShift, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods

steps:
oc login -u system:admin
oc adm policy add-cluster-role-to-user cluster-admin quicklab
 oc new-project my-project2
 sed -i 's/namespace: .*/namespace: my-project2/' install/cluster-operator/*RoleBinding*.yaml
 oc project my-project2
 oc apply -f examples/templates/cluster-operator -n my-project2
 oc adm policy add-cluster-role-to-user strimzi-cluster-operator-namespaced --serviceaccount strimzi-cluster-operator -n my-project2
 oc adm policy add-cluster-role-to-user strimzi-entity-operator --serviceaccount strimzi-cluster-operator -n my-project2
 oc adm policy add-cluster-role-to-user strimzi-topic-operator --serviceaccount strimzi-cluster-operator -n my-project2
 oc apply -f install/cluster-operator -n my-project2
 oc apply -f examples/kafka/kafka-persistent.yaml (or) oc apply -f examples/kafka/kafka-jbod.yaml
 oc apply -f kafkaTopic.yaml
 oc get pods;
 oc get pods -w

./kafka-topics.sh --zookeeper localhost:2181 --describe --topic my-topic-abc

Scaling up a Kafka cluster Reassign partiotions:
cat topic-reassign.json | oc rsh -c kafka my-cluster-kafka-1 /bin/bash -c 'cat >/tmp/topic-reassign.json'
oc rsh -c kafka my-cluster-kafka-1  bin/kafka-reassign-partitions.sh --zookeeper localhost:2181  --reassignment-json-file /tmp/topic-reassign.json --execute


