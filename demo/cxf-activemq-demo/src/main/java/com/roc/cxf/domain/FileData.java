package com.roc.cxf.domain;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;


@XmlAccessorType(XmlAccessType.FIELD)
public class FileData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	@XmlMimeType("application/octet-stream") //声明是文件流数据类型
	private DataHandler data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataHandler getData() {
		return data;
	}
	public void setData(DataHandler data) {
		this.data = data;
	}
	
	
	
}
