package com.fosun;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.apache.spark.sql.Dataset;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.RowFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import com.fosun.tableObjects.*;

import scala.Function1;

import cc.shanruifeng.functions.card.*;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static Iterator<Row> ruleExecutor(Iterator<Row> iter) {
	    	return iter;
	    }

	public static final void main(String[] args) {
        try {
//        	// set windows compatible parameter
//        	System.setProperty("hadoop.home.dir", "D:\\Softwares\\hadoop");
        	
        	// spark session
        	SparkConf conf = new SparkConf();
        	
//        	SparkSession spark = SparkSession
//        			.builder()
//        			.master("local[2]")
//        			.appName("Drool Demo")
//        			.config(conf)
//					.enableHiveSupport()
//        			.getOrCreate();

			SparkSession spark = SparkSession
				.builder()
				.appName("Drool Demo")
				.config(conf)
				.enableHiveSupport()
				.getOrCreate();
        	
        	// configuration file
        	Config config = ConfigFactory.load("app.conf");
        	String sourcePath = config.getString("sourcePath");
        	String targetPath = config.getString("targetPath");
        	
        	// register UDF
        	spark.sql("create temporary function id_card_sex as 'cc.shanruifeng.functions.card.UDFChinaIdCardGender'");
        	
        	// get file
			Dataset<Row> rawdata = spark.read().parquet(sourcePath);
//        	Dataset<Row> rawdata = spark.read().csv("starM.csv");

			// use UDF
			rawdata.createOrReplaceTempView("starM");
			Dataset<Row> udfModified = spark.sql("SELECT *, id_card_sex('652423195410291173') as id_sex FROM starM");
			JavaRDD<Row> parFile = udfModified.javaRDD();
			
            // go !         	
        	JavaRDD<CleanedPerson> resultRDD = parFile.mapPartitions(iterator->{
                // load up the knowledge base
    	        KieServices ks = KieServices.Factory.get();
        	    KieContainer kContainer = ks.getKieClasspathContainer();
            	KieSession kSession = kContainer.newKieSession("ksession-rules");
            	
        		List<CleanedPerson> persons = new ArrayList<CleanedPerson>();
        		List<Row> rows = new ArrayList<Row>();
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
        	Dataset cleanedPerDF = spark.createDataFrame(resultRDD, CleanedPerson.class);
        	cleanedPerDF.write().mode("OverWrite").parquet(targetPath);
        			
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
   
    
}
