package com.roc.cxf.service.ws.restful;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.roc.cxf.service.ws.vo.PaginationQueryParam;
import com.roc.cxf.service.ws.vo.ResultVO;

public interface IBasicJsonWebService<T> {
	
	@POST
	@Path("/")
	public ResultVO<T> add(T vo);
	
	@DELETE
	@Path("/{id}")
	public ResultVO<T> delete(@PathParam("id") String id);
	
	@PUT
	@Path("/")
	public ResultVO<T> update(T vo);
	
	@GET
	@Path("/{id}")
	public ResultVO<T> get(@PathParam("id") String id);
	
	@GET
	@Path("/getPagination")
	public ResultVO<T> getPagination(PaginationQueryParam queryParam);
	
	@GET
	@Path("/getPaginationByClumnName")
	public ResultVO<T> getPaginationByName(String ClumnName, PaginationQueryParam queryParam);
}
