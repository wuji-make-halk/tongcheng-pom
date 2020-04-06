package com.micro.pmo.moudle.brand.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 品牌系列Entity
 */
@Table(name = "sys_brand_series")
public class BrandSeries implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 系列id
	 */
	private String brandSeriesId;
	
	/**
	 * 车型名称
	 */
	private String brandName;
	
	/**
	 * 品牌id
	 */
	private Integer brandId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public BrandSeries(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public String getBrandSeriesId() {
		return brandSeriesId;
	}
	
	/**
	 * 设置
	 * 
	 * @param brandSeriesId 
	 */
	public void setBrandSeriesId(String brandSeriesId) {
		this.brandSeriesId = brandSeriesId;
	}
	
	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	/**
	 * 获取品牌id
	 * 
	 * @return Integer 品牌id
	 */
	public Integer getBrandId() {
		return brandId;
	}
	
	/**
	 * 设置品牌id
	 * 
	 * @param brandId 品牌id
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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
	
}