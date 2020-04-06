package com.micro.pmo.moudle.customer.admin.vo;

import com.micro.pmo.commons.vo.BaseQuery;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
public class AdminCusQuery extends BaseQuery{

	/**
	 * 用户手机号
	 */
	private String cusPhone;
	
	/**
	 * 昵称
	 */
	private String cusNick;
	
	/***
	 * 市
	 */
	private String city;

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
}
