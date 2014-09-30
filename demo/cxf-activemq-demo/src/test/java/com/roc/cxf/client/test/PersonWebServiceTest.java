package com.roc.cxf.client.test;

import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.reflect.TypeToken;
import com.roc.cxf.service.ws.restful.IPersonWebService;
import com.roc.cxf.service.ws.vo.PaginationQeuryResult;
import com.roc.cxf.service.ws.vo.PaginationQueryParam;
import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/applicationContextTest.xml"})
public class PersonWebServiceTest {
	
	@Resource
	private IPersonWebService personWebService;
	
	@Test
	public void test_add(){
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		vo.setAge(18);
		String jsonResult = this.personWebService.add(vo);
		Type type = new TypeToken<ResultVO<UserVO>>(){}.getType();
		ResultVO<UserVO> resultVO = JsonUtils.fromJson(jsonResult, type);
		System.out.println(JsonUtils.toJsonString(resultVO));
		UserVO userVO = (UserVO)resultVO.getResult();
		System.out.println("user.age = " + userVO.getAge());
	}
	
	@Test
	public void test_get(){
		String jsonResult = this.personWebService.get("1");
		System.out.println(jsonResult);
	}
	
	@Test
	public void test_getXml(){
		UserVO userVO = this.personWebService.getXml("1");
		System.out.println(JsonUtils.toJsonString(userVO));
	}
	
	@Test
	public void test_getJson(){
		UserVO userVO = this.personWebService.getJson("1");
		System.out.println(JsonUtils.toJsonString(userVO));
	}
	
	@Test
	public void test_getPagination(){
		String result = this.personWebService.getPagination(1,10);
		System.out.println(result);
	}
	
	@Test
	public void test_getPaginationJson(){
		PaginationQeuryResult<UserVO> pResult = this.personWebService.getPaginationJson(1, 10);
	}
	
	@Test
	public void test_getPagination2(){
		PaginationQueryParam param = new PaginationQueryParam();
		param.setPageNum(1);
		param.setPageSize(10);
		PaginationQeuryResult<UserVO> result = this.personWebService.getPagination(param);
	}
	
	@Test
	public void test_getPaginationResult(){
		PaginationQueryParam param = new PaginationQueryParam();
		param.setPageNum(1);
		param.setPageSize(10);
		ResultVO<PaginationQeuryResult<UserVO>> result = this.personWebService.getPaginationResult(param);
		System.out.println(JsonUtils.toJsonString(result.getResult().getList()));
	}
	
	@Test
	public void test_getPaginationByColumnName(){
		//1.准备查询参数
		Integer pageNum = 1;
		Integer pageSize = 10;
		String columnName = "name";
		String equalsOrLike = "equals";
		String ascOrDesc = "desc";
		//2.调用网络服务并得到json字符串格式查询结果
		String result = this.personWebService.getPaginationByColumnName(pageNum,pageSize,columnName,equalsOrLike,ascOrDesc);
		//1.解释出网络服务数据对象
		Type type = new TypeToken<ResultVO<PaginationQeuryResult<UserVO>>>(){}.getType();
		ResultVO<PaginationQeuryResult<UserVO>> resultVO = JsonUtils.fromJson(result, type);
		//2.从网络服务数据对象中取出分页查询对象
		PaginationQeuryResult<UserVO> pResult = resultVO.getResult();
		//3.打印结果查看
		System.out.println(result);
		System.out.println(JsonUtils.toJsonString(pResult));
		System.out.println(JsonUtils.toJsonString(pResult.getList()));
		System.out.println(JsonUtils.toJsonString(pResult.getList().get(0)));
	}
	
}
