package com.micro.pmo.moudle.car.admin.vo;

import com.micro.pmo.commons.vo.BaseQuery;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月12日 
*/
public class AdminCarQueryVO  extends BaseQuery{

	/**
	 *  车辆创建者所在省
	 */
	private String provinceName;
	
	/**
	 * 创建者所在市
	 */
	private String cityName;
	
	/***
	 * 车辆所在地
	 */
	private String cityLocation;
	
	/**
	 * 搜索词
	 */
	private String keyword;
	/**
	 * 车辆状态0 未上架 1在售 2已售 3其他
	 */
	private Integer carStatus;
	
	/**
	 * 微店id
	 */
	private Integer storeId;
 
	/**
	 * 删除标识 0未删除 1已删除
	 */
	private Integer delFlag;

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

}
