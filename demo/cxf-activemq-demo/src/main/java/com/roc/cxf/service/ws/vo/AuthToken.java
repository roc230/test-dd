package com.roc.cxf.service.ws.vo;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;


public class AuthToken implements Serializable{

	@Expose(deserialize = true, serialize = false)
	private static final long serialVersionUID = -2707314591406529371L;
	/**
	 * 有效期限
	 */
	private Date deadline;
	/**
	 * 是否已通过认证
	 */
	private Boolean isAuthenticated = false;
	/**
	 * 服务端会话id，如果未通过认证，设为""
	 */
	private String sessionId;
	/**
	 * 帐户ID
	 */
	private String accountId;

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
