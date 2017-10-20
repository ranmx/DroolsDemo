package com.fosun;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.spark.sql.catalyst.ScalaReflection.Schema;
import org.apache.spark.sql.types.StructType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.apache.spark.sql.Dataset;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.Encoders;

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
        	
        	SparkSession spark = SparkSession
        			.builder()
        			.master("local[2]")
        			.appName("Drool Demo")
        			.config(conf)
        			.getOrCreate();
        	
        	// configuration file
			Config config = ConfigFactory.load("app.conf");
			String sourcePath = config.getString("sourcePath");
			String targetPath = config.getString("targetPath");
        	
        	// get file
			JavaRDD<Row> parFile = spark.read().parquet(sourcePath).javaRDD();
			
            // go !         	
        	JavaRDD<CleanedPerson> resultRDD = parFile.mapPartitions(iterator->{
                // load up the knowledge base
    	        KieServices ks = KieServices.Factory.get();
        	    KieContainer kContainer = ks.getKieClasspathContainer();
            	KieSession kSession = kContainer.newKieSession("ksession-rules");
            	
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


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
   
    
}
