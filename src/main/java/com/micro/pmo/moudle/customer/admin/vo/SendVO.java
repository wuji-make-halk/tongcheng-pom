package com.micro.pmo.moudle.customer.admin.vo;

import javax.validation.constraints.NotNull;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月10日
 */
public class SendVO {

	/**
	 * @ 赠送对象id
	 */
	@NotNull(message = "赠送对象id不能为空")
	private Integer cusId;

	/**
	 * 
	 */
	@NotNull(message = "配置id不能为空")
	private Integer configId;

	/**
	 * 赠送次数
	 */
	@NotNull(message = "赠送次数不能为空")
	private Integer sendNum;
 
	
	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

}
