package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 用户微店表Entity
 */
@Table(name = "cus_store")
public class CusStore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 平台人员和微店关联主id
	 */
	private Integer cusStoreId;
	
	/**
	 * 人员id
	 */
	private Integer cusId;
	
	/**
	 * 创建时间
	 */
	private Date creatTime;
	
	/**
	 * 
	 */
	private Integer creator;
	
	/**
	 * 店铺员工父级id
	 */
	private Integer parentId;
	
	/**
	 * 用户类型：0 店长，1 店员用户
	 */
	private Integer cusType;
	
	/***
	 * 微店id
	 */
	private Integer storeId;
	
	/***
	 * 电话号码
	 */
	private String cusPhone;
	
	private String cusNick;
	
	private String cusAvatar;
	
	public CusStore(){
		super();
	}

	
	public CusStore(Integer cusId, Date creatTime, Integer creator, Integer parentId, Integer cusType,
			Integer storeId) {
		super();
		this.cusId = cusId;
		this.creatTime = creatTime;
		this.creator = creator;
		this.parentId = parentId;
		this.cusType = cusType;
		this.storeId = storeId;
	}




	/**
	 * 获取平台人员和微店关联主id
	 * 
	 * @return Integer 平台人员和微店关联主id
	 */
	public Integer getCusStoreId() {
		return cusStoreId;
	}
	
	/**
	 * 设置平台人员和微店关联主id
	 * 
	 * @param cusStoreId 平台人员和微店关联主id
	 */
	public void setCusStoreId(Integer cusStoreId) {
		this.cusStoreId = cusStoreId;
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
	 * 获取创建时间
	 * 
	 * @return Date 创建时间
	 */
	public Date getCreatTime() {
		return creatTime;
	}
	
	/**
	 * 设置创建时间
	 * 
	 * @param creatTime 创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置
	 * 
	 * @param creator 
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	/**
	 * 获取店铺员工父级id
	 * 
	 * @return Integer 店铺员工父级id
	 */
	public Integer getParentId() {
		return parentId;
	}
	
	/**
	 * 设置店铺员工父级id
	 * 
	 * @param parentId 店铺员工父级id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取用户类型：0店长，1 店员用户
	 * 
	 * @return Integer 用户类型：0 店长，1店员用户
	 */
	public Integer getCusType() {
		return cusType;
	}
	
	/**
	 * 设置用户类型：0店长，1店员用户
	 * 
	 * @param cusType 用户类型：0 店长，1店员用户
	 */
	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusAvatar() {
		return cusAvatar;
	}

	public void setCusAvatar(String cusAvatar) {
		this.cusAvatar = cusAvatar;
	}
	
}