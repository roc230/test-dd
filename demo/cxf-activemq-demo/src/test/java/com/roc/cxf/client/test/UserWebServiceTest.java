package com.roc.cxf.client.test;

import javax.annotation.Resource;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.form.Form;
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
public class UserWebServiceTest {

	@Resource(name = "thirdUserWS")
	private IUserWebService userWebService;
	
	@Test
	public void test_get(){
		UserVO userVO = this.userWebService.get("1");
		Assert.assertNotNull(userVO);
		System.out.println(JsonUtils.toJsonString(userVO));
	}
	
	@Test
	public void test_register(){
		WebClient rs = WebClient.create("http://localhost:8080/services/register/registerUser");
    	WebClient.getConfig(rs).getHttpConduit().getClient().setReceiveTimeout(10000000L);
    	rs.form(new Form().set("user", "barry@social.com").set("password", "1234"));
	}
	
}
