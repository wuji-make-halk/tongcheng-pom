package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月5日
 */
public class FindCarVO {
	/**
	 * 车辆id
	 */
	private Integer carId;
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
	private BigDecimal carMieage;
	/**
	 * 车辆所在地
	 */
	private String provinceLocation;
	/**
	 * 车辆所在城市
	 */
	private String cityLocation;
	/**
	 * 网络标价
	 */
	private BigDecimal interPrice;
	/**
	 * 成本价格
	 */
	private BigDecimal costPrice;
	/**
	 * 车辆照片
	 */
	private String carImg1;
	/**
	 * 排放标准
	 */
	private String carDischarge;
 
	/****
	 * 时间
	 */
	private Date createTime;
 
	/***
	 * 初次上牌时间
	 */
	private Date carOldBoadTime;
	
	/**
	 * 微店:1 发布 2 不发布
	 */
	private Integer microShop;
	
	/**
	 * 平台：1 发布 2 不发布
	 */
	private Integer platform;

	/**
	 * 批发价
	 */
	private BigDecimal wholesalePrice;
	
	/**
	 * 是否有订单绑定： true 是 false 不是
	 */
	private Boolean isOrder;
	
	/***
	 * 创建人
	 */
	@JSONField(serialize = false)
	private Integer creator;
	
	/***
	 * 是否可以下架
	 */
	private Boolean isHandle;

	/**
	 * 最后刷新或推广的时间
	 */
	private Date lastExtensionDate;
	
	/**
	 * 刷新或推广的类型
	 */
	private Integer extensionType;
	
	/**
	 * 车龄
	 */
	private Integer carAge;
	
	/***
	 * 微店id
	 */
	private Integer storeId;

	public Integer getCarAge() {
		return carAge;
	}

	public void setCarAge(Integer carAge) {
		this.carAge = carAge;
	}

	public Date getLastExtensionDate() {
		return lastExtensionDate;
	}

	public void setLastExtensionDate(Date lastExtensionDate) {
		this.lastExtensionDate = lastExtensionDate;
	}

	public Integer getExtensionType() {
		return extensionType;
	}

	public void setExtensionType(Integer extensionType) {
		this.extensionType = extensionType;
	}

	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public Integer getMicroShop() {
		return microShop;
	}

	public void setMicroShop(Integer microShop) {
		this.microShop = microShop;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}



	public String getProvinceLocation() {
		return provinceLocation;
	}

	public void setProvinceLocation(String provinceLocation) {
		this.provinceLocation = provinceLocation;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}


	public String getCarImg1() {
		return carImg1;
	}

	public void setCarImg1(String carImg1) {
		this.carImg1 = carImg1;
	}

	public String getCarDischarge() {
		return carDischarge;
	}

	public void setCarDischarge(String carDischarge) {
		this.carDischarge = carDischarge;
	}
 

	public Date getCarOldBoadTime() {
		return carOldBoadTime;
	}

	public void setCarOldBoadTime(Date carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}


	public Boolean getIsHandle() {
		if(isHandle == null){
			return true;
		}
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}
	
	public Boolean getIsOrder() {
		if(isOrder == null){
			return false;
		}
		return isOrder;
	}

	public void setIsOrder(Boolean isOrder) {
		this.isOrder = isOrder;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getCarMieage() {
		if(carMieage != null){
			return carMieage.stripTrailingZeros();
		}
		return carMieage;
	}

	public void setCarMieage(BigDecimal carMieage) {
		this.carMieage = carMieage;
	}

	public BigDecimal getInterPrice() {
		if(interPrice != null){
			return interPrice.stripTrailingZeros();
		}
		return interPrice;
	}

	public void setInterPrice(BigDecimal interPrice) {
		this.interPrice = interPrice;
	}

	public BigDecimal getCostPrice() {
		if(costPrice != null){
			return costPrice.stripTrailingZeros();
		}
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

}
