package com.micro.pmo.moudle.store.vo;

import javax.validation.constraints.NotNull;

public class StaffVo {

	@NotNull(message = "电话号码不能为空")
	private String phone;
	
	@NotNull(message = "姓名不能不能为空")
	private String name;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StaffVo() {
		super();
	}
	
	
}
