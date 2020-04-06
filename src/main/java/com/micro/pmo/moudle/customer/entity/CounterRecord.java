package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.util.Date;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;

/**
 * CounterRecord
 * 
 * @author
 * 
 */
public class CounterRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private Integer recordId;
	/**
	 * 操作人id
	 */
	private Integer userId;
	/**
	 * 赠送对象id
	 */
	private Integer cusId;
	/**
	 * 类型
	 */
	private CounterTypeEnum counterType;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
 
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 赠送的配置id
	 */
	private Integer configId;
	/**
	 * 赠送数量
	 */
	private Integer sendNum;
	/**
	 * 0为赠送，1为购买，2为使用
	 */
	private Integer operateType;

	private String orderId;
	
	public CounterRecord() {
		super();
	}

	public CounterRecord(Integer cusId, CounterTypeEnum counterType, Date gmtCreate, Integer configId,
			Integer sendNum, Integer operateType,String orderId) {
		super();
		this.cusId = cusId;
		this.counterType = counterType;
		this.gmtCreate = gmtCreate;
		this.configId = configId;
		this.sendNum = sendNum;
		this.operateType = operateType;
		this.orderId = orderId;
	}
	
	public CounterRecord(Integer userId, Integer cusId, CounterTypeEnum counterType, Date gmtCreate, Integer configId,
			Integer sendNum, Integer operateType) {
		super();
		this.userId = userId;
		this.cusId = cusId;
		this.counterType = counterType;
		this.gmtCreate = gmtCreate;
		this.configId = configId;
		this.sendNum = sendNum;
		this.operateType = operateType;
	}
	

	public CounterRecord(Integer cusId, CounterTypeEnum counterType, Date gmtCreate,  Integer operateType) {
		super();
		this.cusId = cusId;
		this.counterType = counterType;
		this.gmtCreate = gmtCreate;
		this.operateType = operateType;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public CounterRecord setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public CounterRecord setCusId(Integer cusId) {
		this.cusId = cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public CounterRecord setCounterType(String counterType) {
		this.counterType = CounterTypeEnum.getEnumByValue(counterType);
		return this;
	}
 

	public void setCounterType(CounterTypeEnum counterType) {
		this.counterType = counterType;
	}

	public CounterTypeEnum getCounterType() {
		return this.counterType;
	}

	public CounterRecord setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
 
	public CounterRecord setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public CounterRecord setConfigId(Integer configId) {
		this.configId = configId;
		return this;
	}

	public Integer getConfigId() {
		return this.configId;
	}

	public CounterRecord setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
		return this;
	}

	public Integer getSendNum() {
		return this.sendNum;
	}

	public CounterRecord setOperateType(Integer operateType) {
		this.operateType = operateType;
		return this;
	}

	public Integer getOperateType() {
		return this.operateType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}