package com.micro.pmo.moudle.banner.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 轮播图Entity
 */
@Table(name = "banner")
public class Banner implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	private Integer bannerId;
	
	/**
	 * 轮播图地址
	 */
	@NotNull(message = "banner图片不能为空")
	private String imgUrl;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 图片排序
	 */
	private Integer imgSort;
	
	
	public Banner(){
		super();
	}

	
	
	
	/**
	 * 获取
	 * 
	 * @return Integer 
	 */
	public Integer getBannerId() {
		return bannerId;
	}
	
	/**
	 * 设置
	 * 
	 * @param bannerId 
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	
	/**
	 * 获取轮播图地址
	 * 
	 * @return String 轮播图地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * 设置轮播图地址
	 * 
	 * @param imgUrl 轮播图地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/**
	 * 获取创建人
	 * 
	 * @return String 创建人
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
	
	/**
	 * 获取图片排序
	 * 
	 * @return Integer 图片排序
	 */
	public Integer getImgSort() {
		return imgSort;
	}
	
	/**
	 * 设置图片排序
	 * 
	 * @param imgSort 图片排序
	 */
	public void setImgSort(Integer imgSort) {
		this.imgSort = imgSort;
	}




	public Banner(String imgUrl, Integer creator, Date createTime, Integer imgSort) {
		super();
		this.imgUrl = imgUrl;
		this.creator = creator;
		this.createTime = createTime;
		this.imgSort = imgSort;
	}
	
}