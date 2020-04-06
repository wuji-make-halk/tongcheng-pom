package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * 出价Entity
 */
@Table(name = "car_bid")
public class CarBid implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 买车id
	 */
	private Integer bidId;
	
	/**
	 * 出价
	 */
	private BigDecimal binPrice;
	
	/**
	 * 车辆id
	 */
	private Integer carId;

	/**
	 * 创建者
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public CarBid(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getBidId() {
		return bidId;
	}
	
	/**
	 * 设置
	 * 
	 * @param bidId 
	 */
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	
	/**
	 * 获取出价
	 * 
	 * @return BigDecimal 出价
	 */
	public BigDecimal getBinPrice() {
		return binPrice;
	}
	
	/**
	 * 设置出价
	 * 
	 * @param binPrice 出价
	 */
	public void setBinPrice(BigDecimal binPrice) {
		this.binPrice = binPrice;
	}
	
	public Integer getCarId() {
		return carId;
	}


	public void setCarId(Integer carId) {
		this.carId = carId;
	}


	/**
	 * 获取创建者
	 * 
	 * @return Integer 创建者
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建者
	 * 
	 * @param creator 创建者
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
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
	
}