package com.fosun;

import org.junit.Test;

/**
 * Created by ranmx on 2017/10/31.
 */


public class ConfigTest {
    Config conf = new Config("appTest", "YongAn");

    @Test
    public void printConf(){
        System.out.println(conf.getKieBase());
        System.out.println(conf.getKieProcess());
        System.out.println(conf.getSourcePath());
        System.out.println(conf.getTargetPath());
    }
}
