package com.micro.pmo.moudle.extension.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月8日 
*/
public class InputExtensionBuyVO extends InputCusCounterBuyVO{

	@NotNull(message = "车辆id不能为空")
	private Integer carId;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	
	
}
