package com.roc.cxf.client.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.roc.cxf.service.ws.restful.IAuthenticateWebService;
import com.roc.cxf.service.ws.vo.AuthToken;
import com.roc.cxf.service.ws.vo.PasswordToken;
import com.roc.utils.Base64Utils;
import com.roc.utils.JsonUtils;
import com.roc.utils.RSAUtils;
import com.roc.utils.RSAUtils2;
import com.roc.utils.URLEncodeDecodeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContextTest.xml"})
public class AuthenticateWebServiceTest {
	
	@Resource(name = "authenticateWS")
	private IAuthenticateWebService authenticateWebService;
	
	@Test
	public void test_login() throws Exception{
		PasswordToken passwordToken = new PasswordToken();
		passwordToken.setLoginName("zhangsan");
		passwordToken.setPassword("1234567");
		String jsonPasswordToken = JsonUtils.toJsonString(passwordToken);
		
		String encryptJsonPasswordToken = RSAUtils2.encrypt(jsonPasswordToken, getPublicKey());
		
		String encryptJsonAuthToken = this.authenticateWebService.login(encryptJsonPasswordToken);
		String decryptJsonAuthToken = RSAUtils2.decrypt(encryptJsonAuthToken, getPublicKey());
		AuthToken authToken = JsonUtils.fromJson(decryptJsonAuthToken, AuthToken.class);
		System.out.println(decryptJsonAuthToken);
		
	}
	
	@Test
	public void test_login2() throws Exception{
		PasswordToken passwordToken = new PasswordToken();
		passwordToken.setLoginName("zhangsan");
		passwordToken.setPassword("1234567");
		String jsonPasswordToken = JsonUtils.toJsonString(passwordToken);
		
		byte[] encryptJsonPasswordToken = RSAUtils.encrypt(jsonPasswordToken, getPublicKey());
		
		byte[] byteJsonAuthToken = this.authenticateWebService.login2(encryptJsonPasswordToken);
		byte[] jsonAuthToken = RSAUtils.decrypt(byteJsonAuthToken, getPublicKey());
		String strJsonAuthToken = new String(jsonAuthToken);
		System.out.println(strJsonAuthToken);
		AuthToken authToken = JsonUtils.fromJson(strJsonAuthToken, AuthToken.class);
	}
	
	private PublicKey getPublicKey(){
		String base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdR391uSJimfo7B85cyQz36S4FKqTFQbVYWTKVgkjx54SjSCQoypeZf8KLfXkkD3KGv+Nnrqwc29lSYecJcCwdzh04rHmbb+3GvhX/ogBJ2G8eJsDt1+Sr0o8n5UI+/7ONDjXfhPWfP/cZqWw3KtJEmWmcwF6t22L54dBb6JYK4QIDAQAB";
		try {
			return RSAUtils.toPublicKey(Base64Utils.decode(base64PublicKey));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
