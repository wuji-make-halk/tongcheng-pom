package com.micro.pmo.moudle.car.vo;

import java.io.Serializable;
import java.math.BigDecimal;
/***
 * 车辆发布时匹配预订收车
 * @author raoBo
 *
 */
public class ReserveCarVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6483315183672324778L;

	/***
	 * 预订收车id
	 */
	private Integer reserveId;
	/**
	 * 车辆颜色
	 */
	private String carColor;
	
	/**
	 * 车辆类型
	 */
	private String carType;
	
	/**
	 * 品牌车型
	 */
	private String brandSeries;
	
	/**
	 * 车辆里程
	 */
	private BigDecimal carMileage;
	
	/**
	 * 初次上牌时间：0 不限 1 1年内 2 2年内 3 三年内 4 4年内 5 五年内 6 6年内 7 6年以上
	 */
	private Integer carOldBoadTime;
	
	/**
	 * 使用性质
	 */
	private String carNature;
	
	/**
	 * 变速箱
	 */
	private String carGearbox;
	
	/**
	 * 地区市1
	 */
	private String regionCity;
	
	
	/**
	 * 地区市2
	 */
	private String region2City;
	
	private String cusNick;
	
	private String deviceCode;
	/***
	 * 设备类型 1 IOS设备  2 Android设备
	 */
	private Integer deviceType;

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public ReserveCarVo() {
		super();
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public BigDecimal getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(BigDecimal carMileage) {
		this.carMileage = carMileage;
	}

	public Integer getCarOldBoadTime() {
		return carOldBoadTime;
	}

	public void setCarOldBoadTime(Integer carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}

	public String getCarNature() {
		return carNature;
	}

	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}

	public String getCarGearbox() {
		return carGearbox;
	}

	public void setCarGearbox(String carGearbox) {
		this.carGearbox = carGearbox;
	}

	public String getRegionCity() {
		return regionCity;
	}

	public void setRegionCity(String regionCity) {
		this.regionCity = regionCity;
	}

	public String getRegion2City() {
		return region2City;
	}

	public void setRegion2City(String region2City) {
		this.region2City = region2City;
	}

	public Integer getReserveId() {
		return reserveId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}

	
	
}
