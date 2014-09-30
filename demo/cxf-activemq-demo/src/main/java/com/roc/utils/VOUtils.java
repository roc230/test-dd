package com.roc.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;



public class VOUtils {
	
	/**
	 * 根据属性名称获取值对象的相应属性值
	 * @param vo 值对象
	 * @param propertyName 属性名称</br>
	 * --可以是值对象的属性名称，如“属性名”</br>
	 * --也可以用于值对象中存在嵌套对象的情况，如"属性名.属性名"
	 * @return 属性值
	 */
	public static Object getPropertyValue(Object vo, String propertyName){
		if(null == vo){
			throw new NullPointerException("方法参数[vo]不能为null!");
		}
		if(StringUtils.isEmpty(propertyName)){
			throw new NullPointerException("方法参数[propertyName]不能为空!");
		}
		try {
			if(propertyName.contains(".")){
				return PropertyUtils.getNestedProperty(vo, propertyName);
			}else{
				return PropertyUtils.getSimpleProperty(vo, propertyName);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 访问数组或List型内Object的属性
	 * @param vo 值对象
	 * @param propertyName 数组或List型内Object的属性名称
	 * @param index 数组或List型内Object的下标
	 * @return propertyName[index]的值
	 */
	public static Object getIndexedPropertyValue(Object vo, String propertyName, int index){
		if(null == vo){
			throw new NullPointerException("方法参数[vo]不能为null!");
		}
		if(StringUtils.isEmpty(propertyName)){
			throw new NullPointerException("方法参数[propertyName]不能为空!");
		}
		try {
			return PropertyUtils.getIndexedProperty(vo, propertyName, index);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 访问Map型bean属性的值
	 * @param vo 值对象
	 * @param propertyName 属性名称
	 * @param mapKey Map型bean属性的key
	 * @return Map型bean属性的值
	 */
	public static Object getMappedPropertyValue(Object vo, String propertyName, String mapKey){
		if(null == vo){
			throw new NullPointerException("方法参数[vo]不能为null!");
		}
		if(StringUtils.isEmpty(propertyName)){
			throw new NullPointerException("方法参数[propertyName]不能为空!");
		}
		if(StringUtils.isEmpty(mapKey)){
			throw new NullPointerException("方法参数[mapKey]不能为空!");
		}
		try {
			return PropertyUtils.getMappedProperty(vo, propertyName, mapKey);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取属性类型
	 * @param vo 值对象
	 * @param propertyName 属性名称
	 * @return 属性类型class
	 */
	public static Class<?> getPropertyType(Object vo, String propertyName){
		if(null == vo){
			throw new NullPointerException("方法参数[vo]不能为null!");
		}
		if(StringUtils.isEmpty(propertyName)){
			throw new NullPointerException("方法参数[propertyName]不能为空!");
		}
		try {
			return PropertyUtils.getPropertyType(vo, propertyName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建包含Bean属性的Map
	 * @param vo
	 * @return
	 */
	public static Map<String, Object> describe(Object vo){
		if(null == vo){
			throw new NullPointerException("方法参数[vo]不能为null!");
		}
		try {
			return PropertyUtils.describe(vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		UserVO user = new UserVO();
		user.setName("roc");
		
		ResultVO<UserVO> result = new ResultVO<UserVO>();
		result.setResult(user);
		
		System.out.println(VOUtils.getPropertyValue(user, "name"));
		System.out.print(VOUtils.getPropertyValue(result, "result.name"));
	}

}
