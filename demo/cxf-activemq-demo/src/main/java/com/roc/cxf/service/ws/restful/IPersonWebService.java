package com.roc.cxf.service.ws.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.roc.cxf.service.ws.vo.PaginationQeuryResult;
import com.roc.cxf.service.ws.vo.PaginationQueryParam;
import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;

@Path("/person")
@Produces({MediaType.APPLICATION_JSON}) //配置类方法返回JSON格式数据，如果个别方法返回其它数据如XML格式，需要在其它方法前指定
@Consumes({MediaType.APPLICATION_XML})
public interface IPersonWebService{
	
	@POST
	@Path("/")
	public String add(UserVO userVO);
	
	@GET
	@Path("/{id}")
	public String get(@PathParam("id") String id);
	
	@GET
	@Path("/getJson/{id}")
	public UserVO getJson(@PathParam("id") String id);
	
	@GET
	@Path("getXml/{id}")
	@Produces({MediaType.APPLICATION_XML})
	public UserVO getXml(@PathParam("id") String id);
	
	@GET
	@Path("/getPagination/{pageNum}/{pageSize}")
	public String getPagination(@PathParam("pageNum") Integer pageNum, 
								@PathParam("pageSize") Integer pageSize);
	
	@GET
	@Path("/getPaginationJson/{pageNum}/{pageSize}")
	public PaginationQeuryResult<UserVO> getPaginationJson(@PathParam("pageNum") Integer pageNum, 
								@PathParam("pageSize") Integer pageSize);
	
	@GET
	@Path("/getPaginationByColumnName/{pageNum}/{pageSize}/{columnName}/{equalsOrLike}/{ascOrDesc}")
	public String getPaginationByColumnName(@PathParam("pageNum") Integer pageNum,
											@PathParam("pageSize") Integer pageSize,
											@PathParam("columnName") String clomnName,
											@PathParam("equalsOrLike") String equalsOrLike,
											@PathParam("ascOrDesc") String ascOrDesc);
	
	@GET
	@Path("/getPagination")
	public PaginationQeuryResult<UserVO> getPagination(@QueryParam("") PaginationQueryParam paginationQueryParam);
	
	@GET
	@Path("/getPaginationResult")
	public ResultVO<PaginationQeuryResult<UserVO>> getPaginationResult(@QueryParam("") PaginationQueryParam paginationQueryParam);
}
