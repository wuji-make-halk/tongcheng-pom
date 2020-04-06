package com.micro.pmo.moudle.car.vo;

import java.util.Date;

public class CarInfoVo extends FindCarVO{

	/**
	 * 车辆照片
	 */
	private String carImg2;
	
	/**
	 * 车辆照片
	 */
	private String carImg3;
	
	/***
	 * 燃油类型
	 */
	private String fuelType;
	
	/**
	 * 车身颜色
	 */
	private String carColor;
	
	/***
	 * 车辆描述
	 */
	private String carDescribe;
	
	
	/***
	 * 车辆条数
	 */
	private Integer carSize;
	
	/***
	 * 拥有人
	 */
	private Integer creator;
	
	/***
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 发布者的手机号
	 */
	private String cusPhone;

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCarImg2() {
		return carImg2;
	}

	public void setCarImg2(String carImg2) {
		this.carImg2 = carImg2;
	}

	public String getCarImg3() {
		return carImg3;
	}

	public void setCarImg3(String carImg3) {
		this.carImg3 = carImg3;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getCarDescribe() {
		return carDescribe;
	}

	public void setCarDescribe(String carDescribe) {
		this.carDescribe = carDescribe;
	}

	public CarInfoVo() {
		super();
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}



	public Integer getCarSize() {
		return carSize;
	}

	public void setCarSize(Integer carSize) {
		this.carSize = carSize;
	}

	public Integer getCreator() {
		return creator;
	}


	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
