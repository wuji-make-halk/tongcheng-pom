package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * app用户Entity
 */
@Table(name = "customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/**
	 * 用户手机号（登录账户）
	 */
	private String cusPhone;
	
	/**
	 * 用户昵称
	 */
	private String cusNick;
	
	/**
	 * 用户头像
	 */
	private String cusAvatar;
	
	/**
	 * 用户姓名
	 */
	private String cusName;
	
	/**
	 * 用户账户状态:0正常 1 限制登录
	 */
	@JsonIgnore
	private Integer cusStatus;
	
	/**
	 * 注册时间 	@DateTimeFormat(pattern="yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	 */
	private Date cusRegTime;
	
	
	/**
	 * 用户token
	 */
	private String cusToken;
	
	/***
	 * 省名称
	 */
	private String provinceName;
	
	/***
	 * 市名称
	 */
	private String cityName;
	
	/***
	 * 获取微店id
	 */
	private Integer storeId;
	
	/***
	 * 设备号
	 */
	private String deviceCode;
	/***
	 * 设备类型 1 IOS设备  2 Android设备
	 */
	private Integer deviceType;
	
	/***
	 * openId 
	 */
	private String openId;
	
	/***
	 * 微信唯一标识
	 */
	private String unionId;
	
	
	
	/***以下都是溶于字段**/
	
	/*** 
	 * 微店类型：0 店长，1店员用户 2为平台用户
	 */
	private Integer cusType;
	/***
	 * 是否是vip true false
	 */
	private Boolean isVip;
	
	/**
	 *  用户剩余金额
	 */
	private BigDecimal surplusMoney;
	
	public Customer(){
		super();
	}
	
	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getCusId() {
		return cusId;
	}
	
	/**
	 * 设置
	 * 
	 * @param cusId 
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获取用户手机号（登录账户）
	 * 
	 * @return String 用户手机号（登录账户）
	 */
	public String getCusPhone() {
		return cusPhone;
	}
	
	/**
	 * 设置用户手机号（登录账户）
	 * 
	 * @param cusPhone 用户手机号（登录账户）
	 */
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	/**
	 * 获取用户昵称
	 * 
	 * @return String 用户昵称
	 */
	public String getCusNick() {
		return cusNick;
	}
	
	/**
	 * 设置用户昵称
	 * 
	 * @param cusNick 用户昵称
	 */
	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}
	
	/**
	 * 获取用户头像
	 * 
	 * @return String 用户头像
	 */
	public String getCusAvatar() {
		return cusAvatar;
	}
	
	/**
	 * 设置用户头像
	 * 
	 * @param cusAvatar 用户头像
	 */
	public void setCusAvatar(String cusAvatar) {
		this.cusAvatar = cusAvatar;
	}
	
	/**
	 * 获取用户姓名
	 * 
	 * @return String 用户姓名
	 */
	public String getCusName() {
		return cusName;
	}
	
	/**
	 * 设置用户姓名
	 * 
	 * @param cusName 用户姓名
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	/**
	 * 获取用户账户状态:1正常 2 限制登录
	 * 
	 * @return Integer 用户账户状态:1正常 2 限制登录
	 */
	public Integer getCusStatus() {
		return cusStatus;
	}
	
	/**
	 * 设置用户账户状态:1正常 2 限制登录
	 * 
	 * @param cusStatus 用户账户状态:1正常 2 限制登录
	 */
	public void setCusStatus(Integer cusStatus) {
		this.cusStatus = cusStatus;
	}
	
	/**
	 * 获取注册时间
	 * 
	 * @return Date 注册时间
	 */
	public Date getCusRegTime() {
		return cusRegTime;
	}
	
	/**
	 * 设置注册时间
	 * 
	 * @param cusRegTime 注册时间
	 */
	public void setCusRegTime(Date cusRegTime) {
		this.cusRegTime = cusRegTime;
	}
		
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getCusToken() {
		return cusToken;
	}
	
	/**
	 * 设置
	 * 
	 * @param cusToken 
	 */
	public void setCusToken(String cusToken) {
		this.cusToken = cusToken;
	}

	public Customer(String cusPhone, String cusNick, String cusAvatar, Integer cusStatus, Date cusRegTime,
			String provinceName, String cityName) {
		super();
		this.cusPhone = cusPhone;
		this.cusNick = cusNick;
		this.cusAvatar = cusAvatar;
		this.cusStatus = cusStatus;
		this.cusRegTime = cusRegTime;
		this.provinceName = provinceName;
		this.cityName = cityName;
	}
	

	public Customer(Integer cusId, String cusPhone, String cusNick, String cusAvatar) {
		super();
		this.cusId = cusId;
		this.cusPhone = cusPhone;
		this.cusNick = cusNick;
		this.cusAvatar = cusAvatar;
	}




	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCusType() {
		return cusType;
	}

	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public void setIsVip(Boolean isVip) {
		this.isVip = isVip;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
}