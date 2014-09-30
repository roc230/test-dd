package com.roc.cxf.service.ws.oauth.thirdparty;

import org.apache.cxf.jaxrs.client.WebClient;

public class ClientOAuthDataManager {
	//注入访问/token的客户端
	private WebClient accessTokenService;
	//注入访问/authorize的地址
	private String authorizationServiceURI = "";
	
	
	
	public void setAccessTokenService(WebClient accessTokenService) {
		this.accessTokenService = accessTokenService;
	}

	public void setAuthorizationServiceURI(String authorizationServiceURI) {
		this.authorizationServiceURI = authorizationServiceURI;
	}
}
