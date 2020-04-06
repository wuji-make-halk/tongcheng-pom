package com.micro.pmo.moudle.store.vo;
/***
 * 详情时候使用
 * @author 82423
 *
 */
public class StoreCarInfoVo {

	/**
	 * 微店id
	 */
	private Integer storeId;
	
	/***
	 * 微店名称
	 */
	private String storeName;
	
	/***
	 * 微店头像
	 */
	private String storePhoto;
	
	/***
	 * 车辆总数
	 */
	private Integer carSize;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStorePhoto() {
		return storePhoto;
	}

	public void setStorePhoto(String storePhoto) {
		this.storePhoto = storePhoto;
	}

	public Integer getCarSize() {
		return carSize;
	}

	public void setCarSize(Integer carSize) {
		this.carSize = carSize;
	}

	public StoreCarInfoVo() {
		super();
	}
	
	
}
