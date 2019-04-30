package com.redhat.kafka.kafka_clients;

public class TestUtil {

	public static void main(String[] args) {
		
		 int numPartitions = 3;
         int sp = (int)Math.abs(numPartitions*0.03);
         int p=0;
         
         System.out.println(sp);

	}

}
