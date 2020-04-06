package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 预订收车Entity
 */
@Table(name = "reserve_car")
public class ReserveCar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 预定收车id
	 */
	private Integer reserveId;
	
	/**
	 * 车辆颜色
	 */
	@NotNull(message="车辆颜色不能为空")
	private String carColor;
	
	/**
	 * 车辆类型
	 */
	@NotNull(message="车辆类型不能为空")
	private String carType;
	
	/**
	 * 品牌车型
	 */
	@NotNull(message="品牌车型不能为空")
	private String brandSeries;
	
	/**
	 * 车辆里程
	 */
	@NotNull(message="车辆里程不能为空")
	private BigDecimal carMileage;
	
	/**
	 * 初次上牌时间：0 不限 1 1年内 2 2年内 3 三年内 4 4年内 5 五年内 6 6年内 7 6年以上
	 */
	@NotNull(message="初次上牌时间不能为空")
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
	 * 地区省1
	 */
	private String regionProvince;
	
	/**
	 * 地区市1
	 */
	@NotNull(message="市不能为空")
	private String regionCity;
	
	/**
	 * 地区省2
	 */
	private String region1Province;
	
	/**
	 * 地区市2
	 */
	private String region2City;
	
	/**
	 * 预订收车：0 初始状态 1 删除
	 */
	private Integer reserveStatus;

	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 完成时间
	 */
	private Date finishTime;
	
	
	public ReserveCar(){
		super();
	}

	
	
	
	/**
	 * 获取预定收车id
	 * 
	 * @return Integer 预定收车id
	 */
	public Integer getReserveId() {
		return reserveId;
	}
	
	/**
	 * 设置预定收车id
	 * 
	 * @param reserveId 预定收车id
	 */
	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}
	
	/**
	 * 获取车辆颜色
	 * 
	 * @return String 车辆颜色
	 */
	public String getCarColor() {
		return carColor;
	}
	
	/**
	 * 设置车辆颜色
	 * 
	 * @param carColor 车辆颜色
	 */
	public void setCarColor(String carColor) {
		this.carColor = carColor;
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
	 * 获取品牌车型
	 * 
	 * @return String 品牌车型
	 */
	public String getBrandSeries() {
		return brandSeries;
	}
	
	/**
	 * 设置品牌车型
	 * 
	 * @param brandSeries 品牌车型
	 */
	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}
	
	/**
	 * 获取车辆里程
	 * 
	 * @return BigDecimal 车辆里程
	 */
	public BigDecimal getCarMileage() {
		return carMileage;
	}
	
	/**
	 * 设置车辆里程
	 * 
	 * @param carMileage 车辆里程
	 */
	public void setCarMileage(BigDecimal carMileage) {
		this.carMileage = carMileage;
	}
	
	/**
	 * 获取初次上牌时间
	 * 
	 * @return Date 初次上牌时间
	 */
	public Integer getCarOldBoadTime() {
		return carOldBoadTime;
	}
	
	/**
	 * 设置初次上牌时间
	 * 
	 * @param carOldBoadTime 初次上牌时间
	 */
	public void setCarOldBoadTime(Integer carOldBoadTime) {
		this.carOldBoadTime = carOldBoadTime;
	}
	
	/**
	 * 获取使用性质
	 * 
	 * @return String 使用性质
	 */
	public String getCarNature() {
		return carNature;
	}
	
	/**
	 * 设置使用性质
	 * 
	 * @param carNature 使用性质
	 */
	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}
	
	/**
	 * 获取变速箱
	 * 
	 * @return String 变速箱
	 */
	public String getCarGearbox() {
		return carGearbox;
	}
	
	/**
	 * 设置变速箱
	 * 
	 * @param carGearbox 变速箱
	 */
	public void setCarGearbox(String carGearbox) {
		this.carGearbox = carGearbox;
	}
	
	/**
	 * 获取地区省1
	 * 
	 * @return String 地区省1
	 */
	public String getRegionProvince() {
		return regionProvince;
	}
	
	/**
	 * 设置地区省1
	 * 
	 * @param regionProvince 地区省1
	 */
	public void setRegionProvince(String regionProvince) {
		this.regionProvince = regionProvince;
	}
	
	/**
	 * 获取地区市1
	 * 
	 * @return String 地区市1
	 */
	public String getRegionCity() {
		return regionCity;
	}
	
	/**
	 * 设置地区市1
	 * 
	 * @param regionCity 地区市1
	 */
	public void setRegionCity(String regionCity) {
		this.regionCity = regionCity;
	}
	
	/**
	 * 获取地区省2
	 * 
	 * @return String 地区省2
	 */
	public String getRegion1Province() {
		return region1Province;
	}
	
	/**
	 * 设置地区省2
	 * 
	 * @param region1Province 地区省2
	 */
	public void setRegion1Province(String region1Province) {
		this.region1Province = region1Province;
	}
	
	/**
	 * 获取地区市2
	 * 
	 * @return String 地区市2
	 */
	public String getRegion2City() {
		return region2City;
	}
	
	/**
	 * 设置地区市2
	 * 
	 * @param region2City 地区市2
	 */
	public void setRegion2City(String region2City) {
		this.region2City = region2City;
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
	 * 获取完成时间
	 * 
	 * @return Date 完成时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	
	/**
	 * 设置完成时间
	 * 
	 * @param finishTime 完成时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	/**
	 * 获取预订收车：0 初始状态 1 达成 2 失败
	 * 
	 * @return Integer 预订收车：0 初始状态 1 达成 2 失败
	 */
	public Integer getReserveStatus() {
		return reserveStatus;
	}
	
	/**
	 * 设置预订收车：0 初始状态 1 达成 2 失败
	 * 
	 * @param reserveStatus 预订收车：0 初始状态 1 达成 2 失败
	 */
	public void setReserveStatus(Integer reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	/**
	 * 匹配小时间
	 */
	@JSONField(serialize = false)
	private Date minDate;
	/***
	 * 匹配最大时间
	 */
	@JSONField(serialize = false)
	private Date maxDate;

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