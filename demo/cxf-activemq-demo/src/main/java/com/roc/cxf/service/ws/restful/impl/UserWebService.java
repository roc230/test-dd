package com.roc.cxf.service.ws.restful.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.roc.cxf.service.ws.restful.IUserWebService;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;

@Path("/user") //本类实例的入口上下文
@Produces({"application/json"}) //标注返回的MIME媒体类型
@Consumes({"application/xml"}) //标注可接受请求的MIME媒体类型
@Service("userWebService")
public class UserWebService implements IUserWebService{

	@Override
	@POST //Restful新增操作标识
	//@GET，@PUT，@POST，@DELETE，标注方法是用的HTTP请求的类型
	@Path("/")
	public String add(UserVO userVO) {
		System.out.println("Method add() is running!");
		System.out.println(JsonUtils.toJsonString(userVO));
		return "success";
	}

	@Override
	@DELETE //Restful删除操作标识
//	@Path("/user/{id}") //如果接口类不设置类入口@Path("/user")时，采用这种方式指定方法入口路径
	@Path("/{id}")  
	public String delete(@PathParam("id") String id) {
		System.out.println("Method delete() is running!");
		return null;
	}

	@Override
	@PUT //Restful更新操作标识
	@Path("/")
	public String update(UserVO userVO) {
		System.out.println("Method update() is running!");
		return null;
	}

	@Override
	@GET //Restful查询操作标识
	@Path("/{id}")
	public UserVO get(@PathParam("id") String id) {
		System.out.println("Method get() is running!");
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		if(StringUtils.isNotEmpty(id)){
			if("1".equals(id)){
				return vo;
			}
		}
		return null;
	}
	
	@Override
	@GET //Restful查询操作标识
	@Path("jsonGet/{id}")
	public String jsonGet(@PathParam("id") String id){
		System.out.println("Method jsonGet() is running!");
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		if(StringUtils.isNotEmpty(id)){
			if("1".equals(id)){
				return JsonUtils.toJsonString(vo);
			}
		}
		return null;
	}

	@Override
	public String jsonGetList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
