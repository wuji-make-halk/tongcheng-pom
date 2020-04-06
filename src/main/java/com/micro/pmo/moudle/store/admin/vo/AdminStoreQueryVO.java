package com.micro.pmo.moudle.store.admin.vo;

import com.micro.pmo.commons.vo.BaseQuery;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月15日 
*/
public class AdminStoreQueryVO extends BaseQuery {

	/**
	 *  微店创建者所在省
	 */
	private String provinceName;
	
	/**
	 * 微店创建者所在市
	 */
	private String cityName;
	
	/**
	 * 搜索词
	 */
	private String keyword;
	
	/***
	 * 城市
	 */
	private String cityLocation;
 
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

 
	
}
