package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CarLowerVo {

	/***
	 * 车辆id
	 */
	@NotNull(message="车辆id不能为空")
	private Integer carId;
	/***
	 * 成交价格
	 */
	@NotNull(message="成交价不能为空")
	private BigDecimal transactionPrice;
	
	/***
	 * 卖出类型：1 零售 2 批发
	 */
	@NotNull(message="零售/批发不能为空")
	private Integer sellType;
	/***
	 * 姓名
	 */
	@Length(max=20,message = "姓名最多10个字")
	private String name;
	/***
	 * 电话号码
	 */
	@Length(max=11,message = "电话号码最多11位")
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
	public CarLowerVo() {
		super();
	}
	
	
	
	
}
