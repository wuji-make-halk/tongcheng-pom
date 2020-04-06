package com.micro.pmo.moudle.weixin.vo;

import java.io.Serializable;

import com.micro.pmo.moudle.customer.entity.Customer;

/***
 * 微信模板
 * @author 82423
 *
 */
public class ResultModel implements Serializable {

	private static final long serialVersionUID = 1L;


	/***
	 * 是否有电话号码
	 */
    private Boolean isHavePhoneNumber;

    /***
     * cusToken
     */
    private String cusToken;

    /***
     * cus 用户信息
     */
    private Customer customer;
    
    /***
     * 微信openId
     */
    private String openId;

	public Boolean getIsHavePhoneNumber() {
		return isHavePhoneNumber;
	}

	public void setIsHavePhoneNumber(Boolean isHavePhoneNumber) {
		this.isHavePhoneNumber = isHavePhoneNumber;
	}

	public String getCusToken() {
		return cusToken;
	}

	public void setCusToken(String cusToken) {
		this.cusToken = cusToken;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ResultModel(Boolean isHavePhoneNumber, String cusToken, Customer customer, String openId) {
		super();
		this.isHavePhoneNumber = isHavePhoneNumber;
		this.cusToken = cusToken;
		this.customer = customer;
		this.openId = openId;
	}

	public ResultModel(Boolean isHavePhoneNumber, String openId) {
		super();
		this.isHavePhoneNumber = isHavePhoneNumber;
		this.openId = openId;
	}

	public ResultModel() {
		super();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
