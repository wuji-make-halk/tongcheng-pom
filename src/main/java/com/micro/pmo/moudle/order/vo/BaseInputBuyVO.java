package com.micro.pmo.moudle.order.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月26日 
*/
public class BaseInputBuyVO {

	/**
	  * 0 账号
	  * 1 微信H5/小程序
	  * 2 微信APP
	 */
	@NotNull(message = "请选择支付方式")
	private Integer type ;
	
	/**
	  * 购买数量
	 */
	private Integer num = 1;
	
	/**
	 * 支付类型
	 */
	private OrderPayModeEnum payMode;

	/**
	 * 发起请求的ip
	 */
	private String ip;
	
	/**
	 * 配置id
	 */
	private Integer configId;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public OrderPayModeEnum getPayMode() {
		return payMode;
	}

	public void setPayMode(OrderPayModeEnum payMode) {
		this.payMode = payMode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
		OrderPayModeEnum payMode = OrderPayModeEnum.getByMode(type);
		if(payMode == null) {
			throw new PmoException(ResultState.PARAM_ERROR,"支付类型错误");
		}
		this.setPayMode(payMode);
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public boolean isAccountPay() {
		return type == null?false:type.equals(OrderPayModeEnum.ACCOUNT_PAY.getMode());
	}
}
