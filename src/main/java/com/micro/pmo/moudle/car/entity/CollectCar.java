package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收车时效记录表 
 * @author wenhaofan 
 * 
 */
public class CollectCar  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*用户
	*/
	private Integer  collectId;
	/**
	*到期时间
	*/
	private Date  expirationDate;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*创建时间
	*/
	private Date  createTime;
	/**
	*购买状态：0 正常 1 失效
	*/
	private Integer  cusCollectStatus;
	/**
	*购买人
	*/
	private Integer  buyer;

	public CollectCar() {
		super();
	}

	public CollectCar(Date expirationDate, Integer cusId, Date createTime) {
		super();
		this.expirationDate = expirationDate;
		this.cusId = cusId;
		this.createTime = createTime;
	}

	public CollectCar setCollectId(Integer collectId) {
		this.collectId=collectId;
		return this;
	}

	public Integer getCollectId() {
		return this.collectId;
	}

	public CollectCar setExpirationDate(Date expirationDate) {
		this.expirationDate=expirationDate;
		return this;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public CollectCar setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public CollectCar setCreateTime(Date createTime) {
		this.createTime=createTime;
		return this;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public CollectCar setCusCollectStatus(Integer cusCollectStatus) {
		this.cusCollectStatus=cusCollectStatus;
		return this;
	}

	public Integer getCusCollectStatus() {
		return this.cusCollectStatus;
	}

	public CollectCar setBuyer(Integer buyer) {
		this.buyer=buyer;
		return this;
	}

	public Integer getBuyer() {
		return this.buyer;
	}
}