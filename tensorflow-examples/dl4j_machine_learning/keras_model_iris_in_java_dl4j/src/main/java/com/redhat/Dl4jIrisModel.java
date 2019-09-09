package com.redhat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Dl4jIrisModel {
    
    // Prediction Value
    private static String irisPrediction = "unknown";

    public static void main(String[] args) throws Exception{
        String inputTopic = "irisInputTopic";
        String outputTopic = "irisOutputTopic";


        // Iris input data (the model returns probabilities for input being each of Iris
        // Type 1, 2 and 3)
        List<String> inputValues = Arrays.asList("5.4,3.9,1.7,0.4", "7.0,3.2,4.7,1.4", "4.6,3.4,1.4,0.3");

        
            MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(
                    "/home/kkakarla/development/git/ramu-dev/tensorflow-examples/Iris_dataset_deep_learning_example/model_iris.json",
                    "/home/kkakarla/development/git/ramu-dev/tensorflow-examples/Iris_dataset_deep_learning_example/model_iris.h5");
       

        final Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-dl4j-iris-prdiction");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
       // props.put(StreamsConfig.STATE_DIR_CONFIG, "state-store1");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        
        StreamsBuilder builder = new StreamsBuilder();

        
            final KStream<String, String> irisInputLines = builder.stream(inputTopic);

          /*  KStream<String, String> KS1 = irisInputLines
                    .flatMapValues(value -> Arrays.asList(value.toLowerCase().split(",")));

            KGroupedStream<String, String> KGS2 = KS1.groupBy((key, value) -> value);

            KTable<String, Long> KTS3 = KGS2.count();

            KTS3.toStream().foreach((k, v) -> System.out.println("Key = " + k + " Value = " + v.toString()));*/
             
           

        // Stream Processor (in this case 'foreach' to add custom logic, i.e. apply the
        // analytic model)
        
        irisInputLines.foreach((key, value) -> {
          
                System.out.println("#####################");
                System.out.println("Iris Input:" + value);

                // TODO Easier way to map from String[] to double[] !!!
                String[] stringArray = value.split(",");
                Double[] doubleArray = Arrays.stream(stringArray).map(Double::valueOf).toArray(Double[]::new);
                double[] irisInput = Stream.of(doubleArray).mapToDouble(Double::doubleValue).toArray();

                // Inference
                INDArray input = Nd4j.create(irisInput);
                INDArray result = model.output(input);

                System.out.println("Probabilities: " + result.toString());

                irisPrediction = result.toString();

            

        });
        
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
         

    }

}
