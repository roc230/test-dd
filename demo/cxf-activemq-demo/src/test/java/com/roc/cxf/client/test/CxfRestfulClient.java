package com.roc.cxf.client.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.roc.cxf.service.ws.restful.IUserWebService;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContextTest.xml"})
public class CxfRestfulClient{
	
	@Resource(name = "userWS")
	private IUserWebService userWebService;
	
	@Test
	public void test_add(){
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		userWebService.add(vo);
	}
	
	@Test
	public void test_get(){
		UserVO vo = userWebService.get("1");
		Assert.assertNotNull(vo);
		System.out.println(JsonUtils.toJsonString(vo));
	}
	
	@Test
	public void test_jsonGet(){
		String jsonResult = userWebService.jsonGet("1");
		System.out.println(jsonResult);
	}
	
	@Test
	public void test_jsonGetList(){
		
	}
	
	@Test
	public void test_delete(){
		userWebService.delete("del");
	}
}
