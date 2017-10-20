package com.fosun;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import org.apache.spark.sql.Dataset;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.catalyst.ScalaReflection.Schema;
import org.apache.spark.sql.types.StructType;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import com.fosun.tableObjects.*;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {


	public static final void main(String[] args) {
        try {
        	// spark session
        	SparkConf conf = new SparkConf();
        	
//        	SparkSession spark = SparkSession
//        			.builder()
//        			.master("local[2]")
//        			.appName("Drool Demo")
//        			.config(conf)
//        			.getOrCreate();

			SparkSession spark = SparkSession
			.builder()
			.appName("Drool Demo")
			.config(conf)
			.getOrCreate();

			JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        	
        	// configuration file
			Config config = ConfigFactory.load("app.conf");
			String sourcePath = config.getString("sourcePath");
			String targetPath = config.getString("targetPath");

//			sourcePath = "D:\\Documents\\CODE\\DroolsDemo\\src\\main\\resources\\yongan.snappy.parquet";
//			targetPath = "D:\\Documents\\CODE\\DroolsDemo\\src\\main\\resources\\result";
        	
        	// get file
			JavaRDD<Row> parFile = spark.read().parquet(sourcePath).javaRDD();


			// Broadcast rules
			KieBase rules = loadRules();
			Broadcast<KieBase> broadcastRules = sc.broadcast(rules);
			
            // go !         	
        	JavaRDD<CleanedPerson> resultRDD = parFile.mapPartitions(iterator->{
                // load up the knowledge base
            	KieSession kSession = broadcastRules.value().newKieSession();
            	
        		List<CleanedPerson> persons = new ArrayList<>();
        		while (iterator.hasNext()) {
        			Row row = iterator.next();
        			YongAn rawData = new YongAn(row);
        			kSession.insert(rawData);
    				persons.add(rawData.cleanedPerson);
        		}
        		kSession.startProcess("RF1", null);
        		kSession.fireAllRules();
        		kSession.dispose();
        		return persons.iterator();
				}
        	);

			Dataset<Row> cleanedPerDF = spark.createDataFrame(resultRDD, CleanedPerson.class);
			cleanedPerDF.repartition(1).write().mode("OverWrite").parquet(targetPath);
//			cleanedPerDF.show();


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static KieBase loadRules() {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		return kieContainer.getKieBase("rules");
	}
    
   
    
}
