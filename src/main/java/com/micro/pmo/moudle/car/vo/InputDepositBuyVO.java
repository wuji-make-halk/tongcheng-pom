package com.micro.pmo.moudle.car.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月26日 
*/
public class InputDepositBuyVO extends BaseInputBuyVO{

	@NotNull(message = "车辆id不能为空")
	private Integer carId;


	
	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
}
