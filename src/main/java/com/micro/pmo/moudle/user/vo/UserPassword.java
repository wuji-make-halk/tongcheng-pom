package com.micro.pmo.moudle.user.vo;

import javax.validation.constraints.NotNull;

/***
 * 密码
 * @author 82423
 *
 */
public class UserPassword {

	@NotNull(message="原密码不能为空")
	private String oldPassword;
	
	@NotNull(message="新密码不能为空")
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
}
