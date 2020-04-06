package com.micro.pmo.moudle.user.vo;

import javax.validation.constraints.NotNull;

public class LoginUser {

	@NotNull(message = "登录名不能为空")
	private String loginName;
	
	@NotNull(message = "密码不能为空")
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginUser() {
		super();
	}
	
	
}
