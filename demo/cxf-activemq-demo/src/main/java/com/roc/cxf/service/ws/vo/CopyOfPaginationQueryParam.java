package com.roc.cxf.service.ws.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paginationQueryParam")
public class CopyOfPaginationQueryParam implements Serializable{

	private static final long serialVersionUID = -4910946479625242100L;
	
	/**
	 * 页码，按UI习惯从第1页开始
	 */
	private Integer pageNum = 1;
	/**
	 * 每页数据量
	 */
	private Integer pageSize = 10;
	/**
	 * 排序参数
	 */
//	private Order[] orders = new Order[]{};
	
	public CopyOfPaginationQueryParam(){}
	
	public CopyOfPaginationQueryParam(Integer pageNum, Integer pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	/**
	 * 把一个新的Order对象加到Order数组中
	 * @param order
	 * @return
	 */
//	public Order[] addOrder(Order order){
//		if(null == order){
//			throw new NullPointerException("order不能为null");
//		}
//		ArrayUtils.add(this.orders, order);
//		return this.orders;
//	}
	
	/**
	 * 把一个Order数组加到分页参数的顺序Order数组中
	 * @param orderArray Order数组
	 * @return 
	 */
//	public Order[] addOrder(Order[] orderArray){
//		if(null == orderArray){
//			throw new NullPointerException("orderArray不能为null");
//		}
//		ArrayUtils.addAll(this.orders, orderArray);
//		return this.orders;
//	}
	@XmlElement(name = "pageNum")
	public Integer getPageNum() {
		return pageNum;
	}
	/**
	 * 设置分页查询的页码，从第1页开始
	 * @param pageNum
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	@XmlElement(name = "pageSize")
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * 设置分页查询每页的数据量
	 * @param pageSize
	 * @return
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
//	public Order[] getOrders() {
//		return this.orders;
//	}
}
