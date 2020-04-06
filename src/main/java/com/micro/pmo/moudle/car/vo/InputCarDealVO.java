package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.car.entity.CarDeal;

/** 
 * 成交价入参
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月15日 
*/
public class InputCarDealVO extends CarDeal{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4186688533842295168L;


	/**
	 * 车辆id
	 */
	@NotNull(message ="车辆id不能为空")
	private Integer carId;
	

	/**
	 * 车辆成交价
	 */
	@NotNull(message ="车辆成交价不能为空")
	private BigDecimal transactionPrice;
	
	/***
	 * 卖出类型：1 零售 2 批发
	 */
	@NotNull(message ="卖出类型")
	private Integer sellType;
	
	/**
	 * 用户名称
	 */
	private String name;
	
	/**
	 * 手机号
	 */
	private String phone;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
 
	
}
