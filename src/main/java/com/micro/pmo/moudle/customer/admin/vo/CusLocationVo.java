package com.micro.pmo.moudle.customer.admin.vo;

import javax.validation.constraints.NotNull;

/***
 * 修改用户地址
 * @author 82423
 *
 */
public class CusLocationVo {

	@NotNull(message = "用户id不能为空")
	private Integer cusId;
	
	@NotNull(message = "用户地址不能为空")
	private String cityName;

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CusLocationVo() {
		super();
	}
	
	
}
