package com.micro.pmo.moudle.order.enu;

public enum OrderStatusEnum {

	/**
	 * 待支付
	 */
	UNPAID(0,"待支付"),
	/**
	 * 支付成功
	 */
	SUCCESS(1,"支付成功"),
	/**
	 * 支付失败
	 */
	ERROR(2,"支付失败"),
	/**
	 * 已退款
	 */
	REFUNDED(3,"已退款"),
	/**
	 * 退款失败
	 */
	REFUNDED_ERROR(4,"退款失败"),
	/**
	 * 取消交易
	 */
	CANCLE(5,"取消交易");
	
	String title;
	
	Integer status;

	public Integer getStatus() {
		return status;
	}
	
	public String getTitle() {
		return title;
	}

	private OrderStatusEnum(Integer status , String title) {
		this.status = status;
		this.title = title;
	}

	public static OrderStatusEnum getByStatus(Integer value) {
		if (value == null) {
			return null;
		}
		for (OrderStatusEnum item : OrderStatusEnum.values()) {
			if (item.getStatus().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
