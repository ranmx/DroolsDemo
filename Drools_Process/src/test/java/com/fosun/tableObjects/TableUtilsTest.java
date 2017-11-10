package com.fosun.tableObjects;

import java.io.IOException;
import org.junit.Test;

import com.fosun.tableObjects.*;


/**
 * Created by ranmx on 2017/10/26.
 */
public class TableUtilsTest {
    @org.junit.Test
    public void testGetClassNamePathFromPackage(){
        try{
            System.out.println(
              TableUtils.getClassNamePathFromPackage("com.fosun.tableObjects.rawTables"));
        }catch (IOException e) {
            System.out.println("io exception");
        }
    }
}
