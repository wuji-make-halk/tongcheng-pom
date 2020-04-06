package com.micro.pmo.moudle.vip.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * vip配置表Entity
 */
@Table(name = "sys_vip")
public class Vip implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * vip主id
	 */
	private Integer vipId;
	
	/**
	 * vip 价格
	 */
	@NotNull(message = "VIP购买价格不能为空")
	private BigDecimal vipPrice;
	
	/**
	 * 时长 单位：月
	 */
	@NotNull(message = "VIP时长不能为空")
	private Integer vipDuration;
	/**
	 * 排序 从大到小
	 */
	@NotNull(message = "VIP排序不能为空")
	private Integer sort;
	
	/**
	 * VIP标题
	 */
	@NotNull(message = "VIP标题不能为空")
	private String vipTitle;
	
	public Vip(){
		super();
	}

	public String getVipTitle() {
		return vipTitle;
	}

	public void setVipTitle(String vipTitle) {
		this.vipTitle = vipTitle;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取vip主id
	 * 
	 * @return Integer vip主id
	 */
	public Integer getVipId() {
		return vipId;
	}
	
	/**
	 * 设置vip主id
	 * 
	 * @param vipId vip主id
	 */
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	
	public BigDecimal getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}

	/**
	 * 获取时长
	 * 
	 * @return Integer 时长
	 */
	public Integer getVipDuration() {
		return vipDuration;
	}
	
	/**
	 * 设置时长
	 * 
	 * @param vipDuration 时长
	 */
	public void setVipDuration(Integer vipDuration) {
		this.vipDuration = vipDuration;
	}
	
}