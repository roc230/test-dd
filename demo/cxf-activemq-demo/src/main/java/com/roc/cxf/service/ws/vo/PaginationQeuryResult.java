package com.roc.cxf.service.ws.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;
import com.google.gson.annotations.SerializedName;

/**
 * 网络服务分页查询结果对象
 * @author roc 2014-8-26
 *
 * @param <T>
 */
public class PaginationQeuryResult<T> implements Serializable{

	private static final long serialVersionUID = 3392533877436030932L;
	
	/**
	 * 当前页码,默认值为1
	 */
	@SerializedName("pageNum")
	private Integer pageNum = 1;
	
	/**
	 * 默认页数据量
	 */
	private Integer defaultPageSize  = 10;
	
	/**
	 * 查询总数据量
	 */
	private Integer totalCount;
	
	/**
	 * 当前查询页的数据列表
	 */
	private List<T> list;
	
	private String columnName;
	private String equalsOrLike;
	private String ascOrDesc;
	
	/**
	 * 获取总页数
	 * @return
	 */
	@JsonIgnore(value = true)
	public Integer getPageCount(){
		int value = this.totalCount/this.defaultPageSize;
		if(this.totalCount % this.defaultPageSize == 0){
			return value;
		}
		return value + 1;
	}
	
	/**
	 * 是否有下一页
	 * @return
	 */
	public Boolean hasNextPage(){
		if(getPageNum() < getPageCount()){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否有前一页
	 * @return
	 */
	public Boolean hasProviousPage(){
		if(this.pageNum > 1){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前页的数据量
	 * @return
	 */
	@JsonIgnore(value = true)
	public Integer getCurrentPageSize(){
		return this.list.size();
	}

	/**
	 * 获取查询总数据量
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getEqualsOrLike() {
		return equalsOrLike;
	}

	public void setEqualsOrLike(String equalsOrLike) {
		this.equalsOrLike = equalsOrLike;
	}

	public String getAscOrDesc() {
		return ascOrDesc;
	}

	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}

}
