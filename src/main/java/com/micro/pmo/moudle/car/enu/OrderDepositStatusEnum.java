package com.micro.pmo.moudle.car.enu;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月7日 
*/
public enum OrderDepositStatusEnum {

	// 0为正在交易，1 确认交易 2交易异常 3为未支付 ， 4为等待卖家支付，5 已退款 ，6 交易完成，7 交易关闭
	
	/**
	 * 双方都已付款时的初始状态
	 */
	TRADING(0,"正在交易"),
	/**
	 * 付款后确认交易
	 */
	AFFIRM(1,"交易成功"),
	/**
	 * 付款后不确认交易
	 */
	ERROR(2,"交易失败"),
	
	NOPAY(3,"未支付"),
	/**
	 * 买方支付完成后的状态
	 */
	WAIT(4,"等待卖家支付定金"),
	/**
	 * 后台退款
	 */
	REFUND(5,"已退款"),
	/**
	 * 双方交易确认状态统一 
	 */
	SUCCESS(6,"交易完成"),
	/**
	 * 未支付和取消订单
	 */
	CANCEL_TRADE(7,"交易关闭");
	
	private Integer value;
	
	private String remark;

	public static OrderDepositStatusEnum getByMode(Integer mode){
		for (OrderDepositStatusEnum item : OrderDepositStatusEnum.values()) {
			if(item.getValue().equals(mode)) {
				return item;
			}
		}
		return null;
	}
	
	OrderDepositStatusEnum(Integer value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public Integer getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}
	
	
	
}
