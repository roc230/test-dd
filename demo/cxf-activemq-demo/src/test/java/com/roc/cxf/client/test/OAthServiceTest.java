package com.roc.cxf.client.test;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.form.Form;
import org.junit.Test;

import com.roc.utils.InputStreamUtils;

public class OAthServiceTest {
	
	@Test
	public void test_register() throws Exception{
		String regiesterURI = "http://localhost:8090/cxf-activemq-demo/services/oauth2/register";
		
		String appName = "thirdPartyApp";
		String appURI = "http://localhost:8090/cxf-activemq-demo/services/oauth2/account";
		String appRedirectURI = "http://localhost:8090/cxf-activemq-demo/services/oauth2/account/getById";
		
		WebClient webClient = WebClient.create(regiesterURI);
		WebClient.getConfig(webClient).getHttpConduit().getClient().setReceiveTimeout(10000000L);
		
		Form form = new Form().set("appName", appName)
								.set("appURI", appURI)
									.set("appRedirectURI", appRedirectURI);
		Response response = webClient.form(form);
		InputStream inputStream = (InputStream)response.getEntity();
		String result = InputStreamUtils.read2String(inputStream);
		System.out.println(result);
	}
	
	@Test
	public void test_userAuth(){
		String userAuthURI = "http://localhost:8090/cxf-activemq-demo/services/oauth2/userAuth";
		
		String loginName = "roc";
		String password = "234";
		
		WebClient webClient = WebClient.create(userAuthURI);
		WebClient.getConfig(webClient).getHttpConduit().getClient().setReceiveTimeout(10000000L);
		
		Form form = new Form().set("loginName", loginName)
				.set("password", password);
		
		Response response = webClient.form(form);
		InputStream inputStream = (InputStream)response.getEntity();
		String result = InputStreamUtils.read2String(inputStream);
		System.out.println(result);
		
	}
	
	@Test
	public void test_thirdPartyCallService(){
		String accountURI = "http://localhost:8090/cxf-activemq-demo/services/thirdParty/accounts";
		String loginName = "roc";
		String password = "234";
		WebClient webClient = this.createClient(accountURI, loginName, password);
		Response response = webClient.form(new Form().set("id", "1"));
	}
	
	private WebClient createClient(String address, String userName, String password) {
    	JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
    	bean.setAddress(address);
    	bean.setUsername(userName);
    	bean.setPassword(password);
    	
    	bean.getOutInterceptors().add(new LoggingOutInterceptor());
    	bean.getInInterceptors().add(new LoggingInInterceptor());
    	
    	return bean.createWebClient();
    }

}
