package com.micro.pmo.moudle.vip.vo;

import javax.validation.constraints.NotNull;

import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月29日 
*/
public class InputBuyVipVO extends BaseInputBuyVO{

	@NotNull(message =  "vip购买配置id不能为空")
	private Integer configId;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	
	
	
}
