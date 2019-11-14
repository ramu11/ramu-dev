# activemq-test

Downlaod Artemis https://activemq.apache.org/artemis/download.html

Extract and create a broker see https://activemq.apache.org/artemis/docs/latest/using-server.html

start both master/slave server0 and server1 brokers
Run broker using ../bin/artemis run

Run example using mvn spring-boot:run

To get rid of cluster timeout issues
sudo route add -net 224.0.0.0 netmask 240.0.0.0 dev lo



