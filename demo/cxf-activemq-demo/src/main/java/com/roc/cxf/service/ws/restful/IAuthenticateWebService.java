package com.roc.cxf.service.ws.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAuthenticateWebService {
	@POST
	@Path("/login")
	public String login(String encryptJsonPasswordToken);
	
	@POST
	@Path("/login2")
	public byte[] login2(byte[] encryptJsonPasswordToken);
	
	public String logoff();

}
