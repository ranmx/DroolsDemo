package com.fosun.tableObjects;

import java.io.Serializable;

/**
 * Created by ranmx on 2017/11/3.
 */
public abstract class TargetTable implements Serializable {
    private String className = this.getClass().getName();

    public String getClassName() {
        return className;
    }
}
