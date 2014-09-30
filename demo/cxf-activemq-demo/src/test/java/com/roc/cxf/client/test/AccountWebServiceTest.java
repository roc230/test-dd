package com.roc.cxf.client.test;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.roc.cxf.service.ws.restful.IAccountWebService;
import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContextTest.xml"})
public class AccountWebServiceTest {

	@Resource(name = "accountWS")
	private IAccountWebService accountWebService;
	
	@Test
	public void test_add(){
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		vo.setAge(18);
		ResultVO<UserVO> result = this.accountWebService.add(vo);
	}
	
	@Test
	public void test_add2(){
		String uri = "http://localhost:8080/cxf-activemq-demo/services/rest/account";
		HttpPost httpPost = new HttpPost(uri);
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			httpResponse.getEntity().writeTo(System.out);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_get(){
		ResultVO<UserVO> result = this.accountWebService.get("1");
		System.out.println(JsonUtils.toJsonString(result));
	}
	
	@Test
	public void test_get_with_auth(){
		String uri = "http://localhost:8080/cxf-activemq-demo/services/account/1";
		HttpGet get = new HttpGet(uri);
		setHttpHeaders(get, "zhangsan", "1234567");
		handleHttpRequest(get);
	}
	
	public void setHttpHeaders(HttpRequest httpRequest, String loginName, String password){
		if(httpRequest instanceof HttpPost || httpRequest instanceof HttpPut){
			httpRequest.setHeader("Content-Type", "application/xml");
		}
		httpRequest.addHeader("Accept", "application/json");
		httpRequest.addHeader("Authorization", loginName + ":" + password);
	}
	
	public void handleHttpRequest(HttpUriRequest httpRequest){
		HttpClient client = HttpClients.createDefault();
		try {
			HttpResponse httpResponse = client.execute(httpRequest);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			Response.Status status = Response.Status.fromStatusCode(statusCode);
			if(status == Response.Status.OK){
				System.out.println(httpResponse.getStatusLine().toString());
				HttpEntity httpEntity = httpResponse.getEntity();
				httpEntity.writeTo(System.out);
//				InputStream inputStream = httpEntity.getContent();
//				System.out.println(InputStreamUtils.read2String(inputStream));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			httpRequest.abort();
		}
	}
}
