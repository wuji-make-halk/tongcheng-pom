package com.micro.pmo.moudle.car.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 成交价查询入参
 * @author raoBo
 *
 */
public class DealVo {


	/***
	 * 初次上牌时间：0 不限 1 1年内 2 2年内 3 三年内 4 4年内 5 五年内 6 6年内 7 6年以上
	 */
	private Integer carOldBoadTime;
	
	/***
	 * 品牌车型
	 */
	private String brandSeries;

	/**
	 * 车身颜色
	 */
	private String carColor;

	/**
	 * 车辆历程
	 */
	private String carMileage;

	/**
	 * 车辆所在省
	 */
	private String provinceLocation;

	/**
	 * 车辆所在城市
	 */
	private String cityLocation;
	
	/***
	 * 最小时间
	 */
	@JSONField(serialize = false)
	private Date minDate;
	
	/***
	 * 最大时间
	 */
	@JSONField(serialize = false)
	private Date maxDate;

	public DealVo() {
		super();
	}


	public Integer getCarOldBoadTime() {
		return carOldBoadTime;
	}

	public void setCarOldBoadTime(Integer carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(String carMileage) {
		this.carMileage = carMileage;
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

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	
	
}
