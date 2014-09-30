package com.roc.cxf.service.ws.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@XmlRootElement
public class ResultVO<T> implements Serializable{

	private static final long serialVersionUID = 3475034802023940960L;
	
	/**
	 * 成功失败标识
	 * 成功"success",失败"failed"
	 */
	@SerializedName("flag")
	@Expose(serialize = true)
	private String flag;
	
	/**
	 * 返回结果
	 * 可以是一个VO对象或者VO对象集合
	 */
	@SerializedName("result")
	@Expose(serialize = true)
	private T result;
	
	/**
	 * 重定向地址
	 * --需要重定向时向这个属性设置重定向地址
	 * --不需要则设置为空字符串
	 */
	@SerializedName("url")
	@Expose(serialize = true)
	private String url;
	
	/**
	 * 异常内容
	 * --如果出现异常时，使用这个属性传回调用端
	 * --不需要时设置为空字符串
	 */
	@SerializedName("exception")
	@Expose(serialize = true)
	private String exception;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
}
