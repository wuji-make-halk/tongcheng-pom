package com.micro.pmo.moudle.profit.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * 利润Entity
 */
@Table(name = "profit")
public class Profit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	private Integer profitId;
	
	/**
	 * 利润来源：0 vip 1 推广 2 订金 3 第三方
	 */
	private Integer profitSource;
	
	/**
	 * 订单
	 */
	private String orderId;
	
	/**
	 * 
	 */
	private BigDecimal money;
	
	/**
	 * 时间
	 */
	private Date createTime;
	
	/**
	 * 用户
	 */
	private Integer cusId;
	
	public Profit(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getProfitId() {
		return profitId;
	}
	
	/**
	 * 设置
	 * 
	 * @param profitId 
	 */
	public void setProfitId(Integer profitId) {
		this.profitId = profitId;
	}
	
	/**
	 * 获取利润来源：0 vip 1 推广 2 订金 3 第三方
	 * 
	 * @return Integer 利润来源：0 vip 1 推广 2 订金 3 第三方
	 */
	public Integer getProfitSource() {
		return profitSource;
	}
	
	/**
	 * 设置利润来源：0 vip 1 推广 2 订金 3 第三方
	 * 
	 * @param profitSource 利润来源：0 vip 1 推广 2 订金 3 第三方
	 */
	public void setProfitSource(Integer profitSource) {
		this.profitSource = profitSource;
	}
	
	/**
	 * 获取订单
	 * 
	 * @return String 订单
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置订单
	 * 
	 * @param orderId 订单
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 获取
	 * 
	 * @return BigDecimal 
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * 设置
	 * 
	 * @param money 
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	/**
	 * 获取时间
	 * 
	 * @return Date 时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置时间
	 * 
	 * @param createTime 时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取用户
	 * 
	 * @return Integer 用户
	 */
	public Integer getCusId() {
		return cusId;
	}
	
	/**
	 * 设置用户
	 * 
	 * @param cusId 用户
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	
}