package com.micro.pmo.moudle.car.admin.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.micro.pmo.commons.vo.BaseQuery;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月2日 
*/
public class AdminDepositOrderQuery extends BaseQuery {

	/**
	 * 用户手机号
	 */
	private String cusPhone;
	
	/**
	 * 用户姓名
	 */
	private String cusName;
	
	/**
	 * 订单创建查询时间 起始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date queryStart;
	
	/**
	 * 订单创建查询时间 结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date queryEnd;
	
	/***
	 * 城市
	 */
	private String cityName;
	
	/**
	 * 订单支付方式
	 */
	private Integer orderPayMode;
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/***
	 * 订单id
	 */
	private String orderId;

	/**
	 * 订单id
	 */
	private List<String> orderIds;

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public Date getQueryStart() {
		return queryStart;
	}

	public void setQueryStart(Date queryStart) {
		this.queryStart = queryStart;
	}

	public Date getQueryEnd() {
		return queryEnd;
	}

	public void setQueryEnd(Date queryEnd) {
		this.queryEnd = queryEnd;
	}

	public Integer getOrderPayMode() {
		return orderPayMode;
	}

	public void setOrderPayMode(Integer orderPayMode) {
		this.orderPayMode = orderPayMode;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
