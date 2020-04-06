package com.micro.pmo.moudle.order.vo;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月5日 
*/

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;

public class PayInfoVO {

	/**
	 * 支付的三方接口信息
	 */
	private Object info;
	
	/**
	 * 支付方式
	 */
	@JSONField(serialize = false)
	private OrderPayModeEnum mode;
	
	/**
	 * 购买类型 
	 */
	@JSONField(serialize = false)
	private OrderPayTypeEnum type;
	
	private String orderId;

	public PayInfoVO(OrderPayModeEnum mode, OrderPayTypeEnum type) {
		super();
		this.mode = mode;
		this.type = type;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayModeTitle() {
		return mode == null ? null : mode.getRemark();
	}
	
	public Integer getPayMode() {
		return mode == null ? null : mode.getMode();
	}
	
	public String getPayTypeTitle() {
		return type == null ? null : type.getTitle();
	}
	
	public Integer getPayType() {
		return type == null ? null : type.getType();
	}
	
	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public OrderPayModeEnum getMode() {
		return mode;
	}

	public void setMode(OrderPayModeEnum mode) {
		this.mode = mode;
	}

	public OrderPayTypeEnum getType() {
		return type;
	}

	public void setType(OrderPayTypeEnum type) {
		this.type = type;
	}
	
	
}
