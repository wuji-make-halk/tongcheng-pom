package com.micro.pmo.moudle.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;

/**
 * 订单Entity
 */
@Table(name = "cus_order")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 订单id
	 */
	private String orderId;
	
	/**
	 * 订单金额
	 */
	private BigDecimal orderMoney;
	
	/**
	 * 支付类型：vip购买：0，推广购买：1，刷新购买：2，账号充值：
	 * 3，预定收车：4，第三方保险查询：5，第三方维修记录：6
	 */
	private Integer paymentType;
	
	/**
	 * 支付方式： 0账号支付，1 微信小程序/H5支付，2 微信APP支付， 3 支付宝手机网站支付，4支付宝APP支付
	 */
	private Integer paymentMode;
	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/**
	 * 微信订单号
	 */
	private String weixinTransactionId;
	
	/**
	 * 微信预付款订单
	 */
	private String weixinPrepayId;
	/**
	 * 微信退款订单号
	 */
	private String weixinRefundId;
	
	/**
	 * 支付宝预支付订单id
	 */
	private String alipayPayId;
 
	/**
	 * 订单支付状态：0 待支付 1 支付成功 2 支付失败 3 已退款 4退款失败 5 已取消
	 */
	private Integer orderStatus;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 订单支付时间
	 */
	private Date finishTime;
	
	/**
	 * 退款时间
	 */
	private Date refundTime;
	

	
	/**
	 * 手续费
	 */
	private BigDecimal serviceCharge;
	
	/**
	 * 订单创建请求客户端ip
	 */
	private String createIp;
	/**
	 * 订单描述
	 */
	private String orderRemark;
	
	/***
	 * 用于支付
	 */
	private String openId;
	
	/**
	 * 商品名称
	 */
	private String productName;
	
	/**
	 * 支付类型
	 */
	private String payType;
	
	public Order(){
		super();
	}
	
	

	public Order(String orderId, Integer paymentMode, String createIp) {
		super();
		this.orderId = orderId;
		this.paymentMode = paymentMode;
		this.createIp = createIp;
	}



	public boolean isAccountPay() {
		return paymentMode == null ? false : paymentMode.equals(OrderPayModeEnum.ACCOUNT_PAY.getMode());
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



	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getWeixinTransactionId() {
		return weixinTransactionId;
	}

	public void setWeixinTransactionId(String weixinTransactionId) {
		this.weixinTransactionId = weixinTransactionId;
	}

	public String getWeixinPrepayId() {
		return weixinPrepayId;
	}

	public void setWeixinPrepayId(String weixinPrepayId) {
		this.weixinPrepayId = weixinPrepayId;
	}

	public String getWeixinRefundId() {
		return weixinRefundId;
	}

	public void setWeixinRefundId(String weixinRefundId) {
		this.weixinRefundId = weixinRefundId;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	
	public Order(BigDecimal orderMoney, Integer paymentType, Integer cusId, Integer orderStatus,String createIp , Integer paymentMode , String openId ,String productName , String payType) {
		super();
		this.orderMoney = orderMoney;
		this.paymentType = paymentType;
		this.cusId = cusId;
		this.orderStatus = orderStatus;
		this.createIp = createIp;
		this.paymentMode = paymentMode;
		//this.finishTime = new Date();
		this.openId = openId;
		this.productName = productName;
		this.payType = payType;
	}
	
	public Order(BigDecimal orderMoney, Integer paymentType, Integer cusId, Integer orderStatus) {
		super();
		this.orderMoney = orderMoney;
		this.paymentType = paymentType;
		this.cusId = cusId;
		this.orderStatus = orderStatus;
		//this.finishTime = new Date();
	}
	
	
	public Order(String orderId, String weixinRefundId, BigDecimal refundAmount, Date refundTime, Integer orderStatus) {
		super();
		this.orderId = orderId;
		this.weixinRefundId = weixinRefundId;
		this.refundAmount = refundAmount;
		this.refundTime = refundTime;
		this.orderStatus = orderStatus;
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
	 * 获取订单金额
	 * 
	 * @return BigDecimal 订单金额
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	
	/**
	 * 设置订单金额
	 * 
	 * @param orderMoney 订单金额
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	
	/**
	 * 支付类型：vip购买：0，推广购买：1，刷新购买：2，账号充值：3，预定收车：4，第三方保险查询：5，第三方维修记录：6
	 * @return
	 */
	public Integer getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(Integer paymentType) {
			this.paymentType = paymentType;
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
	 * 获取支付宝预支付订单id
	 * 
	 * @return String 支付宝预支付订单id
	 */
	public String getAlipayPayId() {
		return alipayPayId;
	}
	
	/**
	 * 设置支付宝预支付订单id
	 * 
	 * @param alipayPayId 支付宝预支付订单id
	 */
	public void setAlipayPayId(String alipayPayId) {
		this.alipayPayId = alipayPayId;
	}
	
	/**
	 * 获取订单支付状态：0 待支付 1 支付成功 2 支付失败 3 已退款
	 * 
	 * @return Integer 订单支付状态：0 待支付 1 支付成功 2 支付失败 3 已退款
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public String getOrderStatusStr() {
		OrderStatusEnum status = OrderStatusEnum.getByStatus(getOrderStatus());
		return status == null ? null :status.getTitle();
	}
	
	/**
	 * 设置订单支付状态：0 待支付 1 支付成功 2 支付失败 3 已退款
	 * 
	 * @param orderStatus 订单支付状态：0 待支付 1 支付成功 2 支付失败 3 已退款
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 获取
	 * 
	 * @return BigDecimal 
	 */
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	
	/**
	 * 设置退款金额
	 * 
	 * @param refundAmount 
	 */
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
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
	 * 获取
	 * 
	 * @return Date 
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	
	/**
	 * 设置
	 * 
	 * @param finishTime 
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	/**
	 * 获取退款时间
	 * 
	 * @return Date 退款时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	
	/**
	 * 设置退款时间
	 * 
	 * @param refundTime 退款时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	
	/**
	 * 获取支付方式： 0账号支付，1 微信小程序/H5支付，2 微信APP支付， 3 支付宝手机网站支付，4支付宝APP支付
	 * 
	 * @return Integer 支付方式：0账号支付，1 微信小程序/H5支付，2 微信APP支付， 3 支付宝手机网站支付，4支付宝APP支付
	 */
	public Integer getPaymentMode() {
		return paymentMode;
	}
	
	/**
	 * 设置支付方式：0账号支付，1 微信小程序/H5支付，2 微信APP支付， 3 支付宝手机网站支付，4支付宝APP支付
	 * 
	 * @param paymentMode 支付方式：0账号支付，1 微信小程序/H5支付，2 微信APP支付， 3 支付宝手机网站支付，4支付宝APP支付
	 */
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}