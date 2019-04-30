package com.redhat.kafka.kafka_clients.constants;

public interface IKafkaConstants {
    
    
    public static String KAFKA_BROKERS = "localhost:9092,localhost:9093,localhost:9094";
    public static Integer MESSAGE_COUNT=5;
    public static String CLIENT_ID="client1";
  //  public static String TOPIC_NAME="demo-3";
    public static String TOPIC_NAME="test-topic";
    public static String GROUP_ID_CONFIG="consumerGroup1";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=5;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
    public static Integer MAX_POLL_RECORDS=1;

}
