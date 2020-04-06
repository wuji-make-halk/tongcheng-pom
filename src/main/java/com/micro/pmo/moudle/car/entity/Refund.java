package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * 退款申请Entity
 */
@Table(name = "refund")
public class Refund implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 退款id
	 */
	private String refundId;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundMoney;
	
	/**
	 * 
	 */
	private BigDecimal realRefundMoney;
	
	/**
	 * 申请人
	 */
	private Integer cusId;
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	
	/**
	 * 处理人
	 */
	private String handler;
	
	/**
	 * 
	 */
	private Date handleTime;
	
	/**
	 * 退款状态：0 申请中 1 处理中 2 已退款 3为退款失败
	 */
	private Integer refundStatus;
	

	public Refund(String refundId, BigDecimal refundMoney, BigDecimal realRefundMoney, Integer cusId, Integer carId,
			String orderId, Date applyTime, Integer refundStatus) {
		super();
		this.refundId = refundId;
		this.refundMoney = refundMoney;
		this.realRefundMoney = realRefundMoney;
		this.cusId = cusId;
		this.carId = carId;
		this.orderId = orderId;
		this.applyTime = applyTime;
		this.refundStatus = refundStatus;
	}

 
	public Refund(String refundId, Integer carId) {
		super();
		this.refundId = refundId;
		this.carId = carId;
	}
	
 
	public Refund(String refundId) {
		super();
		this.refundId = refundId;
	}

	public Refund(Integer carId) {
		super();
		this.carId = carId;
	}


	public Refund(){
		super();
	}
	
	/**
	 * 获取退款id
	 * 
	 * @return String 退款id
	 */
	public String getRefundId() {
		return refundId;
	}
	
	/**
	 * 设置退款id
	 * 
	 * @param refundId 退款id
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	/**
	 * 获取退款金额
	 * 
	 * @return BigDecimal 退款金额
	 */
	public BigDecimal getRefundMoney() {
		return refundMoney;
	}
	
	/**
	 * 设置退款金额
	 * 
	 * @param refundMoney 退款金额
	 */
	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
	
	/**
	 * 获取
	 * 
	 * @return BigDecimal 
	 */
	public BigDecimal getRealRefundMoney() {
		return realRefundMoney;
	}
	
	/**
	 * 设置
	 * 
	 * @param realRefundMoney 
	 */
	public void setRealRefundMoney(BigDecimal realRefundMoney) {
		this.realRefundMoney = realRefundMoney;
	}
	
	/**
	 * 获取申请人
	 * 
	 * @return Integer 申请人
	 */
	public Integer getCusId() {
		return cusId;
	}
	
	/**
	 * 设置申请人
	 * 
	 * @param cusId 申请人
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
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
	 * 获取申请时间
	 * 
	 * @return Date 申请时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	
	/**
	 * 设置申请时间
	 * 
	 * @param applyTime 申请时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	/**
	 * 获取处理人
	 * 
	 * @return String 处理人
	 */
	public String getHandler() {
		return handler;
	}
	
	/**
	 * 设置处理人
	 * 
	 * @param handler 处理人
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	/**
	 * 获取
	 * 
	 * @return Date 
	 */
	public Date getHandleTime() {
		return handleTime;
	}
	
	/**
	 * 设置
	 * 
	 * @param handleTime 
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	/**
	 * 获取退款状态：0 申请中 1 处理中 2 已退款
	 * 
	 * @return Integer 退款状态：0 申请中 1 处理中 2 已退款
	 */
	public Integer getRefundStatus() {
		return refundStatus;
	}
	
	/**
	 * 设置退款状态：0 申请中 1 处理中 2 已退款
	 * 
	 * @param refundStatus 退款状态：0 申请中 1 处理中 2 已退款
	 */
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	
}