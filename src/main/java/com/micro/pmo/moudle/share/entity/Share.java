package com.micro.pmo.moudle.share.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 分享Entity
 */
@Table(name = "share")
public class Share implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 分享id
	 */
	private Integer shareId;
	
	/**
	 * 分享类型：1 微店 2 微店车辆 3平台车辆
	 */
	private Integer shareType;
	
	/**
	 * 车辆id
	 */
	private Integer carId;
	
	/***
	 * 微店id
	 */
	private Integer storeId;
	
	/**
	 * 分享昵称
	 */
	private String shareName;
	
	/**
	 * 分享图片地址
	 */
	private String sharePhoto;
	
	/**
	 * 分享时间
	 */
	private Date shareTime;
	
	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/***以下是展示字段处理***/
	/***
	 * 浏览人数
	 */
	private Integer browserCount;
	
	/***
	 * 浏览人最新头像
	 */
	private String browserUrl;
	
	/***
	 * 时间
	 */
	private String createTime;
	
	public Share(){
		super();
	}

	
	
	
	public Integer getBrowserCount() {
		return browserCount;
	}




	public void setBrowserCount(Integer browserCount) {
		this.browserCount = browserCount;
	}




	public String getBrowserUrl() {
		return browserUrl;
	}




	public void setBrowserUrl(String browserUrl) {
		this.browserUrl = browserUrl;
	}




	public String getCreateTime() {
		return createTime;
	}




	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}




	/**
	 * 获取分享id
	 * 
	 * @return Integer 分享id
	 */
	public Integer getShareId() {
		return shareId;
	}
	
	/**
	 * 设置分享id
	 * 
	 * @param shareId 分享id
	 */
	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	
	/**
	 * 获取分享类型：1 微店 2 车辆
	 * 
	 * @return Integer 分享类型：1 微店 2 车辆
	 */
	public Integer getShareType() {
		return shareType;
	}
	
	/**
	 * 设置分享类型：1 微店 2 车辆
	 * 
	 * @param shareType 分享类型：1 微店 2 车辆
	 */
	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}
	
	/**
	 * 获取分享昵称
	 * 
	 * @return String 分享昵称
	 */
	public String getShareName() {
		return shareName;
	}
	
	/**
	 * 设置分享昵称
	 * 
	 * @param shareName 分享昵称
	 */
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	
	/**
	 * 获取分享图片地址
	 * 
	 * @return String 分享图片地址
	 */
	public String getSharePhoto() {
		return sharePhoto;
	}
	
	/**
	 * 设置分享图片地址
	 * 
	 * @param sharePhoto 分享图片地址
	 */
	public void setSharePhoto(String sharePhoto) {
		this.sharePhoto = sharePhoto;
	}
	
	/**
	 * 获取分享时间
	 * 
	 * @return Date 分享时间
	 */
	public Date getShareTime() {
		return shareTime;
	}
	
	/**
	 * 设置分享时间
	 * 
	 * @param shareTime 分享时间
	 */
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
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




	public Integer getCarId() {
		return carId;
	}




	public void setCarId(Integer carId) {
		this.carId = carId;
	}




	public Integer getStoreId() {
		return storeId;
	}




	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}




	public Share(Integer shareType, Date shareTime, Integer cusId) {
		super();
		this.shareType = shareType;
		this.shareTime = shareTime;
		this.cusId = cusId;
	}
	
}