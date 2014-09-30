package com.roc.cxf.service.ws.restful.impl;

import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.rs.security.oauth2.utils.OAuthContextUtils;
import org.springframework.stereotype.Service;

import com.roc.cxf.service.ws.restful.IAccountWebService;
import com.roc.cxf.service.ws.vo.ResultVO;
import com.roc.cxf.service.ws.vo.UserVO;

@Service
public class AccountWebService extends BasicJsonWebService2<UserVO> implements IAccountWebService {
	
	@Context
	private MessageContext mc;
	
	@Override
	public ResultVO<UserVO> add(UserVO vo) {
		System.out.println("Method add() is running!");
		// TODO Auto-generated method stub
		return SUCCESS(vo);
	}

	@Override
	public ResultVO<UserVO> get(String id) {
		System.out.println("Method: get() is running!");
		String userName = OAuthContextUtils.resolveUserName(mc);
		System.out.println("userName:" + userName);
		
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setName("roc");
		vo.setSex("male");
		vo.setAge(18);
		
		return SUCCESS(vo);
	}
	
	
}
