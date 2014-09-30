package com.roc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 输入流工具类
 * @author roc
 *
 */
public class InputStreamUtils {
	/**
	 * 把输入流转换成字符串
	 * @param inputStream 输入流
	 * @return String
	 */
	public static String read2String(InputStream inputStream){
		if(null == inputStream){
			throw new NullPointerException("参数[inputStream]不能为null");
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 把字符串内容转换成输入流
	 * @param content 内容 
	 * @return InputStream
	 */
	public static InputStream read2InputStream(String content){
		if(null == content){
			throw new NullPointerException("参数[content]不能为null");
		}
		InputStream inputStream = new ByteArrayInputStream(content.getBytes());
		return inputStream;
	}
}
