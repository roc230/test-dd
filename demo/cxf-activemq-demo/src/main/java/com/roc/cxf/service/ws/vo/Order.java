package com.roc.cxf.service.ws.vo;

import java.io.Serializable;

/**
 * 排序的排序对象
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1355642814137432392L;

	public static Order create(String property, boolean desc) {
		return new Order(property, desc);
	}

	public static Order create(String property, boolean desc, boolean isNullLast) {
		return new Order(property, desc, isNullLast);
	}

	/**
	 * 排序属性
	 */
	private String property = null;

	/**
	 * 是否降序
	 */
	private boolean desc = true;

	/**
	 * 如果排序字段是空值，是否放最后
	 */
	private boolean isNullLast = false;

	public Order(String property) {
		this.property = property;
	}

	/**
	 * 实例化
	 * 
	 * @param property
	 *            排序的属性
	 * @param desc
	 *            排序方式
	 */
	public Order(String property, boolean desc) {
		this.property = property;
		this.desc = desc;
	}

	/**
	 * 实例化
	 * 
	 * @param property
	 *            排序的属性
	 * @param desc
	 *            排序方式
	 */
	public Order(String property, boolean desc, boolean isNullLast) {
		this.property = property;
		this.desc = desc;
		this.isNullLast = isNullLast;
	}

	public String getProperty() {
		return this.property;
	}

	public boolean isDesc() {
		return this.desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isNullLast() {
		return isNullLast;
	}

	public void setNullLast(boolean isNullLast) {
		this.isNullLast = isNullLast;
	}
}