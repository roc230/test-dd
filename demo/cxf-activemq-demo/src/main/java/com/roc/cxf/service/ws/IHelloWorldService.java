package com.roc.cxf.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface IHelloWorldService {
	@WebMethod(operationName = "sayHi")
	@WebResult(name = "sayHiResult")
	public String sayHi(@WebParam(name = "content") String content);
}
