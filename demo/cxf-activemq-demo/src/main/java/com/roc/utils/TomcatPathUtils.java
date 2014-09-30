package com.roc.utils;

public class TomcatPathUtils {
	
	public static String getBinPath(){
		return System.getProperty("user.dir");
	}

	public static String getTomcatRoot(){
		String binPath = getBinPath();
		return binPath.substring(0, binPath.lastIndexOf("/"));
	}
	
	public static String getWebappsPath(){
		return getTomcatRoot() + "webapps/";
	}
	
	public static String getProjectPath(){
		return new Object().getClass().getResource("/").getPath();
	}
}
