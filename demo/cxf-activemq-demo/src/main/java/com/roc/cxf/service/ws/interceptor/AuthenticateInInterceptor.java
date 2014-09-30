package com.roc.cxf.service.ws.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.cxf.frontend.FaultInfoException;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.interceptor.JAXRSInInterceptor;
import org.apache.cxf.message.Message;

import com.roc.cxf.service.IAccountService;
import com.roc.cxf.service.ws.vo.UserVO;
import com.roc.utils.JsonUtils;
import com.roc.utils.PropertiesUtils;

public class AuthenticateInInterceptor extends JAXRSInInterceptor{
	
	@Resource
	private IAccountService accountService;

	@Override
	public void handleMessage(Message message) {
		System.out.println("AuthenticateInInterceptor 开始执行...");
		//拦截例外，直接返回
		if(isInterceptorExclue(message)){
			System.out.println("本次请求是例外地址，不进行拦截，拦截器终止!");
			return ;
		}
		//是否已通过认证
		if(!isAuthenticated(message)){
			Response response = message.getExchange().get(Response.class);
			response.status(401).header("www-auth", "basic ream");
		}
		//是否已经授权
		if(!isAuthorized(message)){
			return ;
		}
		
		Map<String, List<String>> protocolHeaders = CastUtils.cast((Map<?, ?>)message.get(Message.PROTOCOL_HEADERS));
		
		for(String str:protocolHeaders.keySet()){
			System.out.println(str);
		}
		System.out.println("==========客户端地址==============");
		System.out.println(message.get(Message.REQUEST_URL));
		
		
		System.out.println("=======测试从消息头部取出数据=======");
		String key = "Authorization";
		if(protocolHeaders.keySet().contains(key)){
			List<String> valueList = protocolHeaders.get(key);
			for(String str : valueList){
				System.out.println(str);
			}
		}
		System.out.println("=======测试本地服务调用========");
		UserVO vo = accountService.get("1");
		System.out.println(JsonUtils.toJsonString(vo));
	}
	
	/**
	 * 如果本次请求地址存在拦截例外地址中，则返回true，否则false
	 * @param message
	 * @return
	 */
	private boolean isInterceptorExclue(Message message){
		String url = (String)message.get(Message.REQUEST_URL);
		String filePath = this.getClass().getResource("/").getPath() + "jaxrs-interceptor-exclude-address.properties";
		Properties properties = PropertiesUtils.loadProperties(filePath);
		for(Object value:properties.values()){
			String val = (String)value;
			if(url.length() >= val.length()){
				if(url.subSequence(0, val.length()).equals(val)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 是否已通过认证，即本次访问是否合法登陆过，带有合法token
	 * @param message
	 * @return
	 */
	private boolean isAuthenticated(Message message){
		System.out.println("进行认证较验...");
		return true;
	}
	
	/**
	 * 是否已授权，即本次访问的网络服务接口是否已经授权可以访问
	 * @param message
	 * @return
	 */
	private boolean isAuthorized(Message message){
		System.out.println("进行授权较验...");
		return true;
	}
	
}
