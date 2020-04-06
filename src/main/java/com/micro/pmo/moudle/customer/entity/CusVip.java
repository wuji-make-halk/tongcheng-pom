package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 用户vip表Entity
 */
@Table(name = "cus_vip")
public class CusVip implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 用户与vip主表
	 */
	private Integer cusVipId;
	
	/**
	 * vip到期时间
	 */
	private Date vipExpirationDate;
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * vip购买状态：0 正常 1 失效
	 */
	private Integer cusVipStatus;
	
	/**
	 * 购买人
	 */
	private Integer buyer;
	
	/**
	 * vipId
	 */
	private Integer vipId;
	
	public CusVip(){
		super();
	}
 
	
	
	public CusVip(Date vipExpirationDate, String orderId, Integer cusId, Date createTime,
			Integer cusVipStatus, Integer buyer) {
		super();
		this.vipExpirationDate = vipExpirationDate;
		this.orderId = orderId;
		this.cusId = cusId;
		this.createTime = createTime;
		this.cusVipStatus = cusVipStatus;
		this.buyer = buyer;
	}



	/**
	 * 获取用户与vip主表
	 * 
	 * @return Integer 用户与vip主表
	 */
	public Integer getCusVipId() {
		return cusVipId;
	}
	
	/**
	 * 设置用户与vip主表
	 * 
	 * @param cusVipId 用户与vip主表
	 */
	public void setCusVipId(Integer cusVipId) {
		this.cusVipId = cusVipId;
	}
	
	/**
	 * 获取vip到期时间
	 * 
	 * @return Date vip到期时间
	 */
	public Date getVipExpirationDate() {
		return vipExpirationDate;
	}
	
	/**
	 * 设置vip到期时间
	 * 
	 * @param vipExpirationDate vip到期时间
	 */
	public void setVipExpirationDate(Date vipExpirationDate) {
		this.vipExpirationDate = vipExpirationDate;
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
	 * 获取用户id
	 * 
	 * @return Integer 用户id
	 */
	public Integer getCusId() {
		return cusId;
	}
	
	/**
	 * 设置用户id
	 * 
	 * @param cusId 用户id
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
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
	 * 获取vip购买状态：0 正常 1 失效
	 * 
	 * @return Integer vip购买状态：0 正常 1 失效
	 */
	public Integer getCusVipStatus() {
		return cusVipStatus;
	}
	
	/**
	 * 设置vip购买状态：0 正常 1 失效
	 * 
	 * @param cusVipStatus vip购买状态：0 正常 1 失效
	 */
	public void setCusVipStatus(Integer cusVipStatus) {
		this.cusVipStatus = cusVipStatus;
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
	 * 获取vipId
	 * 
	 * @return Integer vipId
	 */
	public Integer getVipId() {
		return vipId;
	}
	
	/**
	 * 设置vipId
	 * 
	 * @param vipId vipId
	 */
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	
}