package com.roc.cxf.service.ws.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paginationQueryParam")
public class PaginationQueryParam implements Serializable{
	
	private static final long serialVersionUID = -50894193705037110L;

	private Integer pageNum;
	
	private Integer pageSize;

	@XmlElement(name = "pageNum")
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	@XmlElement(name = "pageSize")
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
