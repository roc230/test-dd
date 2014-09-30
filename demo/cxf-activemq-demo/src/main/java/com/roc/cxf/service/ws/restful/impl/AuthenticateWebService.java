package com.roc.cxf.service.ws.restful.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.roc.cxf.service.ws.restful.IAuthenticateWebService;
import com.roc.cxf.service.ws.vo.AuthToken;
import com.roc.cxf.service.ws.vo.PasswordToken;
import com.roc.utils.Base64Utils;
import com.roc.utils.JsonUtils;
import com.roc.utils.RSAUtils;
import com.roc.utils.RSAUtils2;

@Service
public class AuthenticateWebService implements IAuthenticateWebService{

	@Override
	public String login(String encryptJsonPasswordToken) {
		PasswordToken passwordToken = this.descryptePassworkToken(encryptJsonPasswordToken);
		//进行后台登陆操作
		if(null != passwordToken){
			
		}
		//操作成功后，加密认证信息返回客户端
		AuthToken authToken = new AuthToken();
		authToken.setAccountId("1");
		authToken.setDeadline(new Date());
		authToken.setIsAuthenticated(true);
		authToken.setSessionId("11111");
		
		return this.encryptAuthToken(authToken);
	}

	@Override
	public byte[] login2(byte[] encryptJsonPasswordToken) {
		System.out.println("Method login2() is running!");
		byte[] jsonPasswordToken = RSAUtils.decrypt(encryptJsonPasswordToken, getPrivateKey());
		String strJsonPasswordToken = new String(jsonPasswordToken);
		System.out.println(strJsonPasswordToken);
		PasswordToken passwordToken = JsonUtils.fromJson(strJsonPasswordToken, PasswordToken.class);
		
		AuthToken authToken = new AuthToken();
		authToken.setAccountId("15465465465464565465465465465465465465465465465465465465465465465464");
		authToken.setDeadline(new Date());
		authToken.setIsAuthenticated(true);
		authToken.setSessionId("111115416515151325123123513153665131531513513153153513513");
		
		return RSAUtils.encrypt(JsonUtils.toJsonString(authToken), getPrivateKey());
	}

	@Override
	public String logoff() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PasswordToken descryptePassworkToken(String encryptJsonPasswordToken){
		String decryptPassworkToken = RSAUtils2.decrypt(encryptJsonPasswordToken, getPrivateKey());
		PasswordToken passwordToken = JsonUtils.fromJson(decryptPassworkToken, PasswordToken.class);
		if(null == passwordToken){
			System.out.println("PasswordToken未能正常获取!认证不通过!");
			return null;
		}
		System.out.println("loginName: " + passwordToken.getLoginName());
		System.out.println("password: " + passwordToken.getPassword());
		return passwordToken;
	}
	
	private String encryptAuthToken(AuthToken authToken){
		String jsonAuthenToken = JsonUtils.toJsonString(authToken);
		String encyptJsonAuthToken = RSAUtils2.encrypt(jsonAuthenToken, getPrivateKey());
		return encyptJsonAuthToken;
	}
	
	private PrivateKey getPrivateKey(){
		//String base64PrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlswA71wnWZW0x76laZgBEObBjKkqc4wc0WafsfOEPgKeBy9FK4E7wMTSrjFUGX11qWA5sO6qHHIwDSiBdWTpXQIDAQABAkAmuGNVEtiXzDULRQy8PGepq1bvg1B/Mu8DZdKDov/F3Ahv66Md6854lOD8aCUvyLvrgUWPZneHlKtbAS9taIoBAiEA36fYh4FAEUNcqAtEjEhqoWIV+97er1/jBzmh3P2VYz0CIQCsmsoB2vOiyUxF4MsLe+nxHkIh0aLUP2LBJCUu3U+AoQIhAJ/vNf8IZVky84JTtGwgqOfI7XGUmWeNyRp0kxQl3nr9AiAaN+0FeR/XE2K9npbZKLpxgNokzvY/7Vzv37CyPLXN4QIgTHBkxCpnDG96HdkANY2L3ulDkKd2a35F7kJh0o0bwO0=";
		StringBuffer base64PrivateKeySB = new StringBuffer("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ1Hf3W5ImKZ+jsHzlzJDPfpLgUqpMVBtVhZMpWCSPHnhKNIJCjKl5l/wot9eSQPcoa/42eurBzb2VJh5wlwLB3OHTiseZtv7ca+Ff+iAEnYbx4mwO3X5KvSjyflQj7/s40ONd+E9Z8/9xmpbDcq0kSZaZzAXq3bYvnh0FvolgrhAgMBAAECgYA72tLiDWPNnIXDY1QgsY7S5hXSd4kfXMNT7RY4Gc4C6bXroe/I6ljJXPq/tS+eDpbqOyqi8bGkhfJtzL5tNBhFX/EY175f730iqDqzeSs3UZuNTgJuuTTqfzhPA9je7nZKU73CorEcCTRrf7Dx43wrjZkMkBIul3aHVuuZl0b/wQJBAPNTEETj8QmZy7LNnq6YmuuWKwGB89Ru2luz0/p97EgI3dbHxdtfD/PAqxdmgffn7rr/KZq8mdSmm0K07ADksXkCQQClePMSx4WyPdQ19Stx2mlq7JtPC2wSwfN1P0yGdK9uRnd7QVLmwVgFieyVSCHE+YQMyIzwlHipEDMWGddG/XKpAkEAw0rPq/sm4oXFr+TIj2l8LcmBCty6Tq4KI7/ZSxr2IXq8Uj5U1xUdQI5Kw6VKeyZzvTI+uirVbI+r3YZIRQ9IQQJAcs0C9xht7UdwHy9FQOwjDwewiJqIFEtzOPj9gpsZywwggObFOUsSYmVaOgedRidGMghBn/eh7slKaB7teFcGYQJAXgMiSW3s6mOxkENGx/HdcJHVQKZ8WLnLOiXgwhzfLt1jMWCoRy/YwLGYobr+z4lTBIcaTYno4bVxRzoZ9I36NA==");
		try {
			return RSAUtils.toPrivateKey(Base64Utils.decode(base64PrivateKeySB.toString()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
