package com.roc.cxf.service;

import com.roc.cxf.service.ws.vo.UserVO;

public interface IAccountService {
	
	public String add(UserVO userVO);
	
	public String delete(String id);
	
	public String update(UserVO userVO);
	
	public UserVO get(String id);
}	
