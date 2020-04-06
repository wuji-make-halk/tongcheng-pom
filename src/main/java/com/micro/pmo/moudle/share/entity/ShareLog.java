package com.micro.pmo.moudle.share.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 分享记录Entity
 */
@Table(name = "share_log")
public class ShareLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 分享id
	 */
	private Integer shareLogId;
	
	/**
	 * 用户id
	 */
	private Integer cusId;
	
	/**
	 * 分享链接
	 */
	private String shareUrl;
	
	/**
	 * 分享类型：1 微店 2 微店车辆 3平台车辆
	 */
	@NotNull(message="分享类型不能为空")
	private Integer shareType;
	
	
	
	/**
	 * 分享时间
	 */
	private Date shareTime;
	
	/***
	 * 分享id的集合
	 */
	private String  shareIds;
	
	/***
	 * 微店id
	 */
	private Integer storeId;
	
	public ShareLog(){
		super();
	}

	public Integer getShareLogId() {
		return shareLogId;
	}


	public void setShareLogId(Integer shareLogId) {
		this.shareLogId = shareLogId;
	}

	

	public String getShareIds() {
		return shareIds;
	}

	public void setShareIds(String shareIds) {
		this.shareIds = shareIds;
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
	
	/**
	 * 获取分享链接
	 * 
	 * @return String 分享链接
	 */
	public String getShareUrl() {
		return shareUrl;
	}
	
	/**
	 * 设置分享链接
	 * 
	 * @param shareUrl 分享链接
	 */
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
}