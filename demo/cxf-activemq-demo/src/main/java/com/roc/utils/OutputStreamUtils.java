package com.roc.utils;

import java.io.OutputStream;

/**
 * 输出流操作工具类
 * @author roc
 *
 */
public class OutputStreamUtils {

	/**
	 * 把输出流转换成字符串
	 * @param outputStream 输出流
	 * @return String
	 */
	public static String toString(OutputStream outputStream){
		if(null == outputStream){
			throw new NullPointerException("参数[outputStream]不能为null");
		}
		return outputStream.toString();
	}
}
