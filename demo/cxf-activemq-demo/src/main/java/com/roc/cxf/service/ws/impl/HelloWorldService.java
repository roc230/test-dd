package com.roc.cxf.service.ws.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.roc.cxf.service.ws.IHelloWorldService;

@Service
@WebService(serviceName="helloService",endpointInterface = "com.roc.cxf.service.ws.IHelloWorldService")
public class HelloWorldService implements IHelloWorldService{

	@Override
	public String sayHi(String content) {
		System.out.println("sayHi called");
		return "Hello " + content;
	}

}
