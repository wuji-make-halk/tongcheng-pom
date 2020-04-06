package com.micro.pmo.moudle.feedback.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 意见反馈Entity
 */
@Table(name = "feedback")
public class Feedback implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 反馈id
	 */
	private Integer feedbackId;
	
	/**
	 * 人员id
	 */
	private Integer cusId;
	
	/**
	 * 反馈内容
	 */
	private String feedbackContent;
	
	/**
	 * 申请时间
	 */
	private Date createTime;
	
	/**
	 * 反馈状态：0 待处理 1 已处理
	 */
	private Integer feedbackStatus;
	
	/**
	 * 处理时间
	 */
	private Date handleTime;
	
	/*****用户信息******/
	/***
	 * 用户昵称
	 */
	private String cusNick;
	
	/***
	 * 用户电话
	 */
	private String cusPhone;
	
	public Feedback(){
		super();
	}

	
	
	
	/**
	 * 获取反馈id
	 * 
	 * @return Integer 反馈id
	 */
	public Integer getFeedbackId() {
		return feedbackId;
	}
	
	/**
	 * 设置反馈id
	 * 
	 * @param feedbackId 反馈id
	 */
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	/**
	 * 获取人员id
	 * 
	 * @return Integer 人员id
	 */
	public Integer getCusId() {
		return cusId;
	}
	
	/**
	 * 设置人员id
	 * 
	 * @param cusId 人员id
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	
	/**
	 * 获取反馈内容
	 * 
	 * @return String 反馈内容
	 */
	public String getFeedbackContent() {
		return feedbackContent;
	}
	
	/**
	 * 设置反馈内容
	 * 
	 * @param feedbackContent 反馈内容
	 */
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	
	/**
	 * 获取申请时间
	 * 
	 * @return Date 申请时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置申请时间
	 * 
	 * @param createTime 申请时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取反馈状态：0 待处理 1 已处理
	 * 
	 * @return Integer 反馈状态：0 待处理 1 已处理
	 */
	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}
	
	/**
	 * 设置反馈状态：0 待处理 1 已处理
	 * 
	 * @param feedbackStatus 反馈状态：0 待处理 1 已处理
	 */
	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}
	
	/**
	 * 获取处理时间
	 * 
	 * @return Date 处理时间
	 */
	public Date getHandleTime() {
		return handleTime;
	}
	
	/**
	 * 设置处理时间
	 * 
	 * @param handleTime 处理时间
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getCusNick() {
		return cusNick;
	}


	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}





	public Feedback(Integer cusId, String feedbackContent, Date createTime, Integer feedbackStatus) {
		super();
		this.cusId = cusId;
		this.feedbackContent = feedbackContent;
		this.createTime = createTime;
		this.feedbackStatus = feedbackStatus;
	}
	
}