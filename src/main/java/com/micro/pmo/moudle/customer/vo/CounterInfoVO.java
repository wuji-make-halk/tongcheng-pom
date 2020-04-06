package com.micro.pmo.moudle.customer.vo;

import java.math.BigDecimal;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月10日
 */
public class CounterInfoVO {

	/**
	 * 剩余赠送总数
	 */
	private Integer surplusSize = 0;
 

	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 0为没有赠送次数，1为有赠送次数
	 */
	private Integer state = 0;
 
	/**
	 * 配置id
	 */
	private Integer configId;
	
	/**
	 * 价格
	 */
	private BigDecimal configConfigPrice;
	
	/***
	 * 成交价
	 */
	private Integer dealSize;
 
	public CounterInfoVO() {
		super();
	}

	public CounterInfoVO(Integer surplusSize, String unit,Integer configId, BigDecimal configConfigPrice) {
		super();
		this.surplusSize = surplusSize;
		this.unit = unit;
		this.state = surplusSize > 0 ? 1:0;
		this.configId = configId;
		this.configConfigPrice = configConfigPrice;
	}

	public Integer getSurplusSize() {
		return surplusSize;
	}

	public void setSurplusSize(Integer surplusSize) {
		this.surplusSize = surplusSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getConfigPrice() {
		if(configConfigPrice == null) {
			return "0";
		}
		return configConfigPrice.toPlainString();
	}

	public void setConfigPrice(BigDecimal configConfigPrice) {
		this.configConfigPrice = configConfigPrice;
	}


	public Integer getDealSize() {
		return dealSize;
	}

	public void setDealSize(Integer dealSize) {
		this.dealSize = dealSize;
	}
	
	
}
