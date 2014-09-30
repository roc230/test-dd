package com.roc.cxf.service.impl;

import org.springframework.stereotype.Service;

import com.roc.cxf.service.IAccountService;
import com.roc.cxf.service.ws.vo.UserVO;

@Service
public class AccountService implements IAccountService {

	@Override
	public String add(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO get(String id) {
		
		return createVO();
	}
	
	private UserVO createVO(){
		UserVO vo = new UserVO();
		vo.setId("1");
		vo.setAge(18);
		vo.setName("roc");
		vo.setSex("male");
		return vo;
	}

}
