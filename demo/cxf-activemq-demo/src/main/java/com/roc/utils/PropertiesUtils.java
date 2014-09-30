package com.roc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 * 属性文件操作工具类
 * @author roc
 *
 */
public class PropertiesUtils {

	/**
	 * 加载properties文件
	 * @param filePath
	 * @return
	 */
	public static Properties loadProperties(String filePath) {
		try {
			InputStream inputStream = new FileInputStream(filePath);
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把properties文件保存到指定路径 
	 * @param properties
	 * @param filePath
	 */
	public static void saveProperties(Properties properties, String filePath) {
		try {
			OutputStream outputStream = new FileOutputStream(filePath);
			properties.store(outputStream, "");
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 获取指定properties文件的相应的key的值
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getValue(Properties properties, String key) {
		if (properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		return null;
	}
	/**
	 * 把键值对添加或更新到properties文件
	 * @param properties
	 * @param key
	 * @param newValue
	 */
	public static void addOrUpdate(Properties properties, String key,
			String newValue) {
		if (properties.containsKey(key)) {
			properties.remove(key);
		}
		properties.put(key, newValue);
	}
}
