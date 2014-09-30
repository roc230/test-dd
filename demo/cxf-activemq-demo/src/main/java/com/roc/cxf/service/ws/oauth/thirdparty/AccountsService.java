package com.roc.cxf.service.ws.oauth.thirdparty;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.roc.cxf.service.ws.vo.UserVO;

@Path("/accounts")
public class AccountsService {
	
	private ClientOAuthDataManager clientOAuthDataManager;
	@Context
	private SecurityContext securityContext;

	@POST
	public Response get(@FormParam("id") String id){
		System.out.println("Method AccountsService.get() is running!");
		String scope = "getById";
		
		////////////
		UserVO user = new UserVO();
		user.setId("1");
		user.setName("cuizhip");
		user.setAge(18);
		
		return Response.ok(user).build();
	}

	public void setClientOAuthDataManager(
			ClientOAuthDataManager clientOAuthDataManager) {
		this.clientOAuthDataManager = clientOAuthDataManager;
	}

}
