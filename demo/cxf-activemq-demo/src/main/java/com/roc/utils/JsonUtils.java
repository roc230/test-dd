package com.roc.utils;


import java.lang.reflect.Type;

import com.google.gson.Gson;
/**
 * GSON工具类
 * @author roc
 *
 */
public class JsonUtils {
	/**
	 * 把数据对象转换成JSON字符串
	 * @param obj 数据对象
	 * @return JSON字符串
	 */
	public static <T> String toJsonString(T obj){
		return new Gson().toJson(obj);
	}
	/**
	 * 把JSON字符串转换成指定类型的数据对象
	 * @param jsonStr JSON字符串
	 * @param clazz 数据对象类型
	 * @return 数据对象
	 */
	public static <T> T fromJson(String jsonStr, Class<T> clazz){
		return new Gson().fromJson(jsonStr, clazz);
	}
	/**
	 * 把JSON字符串转换成指定类型的数据对象
	 * @param jsonStr JSON字符串
	 * @param type 数据对象类型,例如: Type type = new TypeToken<List<String>>(){}.getType();
	 * @return
	 */
	@SuppressWarnings("unchecked")  
	public static <T> T fromJson(String jsonStr, Type type){
		return (T) new Gson().fromJson(jsonStr, type);
	}

}
