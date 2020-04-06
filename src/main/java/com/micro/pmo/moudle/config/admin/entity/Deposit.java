package com.micro.pmo.moudle.config.admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 订金配置表Entity
 */
@Table(name = "sys_deposit")
public class Deposit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 订金id
	 */
	private Integer depositId;
	
	/**
	 * 订金金额
	 */
	@NotNull(message = "定金金额不能为空")
	private BigDecimal collectMoney;
	
	/**
	 * 正常退还金额
	 */
	@NotNull(message = "退款金额不能为空")
	private BigDecimal refundMoney;
	
	/***
	 * 异常退款
	 */
	@NotNull(message = "异常退款金额不能为空")
	private BigDecimal abnormalRefund;
	
	/**
	 * 手续费扣费费率
	 */
	private Integer chargeRatio;
	
	public Deposit(){
		super();
	}

	public Integer getChargeRatio() {
		return chargeRatio;
	}
 
	public void setChargeRatio(Integer chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

	/**
	 * 获取订金id
	 * 
	 * @return Integer 订金id
	 */
	public Integer getDepositId() {
		return depositId;
	}
	
	/**
	 * 设置订金id
	 * 
	 * @param depositId 订金id
	 */
	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public BigDecimal getCollectMoney() {
		return collectMoney;
	}

	public void setCollectMoney(BigDecimal collectMoney) {
		this.collectMoney = collectMoney;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public BigDecimal getAbnormalRefund() {
		return abnormalRefund;
	}

	public void setAbnormalRefund(BigDecimal abnormalRefund) {
		this.abnormalRefund = abnormalRefund;
	}
	
}