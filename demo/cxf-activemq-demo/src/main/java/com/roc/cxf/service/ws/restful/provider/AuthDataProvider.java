package com.roc.cxf.service.ws.restful.provider;

import java.util.List;

import org.apache.cxf.rs.security.oauth2.common.AccessTokenRegistration;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;

public class AuthDataProvider implements OAuthDataProvider{

	@Override
	public Client getClient(String s) throws OAuthServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAccessToken createAccessToken(
			AccessTokenRegistration accesstokenregistration)
			throws OAuthServiceException {
		System.out.println("Method createAccessToken() is running...");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAccessToken getAccessToken(String s)
			throws OAuthServiceException {
		System.out.println("Method getAccessToken() is running...");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAccessToken getPreauthorizedToken(Client client,
			List<String> list, UserSubject usersubject, String s)
			throws OAuthServiceException {
		System.out.println("Method getPreauthorizedToken() is running...");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAccessToken refreshAccessToken(Client client, String s,
			List<String> list) throws OAuthServiceException {
		System.out.println("Method refreshAccessToken() is running...");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccessToken(ServerAccessToken serveraccesstoken)
			throws OAuthServiceException {
		System.out.println("Method removeAccessToken() is running...");
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OAuthPermission> convertScopeToPermissions(Client client,
			List<String> list) {
		System.out.println("Method convertScopeToPermissions() is running...");
		// TODO Auto-generated method stub
		return null;
	}

}
