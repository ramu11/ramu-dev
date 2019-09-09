package com.redhat;

import java.io.File;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportKerasConfigForIris {
	
	private static Logger log = LoggerFactory.getLogger(ImportKerasConfigForIris.class);
	
	 public static void main(String[] args) throws Exception {
		 
		 	  
		  
		  MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights
				  ("/home/kkakarla/development/git/ramu-dev/tensorflow-examples/Iris_dataset_deep_learning_example/model_iris.json", 
						  "/home/kkakarla/development/git/ramu-dev/tensorflow-examples/Iris_dataset_deep_learning_example/model_iris.h5" );

		  
		  int numLinesToSkip = 0;
		  String delimiter = ",";
		  RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
		  File file = new File("iris_test.csv");
		  System.out.println("file" +file.getAbsolutePath());
		  recordReader.initialize(new FileSplit(file));
		  // label index
		  int labelIndex = 4;
		  //num of classes
		  int numClasses = 3;
		  //batchSize  all
		  int batchSize = 150;
		  
		  DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
		  
		  DataSet allData = iterator.next();
		  allData.shuffle();
		  
		  
		  //Have our model,we have our record reader to read data
		  //Evaluate the model
		  
		  Evaluation eval = new Evaluation(3);
		  INDArray output = model.output(allData.getFeatures());
		  eval.eval(allData.getLabels(),output);
		  log.info(eval.stats());
		  System.out.println(eval.stats());
		  
		  
		  
		  
		  
	 }

}
