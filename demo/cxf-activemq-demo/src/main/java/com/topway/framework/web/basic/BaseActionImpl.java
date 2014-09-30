package com.topway.framework.web.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseActionImpl extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final String SUCCESS = "success";
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	
	
	public ServletOutputStream getOutputStream() throws IOException{
		return getResponse().getOutputStream();
	}
	
	public PrintWriter getWriter() throws IOException{
		return getResponse().getWriter();
	}

	

}
