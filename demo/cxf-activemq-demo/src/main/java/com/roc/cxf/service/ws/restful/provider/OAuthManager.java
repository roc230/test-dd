package com.roc.cxf.service.ws.restful.provider;

import java.util.List;

import org.apache.cxf.rs.security.oauth2.common.AccessTokenRegistration;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeDataProvider;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeRegistration;
import org.apache.cxf.rs.security.oauth2.grants.code.ServerAuthorizationCodeGrant;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.apache.cxf.rs.security.oauth2.tokens.bearer.BearerAccessToken;

/**
 * clientId、accessToken、proauthorizedToken、codeGrant提供类
 * 
 * @author roc
 * 
 */
public class OAuthManager implements AuthorizationCodeDataProvider {

	private ServerAccessToken serverAccessToken;

	@Override
	public Client getClient(String clientId) throws OAuthServiceException {
		System.out.println("Method: getClient() is running!");
		// TODO Auto-generated method stub
		Client client = new Client("sys001", "clientSecret", true);
		return client;
	}

	@Override
	public ServerAccessToken createAccessToken(
			AccessTokenRegistration accessToken) throws OAuthServiceException {
		System.out.println("Method: createAccessToken() is running!");
		serverAccessToken = new BearerAccessToken(accessToken.getClient(),
				3600L);
		List<String> scope = accessToken.getApprovedScope().isEmpty() ? accessToken
				.getRequestedScope() : accessToken.getApprovedScope();
		serverAccessToken.setScopes(convertScopeToPermissions(
				accessToken.getClient(), scope));
		serverAccessToken.setSubject(accessToken.getSubject());
		serverAccessToken.setGrantType(accessToken.getGrantType());
		return serverAccessToken;
	}

	@Override
	public ServerAccessToken getAccessToken(String accessToken)
			throws OAuthServiceException {
		System.out.println("Method: getAccessToken() is running!");
		return serverAccessToken;
	}

	@Override
	public ServerAccessToken refreshAccessToken(Client client,
			String refreshToken, List<String> requestedScopes)
			throws OAuthServiceException {
		System.out.println("Method: refreshAccessToken() is running!");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccessToken(ServerAccessToken accessToken)
			throws OAuthServiceException {
		System.out.println("Method: removeAccessToken() is running!");
		// TODO Auto-generated method stub

	}

	@Override
	public ServerAccessToken getPreauthorizedToken(Client client,
			List<String> requestedScopes, UserSubject subject, String grantType)
			throws OAuthServiceException {
		System.out.println("Method: getPreauthorizedToken() is running!");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OAuthPermission> convertScopeToPermissions(Client client,
			List<String> requestedScope) {
		System.out.println("Method: convertScopeToPermissions() is running!");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAuthorizationCodeGrant createCodeGrant(
			AuthorizationCodeRegistration reg) throws OAuthServiceException {
		System.out.println("Method: createCodeGrant() is running!");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAuthorizationCodeGrant removeCodeGrant(String code)
			throws OAuthServiceException {
		System.out.println("Method: removeCodeGrant() is running!");
		// TODO Auto-generated method stub
		return null;
	}

}
