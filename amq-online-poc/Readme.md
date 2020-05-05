
 oc login -u system:admin
 
 sed -i 's/amq-online-infra/my-namespace/' install/bundles/enmasse-with-standard-authservice/*.yaml

 oc new-project amq-online-infra

 oc apply -f install/bundles/amq-online

 oc apply -f install/components/example-authservices/standard-authservice.yaml (For authentication)
 OR
 oc apply -f install/components/example-authservices/none-authservice.yaml

 oc apply -f standard-service-config.yaml

 oc apply -f standard-addressspace.yaml

 oc apply -f standard-address.yaml

 oc get addressspaceschema standard -o yaml

 oc get addressspace myspace -o yaml
 >>------
   serviceHost: messaging-d4f4441.amq-online-infra.svc
   externalHost: messaging-wss-d4f4441-amq-online-infra.apps.rhmiamqonline.lab.pnq2.cee.redhat.com

 oc get pv
 NAME                                       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM                                         STORAGECLASS        REASON   AGE
 pvc-94833653-8e8b-11ea-a5d1-fa163ef63531   2Gi        RWO            Delete           Bound    amq-online-infra/data-broker-d4f4441-gcnh-0   glusterfs-storage            22m
 oc get pvc
 NAME                         STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS        AGE
 data-broker-d4f4441-gcnh-0   Bound    pvc-94833653-8e8b-11ea-a5d1-fa163ef63531   2Gi        RWO            glusterfs-storage   22m

 oc get pv pvc-94833653-8e8b-11ea-a5d1-fa163ef63531 -o yaml
 oc get pvc data-broker-d4f4441-gcnh-0 -o yaml



 oc exec  broker-d4f4441-gcnh-0 -i -t -- bash

 bash-4.2$ cd /opt/amq/bin
 bash-4.2$ ./artemis queue stat
 OpenJDK 64-Bit Server VM warning: If the number of processors is expected to increase from one, then you should configure the number of parallel GC threads appropriately using -XX:ParallelGCThreads=N

 --user: is a mandatory property!
 Type the username for a retry
 amq

 --password: is mandatory with this configuration:
 Type the password for a retry

 |NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
 |!!GLOBAL_DLQ             |!!GLOBAL_DLQ             |1              |0             |0              |0                |0              |0               |ANYCAST      |
 |activemq.management      |activemq.management      |0              |0             |0              |0                |0              |0               |ANYCAST      |
 |activemq.management.eb2cd977-fe63-4fff-88b4-d55494ed5ec5|activemq.management.eb2cd977-fe63-4fff-88b4-d55494ed5ec5|1              |0             |0              |0                |0              | 0               |MULTICAST    |
 |activemq.management.tmpreply.bb926b19-cd0e-8d4f-b60c-977fceeee1b8|activemq.management.tmpreply.bb926b19-cd0e-8d4f-b60c-977fceeee1b8|1              |0             |274            |0                |274            |0               |ANYCAST      |
 |activemq.management.tmpreply.ce8b10d6-c2d1-f942-bd75-47304c7c581c|activemq.management.tmpreply.ce8b10d6-c2d1-f942-bd75-47304c7c581c|1              |0             |5              |0                |5              |0               |ANYCAST      |
 |myqueue                  |myqueue                  |1              |0             |0              |0                |0              |0               |ANYCAST      |

 
./artemis producer --url tcp://messaging-d4f4441.amq-online-infra.svc:5672 --user "amq" --password "amq" --destination myqueue --message-count 5 --protocol amqp

./artemis consumer --url tcp://messaging-d4f4441.amq-online-infra.svc:5672 --user "amq" --password "amq" --destination myqueue --protocol amqp

 
oc logs -f qdrouterd-d4f4441-0

https://github.com/fabric8-quickstarts/spring-boot-camel-amq/tree/master/src/main
 

