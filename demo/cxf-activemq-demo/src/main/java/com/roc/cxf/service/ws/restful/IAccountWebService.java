package com.roc.cxf.service.ws.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;

@Path("/account")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface IAccountWebService extends IBasicJsonWebService<UserVO>{

	@POST
	@Path("/")
	public ResultVO<UserVO> add(UserVO vo);
	
}
