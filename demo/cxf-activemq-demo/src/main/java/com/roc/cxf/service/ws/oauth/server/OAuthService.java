package com.roc.cxf.service.ws.oauth.server;

import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.rs.security.oauth2.common.Client;

import com.roc.cxf.service.ws.vo.ConsumerRegistration;
import com.roc.cxf.service.ws.vo.UserRegistration;

@Path("/")
public class OAuthService {
	
	private OAuthDataProvider oAuthDataProvider;

	@POST
	@Path("/register")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
	public ConsumerRegistration register(@FormParam("appName") String appName, 
										 @FormParam("appURI") String appURI, 
										 @FormParam("appRedirectURI") String appRedirectURI){
		System.out.println("Method: OAuthService.register() is running!");
		//生成cientId
		String clientId = this.generateClientId(appName, appURI);
		//生成clientSecret
		String clientSecret = this.generateClientSecret();
		//生成client对象并设置到授权数据提供者中
		Client client = new Client(clientId, clientSecret, true, appName, appURI);
		client.setRedirectUris(Collections.singletonList(appRedirectURI));
		this.oAuthDataProvider.setClient(client);
		//返回第三方APP注册对象
		ConsumerRegistration consumerRegistration = new ConsumerRegistration(clientId, clientSecret);
		return consumerRegistration;
	}
	
	public String generateClientId(String appName, String appURI) {
	    // if appURI is not allowed to contain paths, example, it can only be
	    // www.mycompany.com, then appURI can be used as a consumer key
		return "rocAPP-clientId";
	}
	
	public String generateClientSecret() {
        return "rocAPP-Secret";
    }

	public void setoAuthDataProvider(OAuthDataProvider oAuthDataProvider) {
		this.oAuthDataProvider = oAuthDataProvider;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/userAuth")
	public UserRegistration registerUser(@FormParam("loginName") String loginName,
			                         	@FormParam("password") String password) {
		System.out.println("Method: OAuthService.registerUser() is running!");
		
		System.out.println("--用户注册到accounts完成！");
		return new UserRegistration(loginName);
	}
	
}
