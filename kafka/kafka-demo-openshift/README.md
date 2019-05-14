Download the Red Hat AMQ Streams installation and example resources from the Red Hat Customer Portal.

Navigate to the unzipped folder to get access to the yaml files

Unzip the downloaded install_and_examples_0.zip file.
$ cd <your_download_folder>/install_and_examples_0

Login in to the OpenShift cluster with admin privileges:
$ oc login -u system:admin

create a ne project
oc new-project my-project

Modify the installation files according to the namespace the Cluster Operator is going to be installed in.
sed -i 's/namespace: .*/namespace: my-project/' install/cluster-operator/*RoleBinding*.yaml

Deploy the Cluster Operator
$ oc apply -f install/cluster-operator -n my-project

Create ClusterRoleBindings that grant cluster-wide access to all OpenShift projects or Kubernetes namespaces to the Cluster Operator.
oc adm policy add-cluster-role-to-user strimzi-cluster-operator-namespaced --serviceaccount strimzi-cluster-operator -n my-project
oc adm policy add-cluster-role-to-user strimzi-entity-operator --serviceaccount strimzi-cluster-operator -n my-project
oc adm policy add-cluster-role-to-user strimzi-topic-operator --serviceaccount strimzi-cluster-operator -n my-project

Deploying the Kafka cluster to OpenShift
oc apply -f examples/kafka/kafka-ephemeral.yaml


The last step will create the Kafka CRD and start the deployment of the Cluster Operator. This operator will keep track of your kafka resources and provision or update the changes to those resources. Open a new browser tab and navigate to your web console URL:
https://<your-ip>:8443/console/project/myproject/overview

Check the assigned IP issuing the minishift ip command or just run  minishift console and navigate to My Project.

Login in to the OpenShift web console to check the deployment. Use developer/developer as the user and password. If you haven’t done before, accept the self signed certificates in your browser.

Setup your first Apache Kafka Cluster
The Cluster Operator now will listen for new Kafka resources. Let’s create a simple Kafka cluster with external access configured, so we are able to connect from outside the OpenShift cluster.

Create the new my-cluster kafka Cluster with 3 zookeeper and 3 kafka nodes using ephemeral storage:
$ cat << EOF | oc create -f -
apiVersion: kafka.strimzi.io/v1alpha1
kind: Kafka
metadata: 
 name: my-cluster
spec:
 kafka:
   replicas: 3
   listeners:
     external:
       type: route
   storage:
     type: ephemeral
 zookeeper:
   replicas: 3
   storage:
     type: ephemeral
 entityOperator:
   topicOperator: {}
EOF

Wait a couple minutes, after that you will see the deployment of the Zookeeper and Kafka resources as well as the topic operator.

Now that our cluster is running, we can create a topic to publish and subscribe from our external client. Create the following my-topic Topic custom resource definition with 3 replicas and 3 partitions in my-cluster Kafka cluster:
$ cat << EOF | oc create -f -
apiVersion: kafka.strimzi.io/v1alpha1
kind: KafkaTopic
metadata:
 name: my-topic
 labels:
   strimzi.io/cluster: "my-cluster"
spec:
 partitions: 3
 replicas: 3
EOF
You are now ready to start sending and receiving messages.

Test using an external application

Switch to the camel-kafka-demo folder
$ cd kafka/camel-kafka-demo/

As we are using Routes for external access to the cluster, we need the CA certs to enable TLS in the client. Extract the public certificate of the broker certification authority
$ oc extract secret/my-cluster-cluster-ca-cert --keys=ca.crt --to=- > src/main/resources/ca.crt

creat client truststore and Import the trusted cert to a keystore
$ keytool -keystore src/main/resources/client.truststore.jks -alias CARoot -import -file src/main/resources/ca.crt
$ keytool -import -trustcacerts -alias root -file src/main/resources/ca.crt -keystore src/main/resources/client.truststore.jks -storepass password -noprompt


Now you can run the Fuse application using the maven command:
mvn spring-boot:run

with SSL DEBUG enabled 
mvn  spring-boot:run -Djavax.net.debug=ssl  >>/home/kkakarla/Downloads/ssl-debug.txt


<!--$ mvn -Drun.jvmArguments="-Dbootstrap.server=`oc get routes my-cluster-kafka-bootstrap -o=jsonpath='{.status.ingress[0].host}{"\n"}'`:443" clean package spring-boot:run -->


More steps:
 oc project my-project
 oc apply -f examples/templates/cluster-operator -n my-project
 oc adm policy add-cluster-role-to-user strimzi-cluster-operator-namespaced --serviceaccount strimzi-cluster-operator -n my-project
 oc adm policy add-cluster-role-to-user strimzi-entity-operator --serviceaccount strimzi-cluster-operator -n my-project
 oc adm policy add-cluster-role-to-user strimzi-topic-operator --serviceaccount strimzi-cluster-operator -n my-project
 oc apply -f install/cluster-operator -n my-project
 oc apply -f install/cluster-operator -n my-project
 oc apply -f examples/kafka/kafka-ephemeral.yaml
 oc apply -f kafkaTopic.yaml
 oc get pods;
 oc get pods -w


 oc run kafka-producer -ti --image=registry.access.redhat.com/amq7/amq-streams-kafka:1.1.0-kafka-2.1.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list cluster-name-kafka-bootstrap:9094 --topic my-topic

 oc run kafka-producer -ti --image=registry.access.redhat.com/amq7/amq-streams-kafka:1.1.0-kafka-2.1.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list my-cluster-kafka-bootstrap:9094 --topic my-topic

To access externally create route
oc apply -f my-cluster.yaml
'oc get routes' displays the host to be used as bootstrap url in client
ex: oc get routes my-cluster-kafka-bootstrap 





