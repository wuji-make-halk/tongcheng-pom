package com.micro.pmo.moudle.weixin.vo;

import javax.validation.constraints.NotNull;

/***
 * 微信授权需要参数
 * @author raoBo
 *
 */
public class WeixinAuthorization {

	 /***
	  * 失效时间
	  */
   @NotNull(message = "用户加密信息不能为空")
   private String encryptedData;
	/***
	 * 位移数据
	 */
   @NotNull(message = "位移不能为空")
   private String iv;

   /***
    * code
    */
   @NotNull(message = "微信code不能为空")
   private String code;
   
	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}


	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public WeixinAuthorization() {
		super();
	}
	
}
