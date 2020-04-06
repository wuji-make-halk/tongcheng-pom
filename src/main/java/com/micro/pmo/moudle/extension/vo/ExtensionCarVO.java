package com.micro.pmo.moudle.extension.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月5日
 */
public class ExtensionCarVO {

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
	private String carMieage;
	/**
	 * 初次上牌时间
	 */
	private Date carOldBoadTime;
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
	 * 批发价格
	 */
	private BigDecimal wholesalePrice;
	
	/***
	 * 成本价
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
	/**
	 * 微店:1 发布 2 不发布
	 */
	private Integer microShop;
	/**
	 * 平台：1 发布 2 不发布
	 */
	private Integer platform;
 
	/**
	 * 车辆的推广id
	 */
	private Integer carExtensionId;

	/**
	 * 失效时间
	 */
	@JSONField(serialize = false)
	private Date expireDate;
 
	/**
	 * 0 为推广 1为刷新 2为未推广未刷新  默认为2
	 */
	private Integer type = 2;
	
	
	public Boolean getIsFlush(){
		if(type != null && type == 1){
			return true;
		}
		return false;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		if (type == null ) {
			type = 2;
		}
		this.type = type;
	}

	/**
	 * 返回失效时间 分钟单位
	 * 
	 * @return
	 */
	public long getExpireMinute() {
		if (expireDate == null) {
			return 0;
		}
		long expireTime = expireDate.getTime() - System.currentTimeMillis();
		return expireTime / (1000 * 60);
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public Integer getCarExtensionId() {
		return carExtensionId;
	}

	public void setCarExtensionId(Integer carExtensionId) {
		this.carExtensionId = carExtensionId;

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

	public String getCarMieage() {
		return carMieage;
	}

	public void setCarMieage(String carMieage) {
		this.carMieage = carMieage;
	}

	

	public Date getCarOldBoadTime() {
		return carOldBoadTime;
	}

	public void setCarOldBoadTime(Date carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
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



	public BigDecimal getInterPrice() {
		return interPrice;
	}

	public void setInterPrice(BigDecimal interPrice) {
		this.interPrice = interPrice;
	}

	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
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

 
 
}
