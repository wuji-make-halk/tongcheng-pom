package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 买车记录Entity
 */
@Table(name = "sell_car")
public class SellCar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 买车id
	 */
	private Integer sellId;
	
	/**
	 * 要求
	 */
	@NotNull(message="要求不能为空")
	@Length(min = 1,max = 501,message = "要求只允许输入1~500的文字")
	private String claim;
	
	/**
	 * 买主联系方式
	 */
	@NotNull(message="电话不能为空")
	private String phone;
	
	/**
	 * 期望价格
	 */
	@NotNull(message="期望价格不能为空")
	private BigDecimal hopePrice;
	
	/**
	 * 车辆照片
	 */
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
	
	/**
	 * 买车状态：0 初始状态 1成交 2失败
	 */
	private Integer sellStatus;
	/***
	 * 成交价
	 */
	private BigDecimal realPrice;
	
	/***
	 * 用户昵称
	 */
	@Transient
	private String cusNike;
	
	public SellCar(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getSellId() {
		return sellId;
	}
	
	/**
	 * 设置
	 * 
	 * @param sellId 
	 */
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	
	/**
	 * 获取要求
	 * 
	 * @return String 要求
	 */
	public String getClaim() {
		return claim;
	}
	
	/**
	 * 设置要求
	 * 
	 * @param claim 要求
	 */
	public void setClaim(String claim) {
		this.claim = claim;
	}
	
	/**
	 * 获取买主联系方式
	 * 
	 * @return String 买主联系方式
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置买主联系方式
	 * 
	 * @param phone 买主联系方式
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
	
	/**
	 * 获取买车状态：0 初始状态 成交 2失败
	 * 
	 * @return Integer 买车状态：0 初始状态 成交 2失败
	 */
	public Integer getSellStatus() {
		return sellStatus;
	}
	
	/**
	 * 设置买车状态：0 初始状态 成交 2失败
	 * 
	 * @param sellStatus 买车状态：0 初始状态 成交 2失败
	 */
	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}

	public BigDecimal getRealPrice() {
		if(realPrice != null){
			return realPrice.stripTrailingZeros();
		}
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public String getCusNike() {
		return cusNike;
	}

	public void setCusNike(String cusNike) {
		this.cusNike = cusNike;
	}
	
}