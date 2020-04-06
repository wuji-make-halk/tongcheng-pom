package com.micro.pmo.moudle.car.vo;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月24日 
*/
public class CarCountByStatusVO {

	/**
	 * 未上架
	 */
	private Integer notOn;
	/**
	 * 已售
	 */
	private Integer sold;
	/**
	 * 在售
	 */
	private Integer onSale;

	public Integer getNotOn() {
		return notOn;
	}

	public void setNotOn(Integer notOn) {
		this.notOn = notOn;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Integer getOnSale() {
		return onSale;
	}

	public void setOnSale(Integer onSale) {
		this.onSale = onSale;
	}
	
	
	
}
