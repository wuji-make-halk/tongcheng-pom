package com.micro.pmo.moudle.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  用户账号表 
 * @author wenhaofan 
 * 
 */
public class CusAccount  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*账号主键
	*/
	private Integer  accountId;
	/**
	*充值总金额
	*/
	private BigDecimal  totalMoney;
	/**
	*剩余金额
	*/
	private BigDecimal  surplusMoney;
	/**
	*冻结金额
	*/
	private BigDecimal  freezeMoney;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	/**
	*最后交易时间
	*/
	private Date  gmtLastdeal;
	

	public CusAccount setAccountId(Integer accountId) {
		this.accountId=accountId;
		return this;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public CusAccount setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney=totalMoney;
		return this;
	}

	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public CusAccount setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney=surplusMoney;
		return this;
	}

	public BigDecimal getSurplusMoney() {
		return this.surplusMoney;
	}

	public CusAccount setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney=freezeMoney;
		return this;
	}

	public BigDecimal getFreezeMoney() {
		return this.freezeMoney;
	}

	public CusAccount setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public CusAccount setGmtLastdeal(Date gmtLastdeal) {
		this.gmtLastdeal=gmtLastdeal;
		return this;
	}

	public Date getGmtLastdeal() {
		return this.gmtLastdeal;
	}
}