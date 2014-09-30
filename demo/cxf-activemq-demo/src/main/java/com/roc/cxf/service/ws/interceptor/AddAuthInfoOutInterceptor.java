package com.roc.cxf.service.ws.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxrs.interceptor.JAXRSOutInterceptor;
import org.apache.cxf.message.Message;

public class AddAuthInfoOutInterceptor extends JAXRSOutInterceptor{

	public AddAuthInfoOutInterceptor(){
	}
	
	@Override
	public void handleMessage(Message message) {
		System.out.println("AddAuthInfoOutInterceptor 开始执行...");
		//测试，往消息头部加入数据
		Map<String,List<String>> headers = (Map<String,List<String>>)message.get(Message.PROTOCOL_HEADERS);
		List<String> strList = new ArrayList<String>();
		strList.add("aaa");
		strList.add("123");
		headers.put("Authorization", strList);
		message.put(Message.PROTOCOL_HEADERS, headers);
	}
	

}
