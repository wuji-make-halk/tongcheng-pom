package com.micro.pmo.moudle.user.vo;
/***
 * 后台用户列表查询
 * @author 82423
 *
 */
public class UserQueryVo {

	private String loginName;
	
	private String userPhone;
	
	private String userName;
	
	private Integer userType;
	
	private Integer pageNumKey = 1;
	
	private Integer pageSizeKey = 20;
	
	private Integer userStatus = 0;
	
	private String province;
	
	private String city;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public UserQueryVo() {
		super();
	}

	public Integer getPageNumKey() {
		return pageNumKey;
	}

	public void setPageNumKey(Integer pageNumKey) {
		this.pageNumKey = pageNumKey;
	}

	public Integer getPageSizeKey() {
		return pageSizeKey;
	}

	public void setPageSizeKey(Integer pageSizeKey) {
		this.pageSizeKey = pageSizeKey;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
