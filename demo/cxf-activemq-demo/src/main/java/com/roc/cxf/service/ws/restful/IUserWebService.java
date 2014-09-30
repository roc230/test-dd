package com.roc.cxf.service.ws.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.roc.cxf.service.ws.vo.UserVO;

@Path("/user") //本类实例的入口上下文
@Produces({"application/json"}) //标注返回的MIME媒体类型
@Consumes({"application/xml"}) //标注可接受请求的MIME媒体类型
public interface IUserWebService {
	
	@POST //Restful新增操作标识
			//@GET，@PUT，@POST，@DELETE，标注方法是用的HTTP请求的类型
	@Path("/")
	public String add(UserVO userVO);
	
	@DELETE //Restful删除操作标识
//	@Path("/user/{id}") //如果接口类不设置类入口@Path("/user")时，采用这种方式指定方法入口路径
	@Path("/{id}")  
						//@PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam,
		                //分别标注方法的参数来自于HTTP请求的不同位置，例如@PathParam来自于URL的路径，
						//@QueryParam来自于URL的查询参数，@HeaderParam来自于HTTP请求的头信息，
						//@CookieParam来自于HTTP请求的Cookie
	public String delete(@PathParam("id") String id);
	
	@PUT //Restful更新操作标识
	@Path("/")
	public String update(UserVO userVO);
	
	@GET //Restful查询操作标识
	@Path("/{id}")
	public UserVO get(@PathParam("id") String id);

	@GET //Restful查询操作标识
	@Path("jsonGet/{id}")
	public String jsonGet(@PathParam("id") String id);
	
	@GET //Restful查询操作标识
	@Path("jsonGetList/{id}")
	public String jsonGetList(@PathParam("id") String id);
}
