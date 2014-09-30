package com.roc.cxf.service.ws.vo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class PasswordToken implements Serializable{

	@Expose(serialize = false, deserialize = true)
	private static final long serialVersionUID = 4259216323066890212L;
	
	private String loginName;
	private String password;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
