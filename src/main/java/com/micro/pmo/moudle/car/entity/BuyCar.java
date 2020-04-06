package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 收车Entity
 */
@Table(name = "buy_car")
public class BuyCar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 收车Id
	 */
	private Integer buyCarId;
	
	/***
	 * 车身颜色
	 */
	private String carColour;
	/**
	 * 车辆车型
	 */
	@NotNull(message = "车型不能为空")
	private String carType;
	
	/**
	 * 出厂日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date factoryTime;
	
	/**
	 * 上牌时间
	 */
	@NotNull(message = "上牌时间不能为空")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date boadTime;
	
	/**
	 * 描述
	 */
	@Length(max = 501,message = "描述不能超过500字")
	private String description;
	
	/**
	 * 车主联系方式
	 */
	@NotNull(message = "车主联系方式不能为空")
	private String phone;
	
	/**
	 * 期望价格
	 */
	@NotNull(message = "期望价格不能为空")
	private BigDecimal hopePrice;
	
	/**
	 *  0 初始状态 1成交 2失败
	 */
	private Integer buyStatus;
	
	/**
	 * 成交价格
	 */
	private BigDecimal clinchPrice;
	
	/**
	 * 车辆照片
	 */
	@NotNull(message = "车辆照片不能为空")
	private String carImg1;
	
	/**
	 * 车辆照片
	 */
	private String carImg2;
	
	/**
	 * 车辆照片
	 */
	private String carImg3;
	
	/**
	 * 创建者id
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	@Transient
	private String cusNike;
	
	public BuyCar(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getBuyCarId() {
		return buyCarId;
	}
	
	/**
	 * 设置
	 * 
	 * @param buyCarId 
	 */
	public void setBuyCarId(Integer buyCarId) {
		this.buyCarId = buyCarId;
	}
	
	/**
	 * 获取车辆类型
	 * 
	 * @return String 车辆类型
	 */
	public String getCarType() {
		return carType;
	}
	
	/**
	 * 设置车辆类型
	 * 
	 * @param carType 车辆类型
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	/**
	 * 获取出厂日期
	 * 
	 * @return Date 出厂日期
	 */
	public Date getFactoryTime() {
		return factoryTime;
	}
	
	/**
	 * 设置出厂日期
	 * 
	 * @param factoryTime 出厂日期
	 */
	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}
	
	/**
	 * 获取上牌时间
	 * 
	 * @return Date 上牌时间
	 */
	public Date getBoadTime() {
		return boadTime;
	}
	
	/**
	 * 设置上牌时间
	 * 
	 * @param boadTime 上牌时间
	 */
	public void setBoadTime(Date boadTime) {
		this.boadTime = boadTime;
	}
	
	/**
	 * 获取描述
	 * 
	 * @return String 描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置描述
	 * 
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取车主联系方式
	 * 
	 * @return String 车主联系方式
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置车主联系方式
	 * 
	 * @param phone 车主联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取期望价格
	 * 
	 * @return BigDecimal 期望价格
	 */
	public BigDecimal getHopePrice() {
		if(hopePrice != null){
			return hopePrice.stripTrailingZeros();
		}
		return hopePrice;
	}
	
	/**
	 * 设置期望价格
	 * 
	 * @param hopePrice 期望价格
	 */
	public void setHopePrice(BigDecimal hopePrice) {
		this.hopePrice = hopePrice;
	}
	
	/**
	 * 获取 0 初始状态 1成交 2失败
	 * 
	 * @return Integer  0 初始状态 1成交 2失败
	 */
	public Integer getBuyStatus() {
		return buyStatus;
	}
	
	/**
	 * 设置 0 初始状态 1成交 2失败
	 * 
	 * @param buyStatus  0 初始状态 1成交 2失败
	 */
	public void setBuyStatus(Integer buyStatus) {
		this.buyStatus = buyStatus;
	}
	
	/**
	 * 获取成交价格
	 * 
	 * @return BigDecimal 成交价格
	 */
	public BigDecimal getClinchPrice() {
		if(clinchPrice != null){
			return clinchPrice.stripTrailingZeros();
		}
		return clinchPrice;
	}
	
	/**
	 * 设置成交价格
	 * 
	 * @param clinchPrice 成交价格
	 */
	public void setClinchPrice(BigDecimal clinchPrice) {
		this.clinchPrice = clinchPrice;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg1() {
		return carImg1;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg1 车辆照片
	 */
	public void setCarImg1(String carImg1) {
		this.carImg1 = carImg1;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg2() {
		return carImg2;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg2 车辆照片
	 */
	public void setCarImg2(String carImg2) {
		this.carImg2 = carImg2;
	}
	
	/**
	 * 获取车辆照片
	 * 
	 * @return String 车辆照片
	 */
	public String getCarImg3() {
		return carImg3;
	}
	
	/**
	 * 设置车辆照片
	 * 
	 * @param carImg3 车辆照片
	 */
	public void setCarImg3(String carImg3) {
		this.carImg3 = carImg3;
	}
	
	/**
	 * 获取创建者id
	 * 
	 * @return Integer 创建者id
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建者id
	 * 
	 * @param creator 创建者id
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	/**
	 * 获取创建时间
	 * 
	 * @return Date 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * 
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCusNike() {
		return cusNike;
	}
	public void setCusNike(String cusNike) {
		this.cusNike = cusNike;
	}

	public String getCarColour() {
		return carColour;
	}

	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}
	
}