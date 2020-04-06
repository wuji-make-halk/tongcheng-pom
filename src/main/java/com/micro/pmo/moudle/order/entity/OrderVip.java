package com.micro.pmo.moudle.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderVip 
 * @author wenhaofan 
 * 
 */
public class OrderVip  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*订单id
	*/
	private String  orderVipId;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*vip单价
	*/
	private BigDecimal  vipPrice;
	/**
	*购买的vip配置id
	*/
	private Integer  vipConfigId;
	/**
	*vip购买数量
	*/
	private Integer  vipNum;
	/**
	*总金额
	*/
	private BigDecimal  totalAmoun;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	
	

	public OrderVip() {
		super();
	}

	public OrderVip(String orderVipId, Integer cusId, BigDecimal vipPrice, Integer vipConfigId, Integer vipNum,
			BigDecimal totalAmoun, Date gmtCreate) {
		super();
		this.orderVipId = orderVipId;
		this.cusId = cusId;
		this.vipPrice = vipPrice;
		this.vipConfigId = vipConfigId;
		this.vipNum = vipNum;
		this.totalAmoun = totalAmoun;
		this.gmtCreate = gmtCreate;
	}

	public OrderVip setOrderVipId(String orderVipId) {
		this.orderVipId=orderVipId;
		return this;
	}

	public String getOrderVipId() {
		return this.orderVipId;
	}

	public OrderVip setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public OrderVip setVipPrice(BigDecimal vipPrice) {
		this.vipPrice=vipPrice;
		return this;
	}

	public BigDecimal getVipPrice() {
		return this.vipPrice;
	}

	public OrderVip setVipConfigId(Integer vipConfigId) {
		this.vipConfigId=vipConfigId;
		return this;
	}

	public Integer getVipConfigId() {
		return this.vipConfigId;
	}

	public OrderVip setVipNum(Integer vipNum) {
		this.vipNum=vipNum;
		return this;
	}

	public Integer getVipNum() {
		return this.vipNum;
	}

	public OrderVip setTotalAmoun(BigDecimal totalAmoun) {
		this.totalAmoun=totalAmoun;
		return this;
	}

	public BigDecimal getTotalAmoun() {
		return this.totalAmoun;
	}

	public OrderVip setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}
}