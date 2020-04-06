package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 车辆推广Entity
 */
@Table(name = "car_extension")
public class CarExtension implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 车辆推广ids
	 */
	private Integer carExtensionId;
	
	/**
	 * 推广到期时间
	 */
	private Date extensionExpirationDate;
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	
	/**
	 * 购买人
	 */
	private Integer buyer;
	
	/**
	 * ip购买状态：0 正常 1 失效
	 */
	private Integer carExtensionStatus;
	
	/**
	 * 推广id
	 */
	private Integer extensionId;
	
	/**
	 * 操作类型，0为购买，1为赠送
	 */
	private Integer operationType;
	
	/**
	 * 赠送天数
	 */
	private Integer sendNum;
	
	/**
	 * 最后推广时间
	 */
	private  Date gmtUpdate;
	
	/**
	 * 0推广 1为刷新
	 */
	private Integer type;
	
	public CarExtension(){
		super();
	}

	public CarExtension(Date extensionExpirationDate, String orderId, Integer carId, Integer buyer,
			Integer carExtensionStatus, Date gmtUpdate , Integer type , Integer operationType,Integer day ) {
		super();
		this.extensionExpirationDate = extensionExpirationDate;
		this.orderId = orderId;
		this.carId = carId;
		this.buyer = buyer;
		this.carExtensionStatus = carExtensionStatus;
		this.gmtUpdate = gmtUpdate;
		this.type = type;
		this.operationType = operationType;
		this.sendNum = day;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOperationType() {
		return operationType;
	}
	
	public Date getGmtUpdate() {
		return gmtUpdate;
	}


	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}


	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}


	public Integer getSendNum() {
		return sendNum;
	}


	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}


	/**
	 * 获取车辆推广id
	 * 
	 * @return Integer 车辆推广id
	 */
	public Integer getCarExtensionId() {
		return carExtensionId;
	}
	
	/**
	 * 设置车辆推广id
	 * 
	 * @param carExtensionId 车辆推广id
	 */
	public void setCarExtensionId(Integer carExtensionId) {
		this.carExtensionId = carExtensionId;
	}
	
	/**
	 * 获取推广到期时间
	 * 
	 * @return Date 推广到期时间
	 */
	public Date getExtensionExpirationDate() {
		return extensionExpirationDate;
	}
	
	/**
	 * 设置推广到期时间
	 * 
	 * @param extensionExpirationDate 推广到期时间
	 */
	public void setExtensionExpirationDate(Date extensionExpirationDate) {
		this.extensionExpirationDate = extensionExpirationDate;
	}
	
	/**
	 * 获取订单id
	 * 
	 * @return String 订单id
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置订单id
	 * 
	 * @param orderId 订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	 * 获取购买人
	 * 
	 * @return Integer 购买人
	 */
	public Integer getBuyer() {
		return buyer;
	}
	
	/**
	 * 设置购买人
	 * 
	 * @param buyer 购买人
	 */
	public void setBuyer(Integer buyer) {
		this.buyer = buyer;
	}
	
	/**
	 * 获取ip购买状态：0 正常 1 失效
	 * 
	 * @return Integer ip购买状态：0 正常 1 失效
	 */
	public Integer getCarExtensionStatus() {
		return carExtensionStatus;
	}
	
	/**
	 * 设置ip购买状态：0 正常 1 失效
	 * 
	 * @param carExtensionStatus ip购买状态：0 正常 1 失效
	 */
	public void setCarExtensionStatus(Integer carExtensionStatus) {
		this.carExtensionStatus = carExtensionStatus;
	}
	
	/**
	 * 获取推广id
	 * 
	 * @return Integer 推广id
	 */
	public Integer getExtensionId() {
		return extensionId;
	}
	
	/**
	 * 设置推广id
	 * 
	 * @param extensionId 推广id
	 */
	public void setExtensionId(Integer extensionId) {
		this.extensionId = extensionId;
	}
	
}