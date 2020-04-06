package com.micro.pmo.moudle.weixin.vo;

import java.io.Serializable;

/***
 * 最终获取微信授权最终获取
 * @author raoBo
 *
 */
public class WexinVo implements Serializable {

	private static final long serialVersionUID = 1L;
    /*openId*/
    private String openId;

    /*微信用户名*/
    private String nickName;

    /*性别*/
    private String gender;

    /*语言*/
    private String language;

    /*城市*/
    private String city;

    /*省份*/
    private String province;

    /*国家*/
    private String country;

    /*头像地址*/
    private String avatarUrl;

    /*用户唯一id*/
    private String unionId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}


}
