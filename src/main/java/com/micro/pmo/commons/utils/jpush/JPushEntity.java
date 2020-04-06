package com.micro.pmo.commons.utils.jpush;

public class JPushEntity {

	/***
	 * 设备号
	 */
	private String deviceCode;
	/***
	 * 设备类型 1 IOS设备  2 Android设备
	 */
	private Integer deviceType;
	

	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public JPushEntity(String deviceCode, Integer deviceType) {
		super();
		this.deviceCode = deviceCode;
		this.deviceType = deviceType;
	}
	public JPushEntity() {
		super();
	}
	
	
	
}
