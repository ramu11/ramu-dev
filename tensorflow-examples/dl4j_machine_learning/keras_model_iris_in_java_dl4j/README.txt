Start zookeeper:  ./bin/zkServer.sh start  
Start kafka (3 node cluster)       : 
./kafka-server-start.sh  /home/kkakarla/development/soft/kafka/kafka_2.12-1.1.0/config/server.properties

./kafka-server-start.sh  /home/kkakarla/development/soft/kafka/kafka_2.12-1.1.0/config/server-one.properties

./kafka-server-start.sh  /home/kkakarla/development/soft/kafka/kafka_2.12-1.1.0/config/server-two.properties


./kafka-topics.sh --zookeeper localhost:2181 --create --topic irisInputTopic --partitions 3 --replication-factor 3

./kafka-console-producer.sh --broker-list localhost:9092 --topic irisInputTopic 

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic irisInputTopic --from-beginning --partition 0

./kafka-topics.sh --zookeeper localhost:2181 --delete --topic irisInputTopic


