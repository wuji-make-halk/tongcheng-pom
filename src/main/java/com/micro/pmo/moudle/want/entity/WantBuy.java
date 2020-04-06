package com.micro.pmo.moudle.want.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * 求购Entity
 */
@Table(name = "want_buy")
public class WantBuy implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 求购
	 */
	private Integer wantBuyId;
	
	/**
	 * 求购车型
	 */
	@NotNull(message = "车型不能为空")
	private String carType;
	
	/***
	 * 姓名
	 */
	@Length(max=10,message="请填入10位的姓名")
	private String cusNick;
	/**
	 * 联系电话
	 */
	@Length(max=11,message="请填入11位的电话号码")
	private String phone;
	
	/**
	 * 描述
	 */
	@Length(max=500,message="请填入500内的描述")
	private String description;
	
	/**
	 * 创建者id
	 */
	private Integer creator;
	
	/***
	 * 微店id
	 */
	@NotNull(message = "微店id不能为空")
	private Integer storeId;
	
	/***
	 * 推送人id
	 */
	@NotNull(message = "推送人id不能为空")
	private Integer pushCusId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
		
	public WantBuy(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getWantBuyId() {
		return wantBuyId;
	}
	
	/**
	 * 设置
	 * 
	 * @param wantBuyId 
	 */
	public void setWantBuyId(Integer wantBuyId) {
		this.wantBuyId = wantBuyId;
	}
	
	/**
	 * 获取求购车型
	 * 
	 * @return String 求购车型
	 */
	public String getCarType() {
		return carType;
	}
	
	/**
	 * 设置求购车型
	 * 
	 * @param carType 求购车型
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	/**
	 * 获取联系电话
	 * 
	 * @return String 联系电话
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置联系电话
	 * 
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取描述
	 * 
	 * @return String 描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置描述
	 * 
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取创建者id
	 * 
	 * @return Integer 创建者id
	 */
	public Integer getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建者id
	 * 
	 * @param creator 创建者id
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPushCusId() {
		return pushCusId;
	}

	public void setPushCusId(Integer pushCusId) {
		this.pushCusId = pushCusId;
	}

	public String getCusNick() {
		return cusNick;
	}


	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	
}