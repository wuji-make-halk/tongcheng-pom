package com.micro.pmo.moudle.account.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月26日 
*/
public class InputAccountTopupVO extends BaseInputBuyVO{

	@NotNull(message = "充值金额")
	private BigDecimal money;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
}
