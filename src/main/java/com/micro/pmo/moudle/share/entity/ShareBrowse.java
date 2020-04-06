package com.micro.pmo.moudle.share.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import com.micro.pmo.commons.utils.DateUtil;

/**
 * 分享浏览Entity
 */
@Table(name = "share_browse")
public class ShareBrowse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 分享浏览主id
	 */
	private Integer shareBrowseId;
	
	/***
	 * 分享记录id
	 */
	private Integer shareId;
	
	
	/**
	 * 用户头像
	 */
	private String cusHead;
	
	/**
	 * 昵称
	 */
	private String cusNick;
	
	/***
	 * ip地址
	 */
	private String cusIp;
	
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public ShareBrowse(){
		super();
	}

	
	/**
	 * 获取分享浏览主id
	 * 
	 * @return Integer 分享浏览主id
	 */
	public Integer getShareBrowseId() {
		return shareBrowseId;
	}
	
	/**
	 * 设置分享浏览主id
	 * 
	 * @param shareBrowseId 分享浏览主id
	 */
	public void setShareBrowseId(Integer shareBrowseId) {
		this.shareBrowseId = shareBrowseId;
	}
		
	/**
	 * 获取用户头像
	 * 
	 * @return String 用户头像
	 */
	public String getCusHead() {
		return cusHead;
	}
	
	/**
	 * 设置用户头像
	 * 
	 * @param cusHead 用户头像
	 */
	public void setCusHead(String cusHead) {
		this.cusHead = cusHead;
	}
	
	/**
	 * 获取昵称
	 * 
	 * @return String 昵称
	 */
	public String getCusNick() {
		return cusNick;
	}
	
	/**
	 * 设置昵称
	 * 
	 * @param cusNick 昵称
	 */
	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
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

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public String getCusIp() {
		return cusIp;
	}

	public void setCusIp(String cusIp) {
		this.cusIp = cusIp;
	}
	//时间处理
	public String getVisitTime(){
		return DateUtil.dateToStr(this.createTime, "yyyy-MM-dd HH:mm");
	}


	public ShareBrowse(Integer shareId, String cusHead, String cusNick, String cusIp, Date createTime) {
		super();
		this.shareId = shareId;
		this.cusHead = cusHead;
		this.cusNick = cusNick;
		this.cusIp = cusIp;
		this.createTime = createTime;
	}
	
}