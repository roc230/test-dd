package com.roc.cxf.service.ws.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class TestInInterceptor extends AbstractSuperSoapInterceptor{

	public TestInInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		logger.info("测试拦截器开始工作...");
		printSoapMessageInfo(message);
	}

}
