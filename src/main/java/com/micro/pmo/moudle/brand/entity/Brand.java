package com.micro.pmo.moudle.brand.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Table;

/**
 * 品牌Entity
 */
@Table(name = "sys_brand")
public class Brand implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 品牌id
	 */
	private String brandId;
	
	/**
	 * 品牌名称
	 */
	private String brandName;
	
	/**
	 * 品牌小图
	 */
	private String brandImg;
	
	/**
	 * 品牌首字母
	 */
	private String brandAcronym;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 层级
	 */
	private Integer level;
	
	/***
	 * 装入品牌车型
	 */
	private List<Brand> children;
	
	public Brand(){
		super();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 获取品牌id
	 * 
	 * @return String 品牌id
	 */
	public String getBrandId() {
		return brandId;
	}
	
	/**
	 * 设置品牌id
	 * 
	 * @param brandId 品牌id
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	/**
	 * 获取品牌名称
	 * 
	 * @return String 品牌名称
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * 设置品牌名称
	 * 
	 * @param brandName 品牌名称
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	/**
	 * 获取品牌
	 * 
	 * @return String 品牌
	 */
	public String getBrandImg() {
		return brandImg;
	}
	
	/**
	 * 设置品牌
	 * 
	 * @param brandImg 品牌
	 */
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}
	
	/**
	 * 获取品牌
	 * 
	 * @return String 品牌
	 */
	public String getBrandAcronym() {
		return brandAcronym;
	}
	
	/**
	 * 设置品牌
	 * 
	 * @param brandAcronym 品牌
	 */
	public void setBrandAcronym(String brandAcronym) {
		this.brandAcronym = brandAcronym;
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

	public List<Brand> getChildren() {
		return children;
	}

	public void setChildren(List<Brand> children) {
		this.children = children;
	}
	
}