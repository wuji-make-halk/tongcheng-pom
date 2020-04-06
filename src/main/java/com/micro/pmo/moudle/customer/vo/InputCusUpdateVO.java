package com.micro.pmo.moudle.customer.vo;

import javax.validation.constraints.NotNull;

/**
 * 用户信息修改入参
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月16日
 */
public class InputCusUpdateVO{


	@NotNull(message = "昵称不能为空")
	private String cusNick;
	
	@NotNull(message = "头像不能为空")
	private String cusAvatar;
	
	/***
	 * 地址
	 */
	private String cityName;

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusAvatar() {
		return cusAvatar;
	}

	public void setCusAvatar(String cusAvatar) {
		this.cusAvatar = cusAvatar;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	
}
