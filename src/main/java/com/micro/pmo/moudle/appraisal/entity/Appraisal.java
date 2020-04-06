package com.micro.pmo.moudle.appraisal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 车估价Entity
 */
@Table(name = "appraisal")
public class Appraisal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 车评估id
	 */
	private Integer appraisalId;
	
	/**
	 * 车辆类型
	 */
	@NotNull(message = "车辆类型不能为空")
	private String carType;
	
	/**
	 * 上牌时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "上牌时间不能为空")
	private Date boardTime;
	
	/**
	 * 行驶里程
	 */
	@NotNull(message = "行驶里程不能为空")
	private BigDecimal mileage;
	
	/**
	 * 联系电话
	 */
	@Length(max=11,message="请填写11位的电话号码")
	private String contactPhone;
	
	/***
	 * 描述
	 */
	@Length(max=500,message = "描述最大不超过500字")
	private String appraisalDescribe;
	
	/**
	 * 创建者id
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/***
	 * 微店id
	 */
	@NotNull(message = "微店id不能为空")
	private Integer storeId;
	
	/***
	 * 推送人id
	 */
	@NotNull(message = "推送人id不能为空")
	private Integer pushCusId;
	
	
	public Appraisal(){
		super();
	}

	
	
	
	/**
	 * 获取车评估id
	 * 
	 * @return Integer 车评估id
	 */
	public Integer getAppraisalId() {
		return appraisalId;
	}
	
	/**
	 * 设置车评估id
	 * 
	 * @param appraisalId 车评估id
	 */
	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
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
	 * 获取上牌时间
	 * 
	 * @return Date 上牌时间
	 */
	public Date getBoardTime() {
		return boardTime;
	}
	
	/**
	 * 设置上牌时间
	 * 
	 * @param boardTime 上牌时间
	 */
	public void setBoardTime(Date boardTime) {
		this.boardTime = boardTime;
	}
	
	/**
	 * 获取行驶里程
	 * 
	 * @return BigDecimal 行驶里程
	 */
	public BigDecimal getMileage() {
		return mileage;
	}
	
	/**
	 * 设置行驶里程
	 * 
	 * @param mileage 行驶里程
	 */
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	
	/**
	 * 获取联系电话
	 * 
	 * @return String 联系电话
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	
	/**
	 * 设置联系电话
	 * 
	 * @param contactPhone 联系电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	/**
	 * 获取创建者id
	 * 
	 * @return Integer 创建者id
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建者id
	 * 
	 * @param creator 创建者id
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPushCusId() {
		return pushCusId;
	}

	public void setPushCusId(Integer pushCusId) {
		this.pushCusId = pushCusId;
	}

	public String getAppraisalDescribe() {
		return appraisalDescribe;
	}

	public void setAppraisalDescribe(String appraisalDescribe) {
		this.appraisalDescribe = appraisalDescribe;
	}
	
}