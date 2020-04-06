package com.micro.pmo.moudle.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.pmo.moudle.account.accountenum.AccountLogEnum;

/**
 * 用户账号流水表
 * @author wenhaofan 
 * 
 */
public class CusAccountLog  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*资金流水号
	*/
	private String  logId;
	/**
	*用户id
	*/
	private Integer  accountId;
	/**
	*支付来源（微信支付：0，支付宝支付：1，账号支付：2）
	*/
	private Integer  logSource;
	/**
	*流水类型（充值：0，退款：1，支付：2,正在充值：3）
	*/
	private Integer  flowType;
	/**
	 * 流水类型
	 */
	@JSONField(serialize=false)
	private AccountLogEnum accountLogEnum;
	/**
	*操作金额
	*/
	private BigDecimal  money;
	/**
	*操作后金额
	*/
	private BigDecimal  surplusMoney;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	/**
	 * 流水描述
	 */
	private String logRemark;
 
	/**
	 * 最后修改时间
	 */
	private Date gmtModify;
	
	/**
	 * 支付类型
	 */
	private String payType;
	
	/**
	 * 购买的商品名称
	 */
	private String productName;
 
	public CusAccountLog() {
		super();
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public CusAccountLog(Integer flowType, String orderId) {
		super();
		this.flowType = flowType;
		this.orderId = orderId;
	}


	public CusAccountLog(Integer flowType, BigDecimal surplusMoney, String orderId) {
		super();
		this.flowType = flowType;
		this.surplusMoney = surplusMoney;
		this.orderId = orderId;
	}


	public CusAccountLog(Integer accountId, Integer logSource,  BigDecimal money,
			BigDecimal surplusMoney, String orderId, String logRemark,Integer flowType,String productName,String payType) {
		super();
		this.accountId = accountId;
		this.logSource = logSource;
		this.money = money;
		this.surplusMoney = surplusMoney;
		this.orderId = orderId;
		this.logRemark = logRemark;
		this.flowType = flowType;
		this.productName  = productName;
		this.payType = payType;
	}
	
	
	public Date getGmtModify() {
		return gmtModify;
	}


	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}


	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public CusAccountLog setLogId(String logId) {
		this.logId=logId;
		return this;
	}

	public String getLogId() {
		return this.logId;
	}

	public CusAccountLog setAccountId(Integer accountId) {
		this.accountId=accountId;
		return this;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public CusAccountLog setLogSource(Integer logSource) {
		this.logSource=logSource;
		return this;
	}

	public Integer getLogSource() {
		return this.logSource;
	}

	public CusAccountLog setFlowType(Integer flowType) {
		setAccountLogEnum(AccountLogEnum.getEnumByFlowType(flowType));
		
		if(getAccountLogEnum()==null){
			return this;
		}
		
		this.flowType=getAccountLogEnum().getFlowType();
		return this;
	}

	public Integer getFlowType() {
		return this.flowType;
	}
 
	public AccountLogEnum getAccountLogEnum() {
		return accountLogEnum;
	}

	public void setAccountLogEnum(AccountLogEnum accountLogEnum) {
		this.accountLogEnum = accountLogEnum;
		this.flowType=getAccountLogEnum().getFlowType();
	}

	public CusAccountLog setMoney(BigDecimal money) {
		this.money=money;
		return this;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public CusAccountLog setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney=surplusMoney;
		return this;
	}

	public BigDecimal getSurplusMoney() {
		return this.surplusMoney;
	}

	public CusAccountLog setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}
}