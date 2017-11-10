package com.fosun;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;


import java.util.Map;
import org.apache.hadoop.util.hash.Hash;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
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

import com.fosun.tableObjects.*;
import com.fosun.tableObjects.targetTables.CleanedPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsClean {

    static private Logger logger = LoggerFactory.getLogger(DroolsClean.class);
    static private SparkSession spark = getSparkContext();
    static private Config config = null;

	public static void main(String[] args) {
	    String ConfigFile = "appTest.conf";

        try {
            // get raw table name and check
            if (args.length !=1 ){
                throw new IllegalArgumentException("must have only one table name as argument");
            }
            String tableName = args[0];

            // spark session
            JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

            // get path from configuration file
            config = new com.fosun.Config(ConfigFile, tableName);

            // get file
            JavaRDD<Row> parFile = spark.read().parquet(config.getSourcePath()).javaRDD();

            // Broadcast rules
            KieBase rules = loadRules(config.getKieBase());
            Broadcast<KieBase> broadcastRules = sc.broadcast(rules);

            // go !

            JavaPairRDD<String, TargetTable> resultPair = parFile.mapPartitionsToPair(iterator->{
                  // load up the knowledge base
                  KieSession kSession = broadcastRules.value().newKieSession();

                  ArrayList<Tuple2<String, TargetTable>> targetTableMaps = new ArrayList<>();
                  while (iterator.hasNext()) {
                      Row row = iterator.next();
                      RawTable rawData = getRawTableFromName(tableName);
                      rawData.extractRow(row);
                      kSession.insert(rawData);
                      addTargetTablesInMap(targetTableMaps, rawData);
                  }
                  kSession.startProcess(config.getKieProcess(), null);
                  kSession.fireAllRules();
                  kSession.dispose();
                  return targetTableMaps.iterator();
              }
            );

            saveResultByClass(resultPair);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static KieBase loadRules(String kieBaseName) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		return kieContainer.getKieBase(kieBaseName);
	}

	private static RawTable getRawTableFromName(String name)
      throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        String path = getTablePathFromName(name);
        return (RawTable) Class.forName(path).newInstance();
    }

    private static SparkSession getSparkContext() {
        SparkConf conf = new SparkConf();
        return SparkSession
          .builder()
          .config(conf)
          .getOrCreate();
    }

    private static String getTablePathFromName (String name) throws IOException {
        String rawTablePath = "com.fosun.tableObjects.rawTables";
        HashMap<String, String> tableMap = TableUtils.getClassNamePathFromPackage(rawTablePath);
        String path = tableMap.get(name);
        if (path == null) {
            throw new IOException("Table not exists:" + name);
        }
        return path;
    }

    private static void addTargetTablesInMap (ArrayList<Tuple2<String, TargetTable>> tableMap, RawTable rawTable) {
        for (TargetTable targetTable: rawTable.getTargetTables()) {
            Tuple2<String, TargetTable> targetTableTuple2 = new Tuple2<>(targetTable.getClassName(), targetTable);
            tableMap.add(targetTableTuple2);
        }
    }

    private static void saveResultByClass (JavaPairRDD<String, TargetTable> resultPairRdd)
      throws ClassNotFoundException{
        resultPairRdd.persist(StorageLevel.MEMORY_ONLY());
        List<String> keys = resultPairRdd.keys().distinct().collect();
        for (String key: keys) {
            JavaRDD<TargetTable> targetTableJavaRDD =
              resultPairRdd.filter(kv->kv._1.equals(key)).values();
            Class<?> targetTableClass = Class.forName(key);
            Dataset<Row> cleanedPerDF = spark.createDataFrame(targetTableJavaRDD, targetTableClass);
            cleanedPerDF.repartition(1).write().mode("OverWrite")
              .parquet(config.getTargetPath() + '_' + key);
        }
    }
}
