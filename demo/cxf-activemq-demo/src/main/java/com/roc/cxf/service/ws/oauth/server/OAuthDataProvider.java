package com.roc.cxf.service.ws.oauth.server;

import java.util.ArrayList;
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

public class OAuthDataProvider implements AuthorizationCodeDataProvider{
	
	private Client client;
	private ServerAccessToken serverAccessToken;
	private ServerAuthorizationCodeGrant serverAuthorizationCodeGrant;

	@Override
	public Client getClient(String clientId) throws OAuthServiceException {
		if(null == client){
			return null;
		}
		return client.getClientId().equals(clientId) ? client : null;
	}

	@Override
	public ServerAccessToken createAccessToken(
			AccessTokenRegistration accessToken) throws OAuthServiceException {
		this.serverAccessToken = new BearerAccessToken(accessToken.getClient(), 3600L);
		List<String> scopes = accessToken.getApprovedScope().isEmpty() ? accessToken.getRequestedScope() : accessToken.getApprovedScope();
		this.serverAccessToken.setScopes(this.convertScopeToPermissions(accessToken.getClient(), scopes));
		this.serverAccessToken.setSubject(accessToken.getSubject());
		this.serverAccessToken.setGrantType(accessToken.getGrantType());
		return this.serverAccessToken;
	}

	@Override
	public ServerAccessToken getAccessToken(String accessToken)
			throws OAuthServiceException {
		if(null == this.serverAccessToken){
			return null;
		}
		return this.serverAccessToken.getTokenKey().equals(accessToken) ? this.serverAccessToken : null;
	}

	@Override
	public ServerAccessToken getPreauthorizedToken(Client client,
			List<String> requestedScopes, UserSubject subject, String grantType)
			throws OAuthServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerAccessToken refreshAccessToken(Client client,
			String refreshToken, List<String> requestedScopes)
			throws OAuthServiceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeAccessToken(ServerAccessToken accessToken)
			throws OAuthServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OAuthPermission> convertScopeToPermissions(Client client,
			List<String> requestedScope) {
		System.out.println("Method: OAuthManager.convertScopeToPermissions() is running!");
		List<OAuthPermission> list = new ArrayList<OAuthPermission>();
		for (String scope : requestedScope) {
		    if (scope.equals("getAccountById")) {
		    	OAuthPermission oAuthPermission = new OAuthPermission("getAccountById", "根据ID查询帐户");
		    	list.add(oAuthPermission);
		    }
		}
		System.out.println("--根据访问范围scope集合生成客户端访问权限集合");
		return list;
	}

	@Override
	public ServerAuthorizationCodeGrant createCodeGrant(
			AuthorizationCodeRegistration reg) throws OAuthServiceException {
		System.out.println("Method: OAuthManager.createCodeGrant() is running!");
		
		this.serverAuthorizationCodeGrant = new ServerAuthorizationCodeGrant(this.client, 3600L);
		this.serverAuthorizationCodeGrant.setRedirectUri(reg.getRedirectUri());
		this.serverAuthorizationCodeGrant.setSubject(reg.getSubject());
		List<String> scope = reg.getApprovedScope().isEmpty() ? reg.getRequestedScope() 
                : reg.getApprovedScope();
		this.serverAuthorizationCodeGrant.setApprovedScopes(scope);
		return this.serverAuthorizationCodeGrant;
	}

	/* (non-Javadoc)
	 * @see org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeDataProvider#removeCodeGrant(java.lang.String)
	 */
	@Override
	public ServerAuthorizationCodeGrant removeCodeGrant(String code)
			throws OAuthServiceException {
		System.out.println("Method: OAuthManager.removeCodeGrant() is running!");
		ServerAuthorizationCodeGrant theGrant = null;
		if (this.serverAuthorizationCodeGrant.getCode().equals(code)) {
			theGrant = this.serverAuthorizationCodeGrant;
			this.serverAuthorizationCodeGrant = null;
		}
		return theGrant;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
