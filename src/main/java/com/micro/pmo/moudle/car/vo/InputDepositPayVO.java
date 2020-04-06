package com.micro.pmo.moudle.car.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;


/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月7日 
*/
public class InputDepositPayVO extends BaseInputBuyVO {

	/**
	 * 订单id
	 */
	@NotNull(message = "卖家订单ID不能为空")
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	
}
