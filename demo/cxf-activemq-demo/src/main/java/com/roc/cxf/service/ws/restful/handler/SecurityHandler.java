package com.roc.cxf.service.ws.restful.handler;

import java.security.Principal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;

/**
 * 安全拦截器
 * --检测到达的访问是否已携带了必备校验信息
 * @author roc
 *
 */
@Provider
public class SecurityHandler implements RequestHandler{
	
	@Context
	private HttpHeaders headers;
	

	@Override
	public Response handleRequest(Message message, ClassResourceInfo resourceClass) {
		// TODO Auto-generated method stub
		SecurityContext securityContext = message.get(SecurityContext.class);
		if(securityContext != null){
			Principal principal = securityContext.getUserPrincipal();
			if(null != principal){
				String accountName = principal.getName();
			}
		}
		return null;
	}

}
