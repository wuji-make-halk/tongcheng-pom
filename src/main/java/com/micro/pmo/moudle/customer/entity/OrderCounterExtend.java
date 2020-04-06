package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;

/**
 * OrderExtend 
 * @author wenhaofan 
 * 
 */
public class OrderCounterExtend  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*订单id
	*/
	private String  orderExtendId;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*单价
	*/
	private BigDecimal  price;
	/**
	*购买的配置id
	*/
	private Integer  configId;
	/**
	*购买数量
	*/
	private Integer  buyNum;
	/**
	*总金额
	*/
	private BigDecimal  totalAmoun;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	/**
	*类型 查价：queryPrice，预定收车：collectCar,推广：promotion,刷新：flush
	*/
	private  CounterTypeEnum type;
 
	/**
	 * 车辆id
	 */
	private Integer carId;

	public OrderCounterExtend(String orderExtendId, Integer cusId, BigDecimal price, Integer configId, Integer buyNum,
			BigDecimal totalAmoun, Date gmtCreate, CounterTypeEnum type,Integer carId) {
		super();
		this.orderExtendId = orderExtendId;
		this.cusId = cusId;
		this.price = price;
		this.configId = configId;
		this.buyNum = buyNum;
		this.totalAmoun = totalAmoun;
		this.gmtCreate = gmtCreate;
		this.type = type;
		this.carId = carId;
	}

	
	
	public Integer getCarId() {
		return carId;
	}



	public void setCarId(Integer carId) {
		this.carId = carId;
	}



	public OrderCounterExtend() {
		super();
	}

	public OrderCounterExtend setOrderExtendId(String orderExtendId) {
		this.orderExtendId=orderExtendId;
		return this;
	}

	public String getOrderExtendId() {
		return this.orderExtendId;
	}

	public OrderCounterExtend setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public OrderCounterExtend setPrice(BigDecimal price) {
		this.price=price;
		return this;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public OrderCounterExtend setConfigId(Integer configId) {
		this.configId=configId;
		return this;
	}

	public Integer getConfigId() {
		return this.configId;
	}

	public OrderCounterExtend setBuyNum(Integer buyNum) {
		this.buyNum=buyNum;
		return this;
	}

	public Integer getBuyNum() {
		return this.buyNum;
	}

	public OrderCounterExtend setTotalAmoun(BigDecimal totalAmoun) {
		this.totalAmoun=totalAmoun;
		return this;
	}

	public BigDecimal getTotalAmoun() {
		return this.totalAmoun;
	}

	public OrderCounterExtend setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public CounterTypeEnum getType() {
		return type;
	}

	public void setType(CounterTypeEnum type) {
		this.type = type;
	}
	
	public void setType(String type) {
		setType(CounterTypeEnum.getEnumByValue(type));
	}
	
}