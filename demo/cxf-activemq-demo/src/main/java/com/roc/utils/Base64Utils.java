package com.roc.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * BASE64编码解码
 * 
 * @author roc
 * 
 */
public class Base64Utils {

	public static final String CHARSET = "utf-8";
	private static BASE64Encoder encoder = null;
	private static BASE64Decoder decoder = null;

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public synchronized static String encode(String content) {
		if (null == content) {
			return null;
		}
		if (null == encoder) {
			encoder = new BASE64Encoder();
		}
		return encoder.encode(content.getBytes());
	}

	public synchronized static String encode(byte[] content) {
		if (null == content) {
			return null;
		}
		if (null == encoder) {
			encoder = new BASE64Encoder();
		}
		return encoder.encode(content);
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            需要解密的内容
	 * @return
	 * @throws IOException
	 */
	public synchronized static final String decodeToString(String content) {
		byte[] bytes = null;
		try {
			bytes = decode(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(bytes);
	}

	public synchronized static final byte[] decode(String content)
			throws IOException {
		if (null == content) {
			return null;
		}
		if (null == decoder) {
			decoder = new BASE64Decoder();
		}
		byte[] bytes = decoder.decodeBuffer(content);
		return bytes;
	}

	public static void main(String[] args) {
		String data = "{\"key\":\"中华人民共121fdd各国\",\"value\":\"dadfd\"}";
		String result = encode(data);
		System.out.println(result);
		result = decodeToString(result);
		System.out.println(result);
	}

}
