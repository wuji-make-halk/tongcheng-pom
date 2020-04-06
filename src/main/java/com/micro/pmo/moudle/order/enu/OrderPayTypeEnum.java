
package com.micro.pmo.moudle.order.enu;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月23日 
*/
public enum OrderPayTypeEnum {
	/**
	 * VIP
	 */
	VIP(0,"购买VIP"),
	/**
	 * 推广
	 */
	EXTENSION(1,"购买推广"),
	/**
	 * 刷新
	 */
	FLUSH(2,"购买刷新"),
	/**
	 * 账号充值
	 */
	PAY_ACCOUNT(3,"账号充值"),
	/**
	 * 预定收车
	 */
	RESERVE(4,"购买预定收车"),
	/**
	 * 第三方保险查询
	 */
	THIRD_INSURE(5,"第三方保险"),
	/**
	 * 第三方维修记录
	 */
	THIRD_REPAIRE(6,"第三方维修"),
	/**
	 * 定金
	 */
	DEPOSIT(7,"支付定金"),
	/**
	 * 查询成交价
	 */
	QUERY_DEAL(8,"查询成交价");
 
	public static OrderPayTypeEnum getByType(Integer type) {
		if (type == null) {
			return null;
		}
		for (OrderPayTypeEnum item : OrderPayTypeEnum.values()) {
			if (item.getType().equals(type)) {
				return item;
			}
		}
		return null;
	}
	
	private Integer type;

	private String title;
	
	public Integer getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}
	
	private OrderPayTypeEnum(Integer type , String title) {
		this.type = type;
		this.title = title;
	}
 
	
//	支付类型：vip购买：0，推广购买：1，刷新购买：2，账号充值：3，预定收车：4，第三方保险查询：5，第三方维修记录：6
	
}
