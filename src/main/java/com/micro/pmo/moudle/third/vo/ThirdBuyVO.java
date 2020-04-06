package com.micro.pmo.moudle.third.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月14日 
*/
public class ThirdBuyVO extends InputCusCounterBuyVO{

	/**
	 * 车架号
	 */
	@NotNull(message = "车架号不能为空")
	private String vin;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
}
