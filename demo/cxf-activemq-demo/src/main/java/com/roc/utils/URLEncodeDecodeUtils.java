package com.roc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeDecodeUtils {
	
	public static final String CHARSET = "utf-8";
	
	public static String encode(String content){
		try {
			return URLEncoder.encode(content, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String content){
		try {
			return URLDecoder.decode(content, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String data = "{\"key\":\"中华人民共121fdd各国\",\"value\":\"dadfd\"}";
		data = new String(data.getBytes("utf-8"), "utf-8");
		String encodeData = encode(data);
		System.out.println("EncodeData:" + encodeData);
		String decodeData = decode(encodeData);
		System.out.println("DecodeData:" + decodeData);
	}

}
