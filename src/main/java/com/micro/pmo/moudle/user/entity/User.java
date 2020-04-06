package com.micro.pmo.moudle.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 后台用户Entity
 */
@Table(name = "sys_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String defPassword = "000000";
	
	/**
	 * 用户id
	 */
	private Integer userId;
	
	/***
	 * 用户头像
	 */
	private String userHeadImg;
	
	/**
	 * 用户电话号码
	 */
	@Length(max=11,message="请输入11位的电话号码")
	@NotNull(message ="电话号码不能为空")
	private String userPhone;
	
	/**
	 * 登录名
	 */
	@NotNull(message ="登录名不能为空")
	@Length(max=10,message="请输入10位以内的登录名")
	private String loginName;
	
	/**
	 * 用户真实姓名
	 */
	@NotNull(message ="用户真实姓名不能为空")
	@Length(max=10,message="请输入10位以内的真实姓名")
	private String userName;
	
	/**
	 * 地区code
	 */
	private String addressCode;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 用户类型：0 超级管理员 1 区域经理
	 */
	private Integer userType;
	
	/**
	 * 省：all 超级管理员  其它省为区域管理员
	 */
	private String province;
	
	/**
	 * 市：all 最高管理员 其它值为区域
	 */
	@NotNull(message ="市不能为空")
	private String city;
	
	/**
	 * 用户状态：0 正常 1 注销
	 */
	private Integer userStatus;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/***
	 * 后台用户token
	 */
	private String userToken;
	
	public User(){
		super();
	}

	
	
	
	/**
	 * 获取用户id
	 * 
	 * @return Integer 用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 设置用户id
	 * 
	 * @param userId 用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取用户电话号码
	 * 
	 * @return String 用户电话号码
	 */
	public String getUserPhone() {
		return userPhone;
	}
	
	/**
	 * 设置用户电话号码
	 * 
	 * @param userPhone 用户电话号码
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	/**
	 * 获取登录名
	 * 
	 * @return String 登录名
	 */
	public String getLoginName() {
		return loginName;
	}
	
	/**
	 * 设置登录名
	 * 
	 * @param loginName 登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置
	 * 
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置
	 * 
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获取用户类型：0 超级管理员 1 区域经理
	 * 
	 * @return Integer 用户类型：0 超级管理员 1 区域经理
	 */
	public Integer getUserType() {
		return userType;
	}
	
	/**
	 * 设置用户类型：0 超级管理员 1 区域经理
	 * 
	 * @param userType 用户类型：0 超级管理员 1 区域经理
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	/**
	 * 获取省：all 超级管理员  其它省为区域管理员
	 * 
	 * @return String 省：all 超级管理员  其它省为区域管理员
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * 设置省：all 超级管理员  其它省为区域管理员
	 * 
	 * @param province 省：all 超级管理员  其它省为区域管理员
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 获取市：all 最高管理员 其它值为区域
	 * 
	 * @return String 市：all 最高管理员 其它值为区域
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * 设置市：all 最高管理员 其它值为区域
	 * 
	 * @param city 市：all 最高管理员 其它值为区域
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 获取用户状态：0 正常 1 注销
	 * 
	 * @return Integer 用户状态：0 正常 1 注销
	 */
	public Integer getUserStatus() {
		return userStatus;
	}
	
	/**
	 * 设置用户状态：0 正常 1 注销
	 * 
	 * @param userStatus 用户状态：0 正常 1 注销
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	/**
	 * 获取创建人
	 * 
	 * @return Integer 创建人
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建人
	 * 
	 * @param creator 创建人
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


	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	
	
}