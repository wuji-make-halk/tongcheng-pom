package com.micro.pmo.moudle.car.vo;

import java.util.Date;

import com.micro.pmo.commons.vo.BaseQuery;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月8日
 */
public class FindCarQueryVO extends BaseQuery {

	/**
	 * 查询地区 省
	 */
	private String provinceLocation;

	/**
	 * 查询地区 市
	 */
	private String cityLocation;;

	/**
	 * 查询条件
	 */
	private String keyword;

	/**
	 * 品牌名称
	 */
	private String brandSeries;

	/**
	 * 排序 0位从低到高，1为从高到低
	 */
	private Integer sort;

	/**
	 * 排序类型 age price gmtCreate
	 */
	private String sortType;

	/**
	 * 查询开始年龄
	 */
	private Integer startAge;
	
	/***
	 * 车龄筛选开始时间
	 */
	private Date startAgeTime;
	/**
	 * 查询结束年龄
	 */
	private Integer endAge;
	
	/***
	 * 车龄筛选结束时间
	 */
	private Date endAgeTime;

	/**
	 * true 为1年以内 false 10年以上的
	 */
	private Boolean ageBelow;

	/**
	 * 是否查询startPrice以上车型
	 */
	private Boolean priceBelow;

	/**
	 * 开始年龄
	 */
	private Integer startPrice;
	/**
	 * 结束年龄
	 */
	private Integer endPrice;

	/**
	 * 是否在平台发布
	 */
	private Integer platform;

	/**
	 * 车辆状态
	 */
	private Integer carStatus;

	/**
	 * 是否删除
	 */
	private Integer delFlag;

 
	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

 
	public String getProvinceLocation() {
		return provinceLocation;
	}

	public void setProvinceLocation(String provinceLocation) {
		this.provinceLocation = provinceLocation;
	}

	public Boolean getAgeBelow() {
		return ageBelow;
	}

	public void setAgeBelow(Boolean ageBelow) {
		this.ageBelow = ageBelow;
	}

	public Boolean getPriceBelow() {
		return priceBelow;
	}

	public void setPriceBelow(Boolean priceBelow) {
		this.priceBelow = priceBelow;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		if ("age".equals(sortType)) {
			this.sortType = "car_age";
		} else if ("price".equals(sortType)) {
			this.sortType = "inter_price";
		}else{
			this.sortType = null;
		}
	}

	/**
	 * 解析车龄查询条件
	 * 
	 * @param ageStr
	 */
	public void setAge(String ageStr) {
		if (ageStr == null || ageStr.length() == 0) {
			return;
		}
		String[] arr = ageStr.split("-");

		if (arr.length < 2 || arr.length > 2) {
			return;
		}

		int startAge = Integer.parseInt(arr[0]);
		setStartAge(startAge);

		try {
			setEndAge(Integer.parseInt(arr[1]));
		} catch (Exception e) {
			setAgeBelow(arr[1].equals("below"));
		}

	}

	/**
	 * 解析价格查询条件
	 * 
	 * @param ageStr
	 */
	public void setPrice(String priceStr) {
		if (priceStr == null || priceStr.length() == 0) {
			return;
		}
		String[] arr = priceStr.split("-");

		if (arr.length < 2 || arr.length > 2) {
			return;
		}

		int startPrice = Integer.parseInt(arr[0]);
		setStartPrice(startPrice);

		try {
			setEndPrice(Integer.parseInt(arr[1]));
		} catch (Exception e) {
			setPriceBelow(arr[1].equals("below"));
		}

	}

	public void setPriceBelow(boolean priceBelow) {
		this.priceBelow = priceBelow;
	}

	public void setAgeBelow(boolean ageBelow) {
		this.ageBelow = ageBelow;
	}

	public Integer getStartAge() {
		return startAge;
	}

	public void setStartAge(Integer startAge) {
		this.startAge = startAge;
	}

	public Integer getEndAge() {
		return endAge;
	}

	public void setEndAge(Integer endAge) {
		this.endAge = endAge;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBrandSeries() {
		return brandSeries;
	}

	public void setBrandSeries(String brandSeries) {
		this.brandSeries = brandSeries;
	}

	public Integer getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Integer startPrice) {
		this.startPrice = startPrice;
	}

	public Integer getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Integer endPrice) {
		this.endPrice = endPrice;
	}

	public Date getStartAgeTime() {
		return startAgeTime;
	}

	public void setStartAgeTime(Date startAgeTime) {
		this.startAgeTime = startAgeTime;
	}

	public Date getEndAgeTime() {
		return endAgeTime;
	}

	public void setEndAgeTime(Date endAgeTime) {
		this.endAgeTime = endAgeTime;
	}

}
