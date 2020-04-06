package com.micro.pmo.moudle.account.vo;

import java.math.BigDecimal;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月6日 
*/
public class AccountPayRefundVO {
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * 操作金额
	 */
	private BigDecimal money;
	/**
	 * 付款备注
	 */
	private String remark;
	/**
	 * 订单ID
	 */
	private String orderId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 支付类型
	 */
	private String payType;

	public AccountPayRefundVO(Integer cusId, BigDecimal money, String remark, String orderId, String productName,
			String payType) {
		super();
		this.cusId = cusId;
		this.money = money;
		this.remark = remark;
		this.orderId = orderId;
		this.productName = productName;
		this.payType = payType;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
}
