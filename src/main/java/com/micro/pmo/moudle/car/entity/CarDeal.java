package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * 车辆成交价Entity
 */
@Table(name = "car_deal")
public class CarDeal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 车辆成交id
	 */
	private Integer dealId;
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	

	/**
	 * 车辆成交价
	 */
	private BigDecimal transactionPrice;
	
	/**
	 * 成交价生成类型： 1客户 2 后台
	 */
	private Integer dealType;
	
	/***
	 * 卖出类型：1 零售 2 批发
	 */
	private Integer sellType;
	
	/**
	 * 用户名称
	 */
	private String name;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间：成交时间
	 */
	private Date createTime;
	
	public CarDeal(){
		super();
	}
	
	/**
	 * 获取车辆成交id
	 * 
	 * @return Integer 车辆成交id
	 */
	public Integer getDealId() {
		return dealId;
	}
	
	/**
	 * 设置车辆成交id
	 * 
	 * @param dealId 车辆成交id
	 */
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	
	/**
	 * 获取车辆id
	 * 
	 * @return Integer 车辆id
	 */
	public Integer getCarId() {
		return carId;
	}
	
	/**
	 * 设置车辆id
	 * 
	 * @param carId 车辆id
	 */
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	

	/**
	 * 获取车辆成交价
	 * 
	 * @return BigDecimal 车辆成交价
	 */
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	
	/**
	 * 设置车辆成交价
	 * 
	 * @param transactionPrice 车辆成交价
	 */
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	
	/**
	 * 获取成交价生成类型： 1系统 2 人工
	 * 
	 * @return Integer 成交价生成类型： 1系统 2 人工
	 */
	public Integer getDealType() {
		return dealType;
	}
	
	/**
	 * 设置成交价生成类型： 1系统 2 人工
	 * 
	 * @param dealType 成交价生成类型： 1系统 2 人工
	 */
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	
	/**
	 * 获取成交名称
	 * 
	 * @return String 成交名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置成交名称
	 * 
	 * @param name 成交名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取手机号
	 * 
	 * @return String 手机号
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置手机号
	 * 
	 * @param phone 手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取创建人
	 * 
	 * @return Integer 创建人
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建人
	 * 
	 * @param creator 创建人
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	/**
	 * 获取创建时间：成交时间
	 * 
	 * @return Date 创建时间：成交时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间：成交时间
	 * 
	 * @param createTime 创建时间：成交时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public CarDeal(Integer carId, BigDecimal transactionPrice, Integer dealType, Integer sellType, String name,
			String phone, Integer creator, Date createTime) {
		super();
		this.carId = carId;
		this.transactionPrice = transactionPrice;
		this.dealType = dealType;
		this.sellType = sellType;
		this.name = name;
		this.phone = phone;
		this.creator = creator;
		this.createTime = createTime;
	}
	
	
}