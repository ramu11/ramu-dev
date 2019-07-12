package org.apache.camel.example.kafka.avro;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.confluent.common.config.ConfigException;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.serializers.AbstractKafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

import org.apache.kafka.common.serialization.Deserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomKafkaAvroDeserializer extends AbstractKafkaAvroDeserializer  implements Deserializer<Object> {
	private static final Logger LOG = LoggerFactory.getLogger(CustomKafkaAvroDeserializer.class);
    private static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";

  @Override
  public void configure(KafkaAvroDeserializerConfig config) {
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
    LOG.info("EXIT CustomKafkaAvroDeserializer  : configure method ");

  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    configure(null);
  }

  @Override
  public Object deserialize(String s, byte[] bytes) {
	    LOG.info("ENTER CustomKafkaAvroDeserializer  : deserialize method ");
		return deserialize(bytes).toString();
  }
  
  @Override
  public void close() {
  }
}
