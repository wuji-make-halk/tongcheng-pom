package com.micro.pmo.moudle.car.admin.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class AdminDepositRefundVO {

	@NotNull(message = "买家退款金额不能为空")
	private BigDecimal buyerPrice;
	
	@NotNull(message = "买家退款金额不能为空")
	private BigDecimal sellerPrice;
	
	@NotNull(message = "买家订单号不能为空")
	private String buyerOrderId;

	public BigDecimal getBuyerPrice() {
		return buyerPrice;
	}

	public void setBuyerPrice(BigDecimal buyerPrice) {
		this.buyerPrice = buyerPrice;
	}

	public BigDecimal getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(BigDecimal sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public String getBuyerOrderId() {
		return buyerOrderId;
	}

	public void setBuyerOrderId(String buyerOrderId) {
		this.buyerOrderId = buyerOrderId;
	}
	
	
	
}
