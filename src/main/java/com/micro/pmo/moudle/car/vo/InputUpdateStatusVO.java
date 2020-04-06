package com.micro.pmo.moudle.car.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class InputUpdateStatusVO {

	@NotNull(message = "收车id不能为空")
	private Integer buyCarId;

	@NotNull(message = "状态不能为空")
	private Integer buyStatus;

	private BigDecimal clinchPrice;

	public Integer getBuyCarId() {
		return buyCarId;
	}

	public void setBuyCarId(Integer buyCarId) {
		this.buyCarId = buyCarId;
	}

	public Integer getBuyStatus() {
		return buyStatus;
	}

	public void setBuyStatus(Integer buyStatus) {
		this.buyStatus = buyStatus;
	}

	public BigDecimal getClinchPrice() {
		return clinchPrice;
	}

	public void setClinchPrice(BigDecimal clinchPrice) {
		this.clinchPrice = clinchPrice;
	}
	
	
}
