package com.fosun.tableObjects;

import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import com.google.common.reflect.ClassPath;


public class TableUtils {

	private static ImmutableSet<ClassPath.ClassInfo> getClassesFromPackage(String packagePath)
			throws IOException {
		// in order to load the class lazily, google forces the class to be loaded in this way
		// rather than just instantiate it.
		return ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(packagePath);
	}

    public static List<String> getClassNamesFromPackage(String packagePath) throws IOException{
		ImmutableSet<ClassPath.ClassInfo> classInfoSet = getClassesFromPackage(packagePath);
		List<String> classNames = new ArrayList<>();
		for (ClassPath.ClassInfo clazz : classInfoSet) {
			classNames.add(clazz.toString());
		}
		return classNames;
	}

	public static HashMap<String, String> getClassNamePathFromPackage(String packagePath)
      throws IOException {
	    HashMap<String, String> namePath = new HashMap<String, String>();
        List<String> names = getClassNamesFromPackage(packagePath);
        for (String name: names) {
            String[] paths = name.split("\\.");
            namePath.put(paths[paths.length-1], name);
        }
        return namePath;
    }
}
