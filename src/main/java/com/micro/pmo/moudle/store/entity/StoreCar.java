package com.micro.pmo.moudle.store.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 微店和车辆中间表Entity
 */
@Table(name = "store_car")
public class StoreCar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 微店与车辆中间表
	 */
	private Integer storeCarId;
	
	/**
	 * 微店id
	 */
	private Integer storeId;
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public StoreCar(){
		super();
	}

	
	
	
	public StoreCar(Integer storeId, Integer carId, Integer creator, Date createTime) {
		super();
		this.storeId = storeId;
		this.carId = carId;
		this.creator = creator;
		this.createTime = createTime;
	}




	/**
	 * 获取微店与车辆中间表
	 * 
	 * @return Integer 微店与车辆中间表
	 */
	public Integer getStoreCarId() {
		return storeCarId;
	}
	
	/**
	 * 设置微店与车辆中间表
	 * 
	 * @param storeCarId 微店与车辆中间表
	 */
	public void setStoreCarId(Integer storeCarId) {
		this.storeCarId = storeCarId;
	}
	
	/**
	 * 获取微店id
	 * 
	 * @return Integer 微店id
	 */
	public Integer getStoreId() {
		return storeId;
	}
	
	/**
	 * 设置微店id
	 * 
	 * @param storeId 微店id
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	/**
	 * 获取车辆id
	 * 
	 * @return Integer 车辆id
	 */
	public Integer getCarId() {
		return carId;
	}
	
	/**
	 * 设置车辆id
	 * 
	 * @param carId 车辆id
	 */
	public void setCarId(Integer carId) {
		this.carId = carId;
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
	
}