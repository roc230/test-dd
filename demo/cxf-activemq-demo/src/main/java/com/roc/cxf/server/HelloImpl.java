package com.roc.cxf.server;

public class HelloImpl implements IHello{

	@Override
	public String sayHello(String message) {
		return "Saying: " + message;
	}

}
