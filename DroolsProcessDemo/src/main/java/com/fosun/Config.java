package com.fosun;

import com.typesafe.config.ConfigFactory;

/**
 * Created by ranmx on 2017/10/30.
 */
public class Config {
    static private com.typesafe.config.Config config = ConfigFactory.load("app.conf");
    private String sourcePath = null;
    private String targetPath = null;
    private String kieProcess = null;
    private String KieBase = null;

    public Config(String confName, String tableName) {
        com.typesafe.config.Config config = ConfigFactory.load(confName);
        sourcePath = config.getString(tableName + ".sourcePath");
        targetPath = config.getString(tableName + ".targetPath");
        kieProcess = config.getString(tableName + ".kieProcess");
        KieBase = config.getString(tableName + ".kieBase");
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getKieProcess() {
        return kieProcess;
    }

    public String getKieBase() {
        return KieBase;
    }
}
