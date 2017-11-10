package com.fosun;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;


/**
 * Created by ranmx on 2017/10/30.
 */
public class DroolsCleanTest {
    static final private Config config = new Config("appTest.conf", "YongAn");
    static final private SparkSession spark = getSparkContext();

    @Test
    public void testKiebase(){
        KieBase kieBase = DroolsClean.loadRules(config.getKieBase());

        System.out.println(kieBase.getProcesses()
        );
    }

    @Test
    public void testKieRun(){
        KieBase kieBase = DroolsClean.loadRules(config.getKieBase());
        KieSession kSession = kieBase.newKieSession();
        kSession.startProcess(config.getKieProcess(), null);
        kSession.fireAllRules();
        kSession.dispose();
    }

    @Test
    public void testRules(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-testRule1");

        // start a new process instance
        kSession.startProcess("testRule1");
    }


    private static SparkSession getSparkContext() {
        SparkConf conf = new SparkConf();
        return SparkSession
          .builder()
          .master("local[2]")
          .appName("Drool Demo")
          .config(conf)
          .getOrCreate();
    }
}
