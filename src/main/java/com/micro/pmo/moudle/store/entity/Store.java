
package com.micro.pmo.moudle.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.customer.entity.Customer;

/**
 * 微店Entity
 */
@Table(name = "store")
public class Store implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 微店id
	 */
	private Integer storeId;
	/***
	 * 微店名称
	 */
	@NotNull(message = "微店名不能为空")
	private String storeName;
	
	/**
	 * 省
	 */
	private String provinceLocation;
	/**
	 * 市
	 */
	@NotNull(message = "市不能为空")
	private String cityLocation;
	/**
	 * 县/区
	 */
	private String countyLocation;
	/**
	 * 门牌号
	 */
	private String doorNumber;
	
	/***
	 * 公告
	 */
	private String storeNotice;
	/***
	 * 创建人
	 */
	private Integer creator;
	
	/***
	 * 创建时间
	 */
	private Date createTime;
	
	/***
	 * 微店头像
	 */
	@NotNull(message = "头像不能为空")
	private String storePhoto;
	
	/***
	 * 联系电话
	 */
	@NotNull(message = "联系电话不能为空")
	private String storePhone;
	
	/***
	 * 轮播图地址
	 */
	@NotNull(message = "轮播图不能为空")
	private String storeImg1;
	
	private String storeImg2;
	
	private String storeImg3;
	
	/**发布微店车辆总条数**/
	private Integer carMun;
	
	/**
	 * 当前用户是否是该微店员工
	 */
	private boolean isStoreCus;
	
	/**
	 * 微店下的员工
	 */
	private List<Customer> cusList;
	
	/**
	 * 微店下的车辆
	 */
	private List<Car> carList;
	
	public Store(){
		super();
	}
	
	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}



	/**
	 * 获取轮播图列表
	 * @return
	 */
	public List<String> getStoreImgList(){
		List<String> storeList = new ArrayList<String>();
		storeList.add(storeImg1);
		storeList.add(storeImg2);
		storeList.add(storeImg3);
		return storeList;
	}
	
	public List<Customer> getCusList() {
		return cusList;
	}

	public void setCusList(List<Customer> cusList) {
		this.cusList = cusList;
	}

	public boolean getIsStoreCus() {
		return isStoreCus;
	}

	public void setIsStoreCus(boolean isStoreCus) {
		this.isStoreCus = isStoreCus;
	}

	/**
	 * 获取
	 * 
	 * @return String 
	 */
	public Integer getStoreId() {
		return storeId;
	}
	
	/**
	 * 设置
	 * 
	 * @param storeId 
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getProvinceLocation() {
		return provinceLocation;
	}

	public void setProvinceLocation(String provinceLocation) {
		this.provinceLocation = provinceLocation;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

	public String getCountyLocation() {
		return countyLocation;
	}

	public void setCountyLocation(String countyLocation) {
		this.countyLocation = countyLocation;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getStoreNotice() {
		return storeNotice;
	}

	public void setStoreNotice(String storeNotice) {
		this.storeNotice = storeNotice;
	}

	public String getStorePhoto() {
		return storePhoto;
	}


	public void setStorePhoto(String storePhoto) {
		this.storePhoto = storePhoto;
	}

	public String getStorePhone() {
		return storePhone;
	}


	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreImg1() {
		return storeImg1;
	}


	public void setStoreImg1(String storeImg1) {
		this.storeImg1 = storeImg1;
	}

	public String getStoreImg2() {
		return storeImg2;
	}

	public void setStoreImg2(String storeImg2) {
		this.storeImg2 = storeImg2;
	}

	public String getStoreImg3() {
		return storeImg3;
	}


	public void setStoreImg3(String storeImg3) {
		this.storeImg3 = storeImg3;
	}

	public Integer getCarMun() {
		return carMun;
	}

	public void setCarMun(Integer carMun) {
		this.carMun = carMun;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}