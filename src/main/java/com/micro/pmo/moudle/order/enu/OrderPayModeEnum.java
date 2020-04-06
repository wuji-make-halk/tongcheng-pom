package com.micro.pmo.moudle.order.enu;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月25日
 */
public enum OrderPayModeEnum {

	/**
	 * 账号支付
	 */
	ACCOUNT_PAY(0,"账号支付"),
	/**
	 * 微信小程序/H5支付
	 */
	WX_PAY_JSAPI(1,"微信支付"),
	/**
	 * 微信APP支付
	 */
	WX_PAY_APP(2,"微信支付"),
	/**
	 * 支付宝手机网站支付
	 */
	ALIPAY_WAP(3,"支付宝支付"),
	/**
	 * 支付宝APP支付
	 */
	ALIPAY_APP(4,"支付宝支付");

	private Integer mode;
	
	private String remark;
	
	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public static OrderPayModeEnum getByMode(Integer mode){
		for (OrderPayModeEnum item : OrderPayModeEnum.values()) {
			if(item.getMode().equals(mode)) {
				return item;
			}
		}
		return null;
	}
	
	private OrderPayModeEnum(Integer mode,String remark) {
		this.mode = mode;
		this.remark = remark;
	}

	public Integer getMode() {
		return mode;
	}

}
