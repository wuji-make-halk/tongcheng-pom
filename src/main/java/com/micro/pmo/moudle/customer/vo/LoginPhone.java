package com.micro.pmo.moudle.customer.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LoginPhone {
	
	@NotNull(message = "电话号码不能为空")
	@Length(min = 11,message = "电话号码位数错误")
    private String phone;

	@NotNull(message = "验证码不能为空")
	@Length(min = 6,message = "验证码位数错误")
    private String code;
	
	/***
	 * 设备码
	 */
	private String deviceCode;
	
	/***
	 * 设备类型 1 IOS设备  2 Android设备
	 */
	private Integer deviceType;

	/***
	 * 位置
	 */
	private String position;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LoginPhone() {
		super();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

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

	public LoginPhone(String phone, String code) {
		super();
		this.phone = phone;
		this.code = code;
	}

    
}
