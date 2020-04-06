package com.micro.pmo.moudle.weixin.vo;

import javax.validation.constraints.NotNull;

/***
 * 微信绑定
 * @author raoBo
 *
 */
public class ParameterModel extends WeixinAuthorization{


    
    /*****以下请求微信第一次获取得到的数据****/
    
    /**登录绑定用户时请求参数***/
	/***
	 * cusPhone 电话号码
	 */
    @NotNull(message = "电话号码不能为空")
    private String cusPhone;
    
    /***
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String phoneCode;
    

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}


    
}
