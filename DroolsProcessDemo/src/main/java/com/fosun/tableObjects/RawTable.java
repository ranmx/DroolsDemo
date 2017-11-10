package com.fosun.tableObjects;

import com.fosun.tableObjects.targetTables.FosunDB;
import java.io.Serializable;

import java.util.ArrayList;
import org.apache.spark.sql.Row;

import com.fosun.tableObjects.targetTables.CleanedPerson;

/**
 * Created by ranmx on 2017/10/25.
 */
public abstract  class RawTable implements Serializable{

    private CleanedPerson cleanedPerson = new CleanedPerson();

    public CleanedPerson getCleanedPerson(){ return cleanedPerson; }

    private FosunDB fosunDB = new FosunDB();

    public FosunDB getFosunDB() {return  fosunDB;}


    public abstract void extractRow(Row row);

    public ArrayList<TargetTable> getTargetTables() {
        ArrayList<TargetTable> targetTables = new ArrayList<>();
        targetTables.add(cleanedPerson);
        targetTables.add(fosunDB);
        return targetTables;
    }
   
}
