package com.micro.pmo.moudle.third.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderThirdApi 
 * @author wenhaofan 
 * 
 */
public class OrderThird  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*订单id
	*/
	private String  orderThirdId;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*调用单价
	*/
	private BigDecimal  thirdPrice;
 
	/**
	*总金额
	*/
	private BigDecimal  totalAmoun;
	
	/**
	 * 车架号
	 */
	private String vin;
  
	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
	
	public OrderThird() {
		super();
	}

	public OrderThird(String orderThirdId, Integer cusId, BigDecimal thirdPrice, BigDecimal totalAmoun, String vin) {
		super();
		this.orderThirdId = orderThirdId;
		this.cusId = cusId;
		this.thirdPrice = thirdPrice;
		this.totalAmoun = totalAmoun;
		this.vin = vin;
		this.gmtCreate = new Date();
	}

	public String getVin() {
		return vin;
	}

	public OrderThird setVin(String vin) {
		this.vin = vin;
		return this;
	}

	public OrderThird setOrderThirdId(String orderThirdId) {
		this.orderThirdId=orderThirdId;
		return this;
	}

	public String getOrderThirdId() {
		return this.orderThirdId;
	}

	public OrderThird setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public OrderThird setThirdPrice(BigDecimal thirdPrice) {
		this.thirdPrice=thirdPrice;
		return this;
	}

	public BigDecimal getThirdPrice() {
		return this.thirdPrice;
	}

	public OrderThird setTotalAmoun(BigDecimal totalAmoun) {
		this.totalAmoun=totalAmoun;
		return this;
	}

	public BigDecimal getTotalAmoun() {
		return this.totalAmoun;
	}
}