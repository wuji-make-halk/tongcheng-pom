package com.micro.pmo.moudle.extension.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Table;

/**
 * 推广费用配置表Entity
 */
@Table(name = "sys_extension")
public class Extension implements Serializable{

	private static final long serialVersionUID = 1L;
 
	/**
	 * 推广表
	 */
	private Integer extensionId;
	
	/**
	 * 推广时长
	 */
	private Integer extensionDuration;
	
	/**
	 * 推广价格
	 */
	private BigDecimal extensionPrice;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	public Extension(){
		super();
	}

	/**
	 * 获取推广表
	 * 
	 * @return Integer 推广表
	 */
	public Integer getExtensionId() {
		return extensionId;
	}
	
	/**
	 * 设置推广表
	 * 
	 * @param extensionId 推广表
	 */
	public void setExtensionId(Integer extensionId) {
		this.extensionId = extensionId;
	}
	
	/**
	 *   设置排序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	  * 获取排序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	/**
	 * 获取推广时长
	 * 
	 * @return Integer 推广时长
	 */
	public Integer getExtensionDuration() {
		return extensionDuration;
	}
	
	/**
	 * 设置推广时长
	 * 
	 * @param extensionDuration 推广时长
	 */
	public void setExtensionDuration(Integer extensionDuration) {
		this.extensionDuration = extensionDuration;
	}

	public BigDecimal getExtensionPrice() {
		return extensionPrice;
	}

	public void setExtensionPrice(BigDecimal extensionPrice) {
		this.extensionPrice = extensionPrice;
	}
	
	
	
}