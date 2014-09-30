package com.roc.cxf.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.roc.cxf.server.IHello;



public class HelloClient {
	private static final String addr = "http://localhost:8080/cxf-demo/services/Hello";
	
	public static void main(String[]args){
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.getInInterceptors().add(new LoggingInInterceptor());
		proxyFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		proxyFactory.setServiceClass(IHello.class);
		proxyFactory.setAddress(addr);
		
		IHello hello = (IHello)proxyFactory.create();
		String reply = hello.sayHello("hello roc");
		System.out.println(reply);
	}
}
