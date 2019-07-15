package org.apache.camel.example.kafka.avro;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.common.config.ConfigException;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.serializers.AbstractKafkaAvroDeserializer;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerializer;
import io.confluent.kafka.serializers.AvroSchemaUtils;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import okhttp3.*;

public class CustomKafkaAvroSerializer extends AbstractKafkaAvroSerializer  implements Serializer<Object> {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomKafkaAvroSerializer.class);
    private static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";
    private boolean useSpecificAvroReader = true;
    private boolean isKey = false;
	
	@Override
	public void close() {
		// TODO Auto-generated method stubs
		
	}


	@Override
	public byte[] serialize(String topic, Object record) {
		// TODO Auto-generated method stub
		 LOG.info("****************serialize*******************************");
		 LOG.info("Serialize method: topic " + topic);
		 LOG.info("Serialize method: byte " + record);
		 
		/* OkHttpClient client = new OkHttpClient();
		//SHOW ALL VERSIONS OF EMPLOYEE
		 Request  request = new Request.Builder()
	                .url("http://localhost:8081/subjects/employees-avro-value/versions/2")
	                .build();
		 String output = null;
		try {
			output = client.newCall(request).execute().body().string();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		LOG.info("Serialize method ouput is :" +output);*/
	
		
		 return serializeImpl(
			        getSubjectName(topic, isKey, record, AvroSchemaUtils.getSchema(record)), record);

		
		
	}
	

  @Override
  public void configure(KafkaAvroSerializerConfig config) {
	  LOG.info("ENTER CustomKafkaAvroDeserializer  : configure method ");
	  LOG.info("ENTER CustomKafkaAvroDeserializer  : SCHEMA_REGISTRY_URL " +SCHEMA_REGISTRY_URL);

    if (SCHEMA_REGISTRY_URL == null) {
      throw new org.apache.kafka.common.config.ConfigException("No schema registry provided");
    }
    try {

      final List<String> schemas = Collections.singletonList(SCHEMA_REGISTRY_URL);
      this.schemaRegistry = new CachedSchemaRegistryClient(schemas, Integer.MAX_VALUE);
      this.useSpecificAvroReader = true;

    } catch (ConfigException e) {
    	e.printStackTrace();
      throw new org.apache.kafka.common.config.ConfigException(e.getMessage());
    }
    LOG.info("EXIT CustomKafkaAvroserializer  : configure method ");

  }


@Override
public void configure(Map<String, ?> arg0, boolean arg1) {
    configure(null);
	
}
}