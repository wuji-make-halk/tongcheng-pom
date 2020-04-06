package com.micro.pmo.commons.utils;

/***
 * 系统定义的状态码
 *
 */
public enum ResultState {

	/* 成功状态 */
	SUCCEED(200, "成功"),
	
	HTTP_RESP_FAILED(102,"连接超时"),
	
	SYS_ERROR(500,"系统繁忙,请尽快联系管理员"),
	
	HTTP_EXCEPTION(101,"http连接错误"),
	
	TOKEN_ERROR(401,"用户未登录"),
	
	LOGIN_ERROR(402,"用户名或密码错误！"),
	
	LOGIN_INVALID(407,"登录用户已失效"),
	
	LOGIN_COD_INVALID(408,"用户登录失败：验证码错误"),
	
	WEIXIN_AUTHORIZE_ERROR(409,"微信授权失败"),
	
	WEIXIN_AUTHORIZE_NO(410,"微信未授权"),
	
	CACHE_ERROR(505,"缓存存入失败"),
	
	WX_PAY_ORDER_CREATE_ERROR(506,"订单创建失败"),
	WX_PAY_ORDER_REFUND_ERROR(507,"订单退款失败"),
	WX_PAY_ACCOUNT_SHORT_OF_FUND(508,"账号余额不足"),
	
	DEPOSIT_PAY_REPEAT(509,"该车辆定金已支付"),
	
	DEPOSIT_PAY_NOT_PAY(510,"该车辆买家未支付定金"),
	
	DEPOSIT_ERROR_PAY(510,"不能购买自己发布的车辆"),
	
	DEPOSIT_SELLER_NOT_PAY(511,"订单状态错误"),
	
	DEPOSIT_REFUND_MONEY_OVERFLOW(511,"订单退款金额大于支付金额"),
	
	DEPOSIT_STICK_NOT_SELF(512,"当前登录用户不是订单的创建者"),
	
	DEPOSIT_BUYER_PAID(513,"卖家已支付不能退款"),
	
	DEPOSIT_SELLER_PAID(514,"卖家已支付，不能取消订单"),
	
	ORDER_CANCEL_ERROR(515,"该订单不支持取消"),
	
	SEND_MSG_ERROR(601,"发送短信失败"),
	
	PARAM_ERROR(602,"参数错误"),
	
	METHOD_ERROR(603,"请求方式错误"),
	
	PHONE_ERROR(604,"电话号码格式错误"),
	
	CUS_FROZEN(605,"电话号码已在平台冻结，请联系客服"),
	
	STORE_ERROR(606,"你没有微店的使用功能"),
	
	STORE_FAIL(607,"开通微店失败：你已有微店或是微店员工！"),
	
	STORE_STAFF(608,"你不是店长不能添加员工"),
	
	STORE_ADD_STAFF(609,"该员工已被其它店添加！"),
	
	CAR_STATUS_ERROR(610,"请先下架车辆再做修改"),

	RESERVE_STATUS_NUMBER(611,"用户只允许12条心愿数量！"),

	RESERVE_UPDATE_STATUS(612,"更新状态不是0，！不能更新!"),
	
	RESERVE_USE_TIME(616,"请先购买预订收车时间"),
	
	CAR_INFO_ERROR(613,"车辆已下架或已售"),

	CAR_BID_PRICE(613,"出价失败"),
	
	OPERATION_ERROR(614,"请选择平台或微店"),
	
	CUS_DEPOSIT_ERROR(615,"不能签署自己发布的车辆的电子合同"),
	
	/*****后台用户状态码*****/
	
	NOT_FIND_USER(670,"未找到用户"),
	
	USER_FREEZING(671,"账号冻结"),
	
	USER_LOGIN_FAIL(672,"登录名或密码错误"),
	
	USER_DEL_FAIL(673,"不能注销自己账号或没有权限"),
	
	USER_PASSWORD_FAIL(674,"原密码错误"),
	
	USER_LOGIN_NAME_ERROR(680,"登录名重复"),
	
	BANER_DEL_FAIL(690,"banner至少要保留一张"),
	
	FILE_ERROR(701,"上传文件失败"),
	
	SEND_USE_UP(702,"赠送次数已用完"),
	
	CAR_FLUSH_ERROR(801,"车辆刷新已到期，请先购买"),
	
	FREQUENCY_ERROR(802,"次数已用完，请先购买");

	private long code; // 状态码
	private String desc; // 说明

	private ResultState(long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
