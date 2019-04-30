/**
 * Topic: Producer writes a record on a topic and the consumer listens to it. A topic can have many partitions but must have at least one.

Partition: A topic partition is a unit of parallelism in Kafka, i.e. two consumers cannot consume messages from the same partition 
           at the same time,A consumer can consume from multiple partitions at the same time.

Offset: A record in a partition has an offset associated with it. Think of it like this: partition is like an array; 
        offsets are like indexs.
      
        In below the Partitioner class, I have overridden the method partition which returns the partition number in which the record will go.*/
       
 

package com.redhat.kafka.kafka_clients.partitioner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class CustomPartitioner implements Partitioner {

   
    private static Map<String,Integer> countryToPartitionMap;

    public void configure(Map<String, ?> configs) {
        
        countryToPartitionMap = new HashMap<String, Integer>();
        for(Map.Entry<String,?> entry: configs.entrySet()){
            if(entry.getKey().startsWith("partitions.")){
                String keyName = entry.getKey();
                String value = (String)entry.getValue();
                System.out.println( keyName.substring(11));
                int paritionId = Integer.parseInt(keyName.substring(11));
                countryToPartitionMap.put(value,paritionId);
            }
        }
    }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List partitions = cluster.availablePartitionsForTopic(topic);
        String valueStr = (String)value;
        String countryName = ((String) value).split(":")[0];
        if(countryToPartitionMap.containsKey(countryName)){
            
            System.out.println("partition no  is:" +countryToPartitionMap.get(countryName));
            
            //If the country is mapped to particular partition return it
            return countryToPartitionMap.get(countryName);
        }else {
            //If no country is mapped to particular partition distribute between remaining partitions
            int noOfPartitions = cluster.topics().size();
           
            System.out.println("partition no  is:" +value.hashCode()%noOfPartitions + countryToPartitionMap.size());
            return  value.hashCode()%noOfPartitions + countryToPartitionMap.size() ;
        }
    }

    public void close() {
    }
}
