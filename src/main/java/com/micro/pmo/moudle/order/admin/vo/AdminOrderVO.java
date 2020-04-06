package com.micro.pmo.moudle.order.admin.vo;

import java.math.BigDecimal;

import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/
public class AdminOrderVO extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String cusNick;
	
	private String cusPhone;

	/**
	 * 购买单价
	 */
	private BigDecimal unitPrice;

	/**
	 * 购买数量
	 */
	private Integer buyNum;
	
	/**
	 * 设置vip记录表中的单价和数量
	 * @param vipPrice
	 */
	public void setVipPrice(BigDecimal vipPrice) {
		this.unitPrice = vipPrice;
	}
	
	public void setVipNum(Integer num) {
		this.buyNum = num;
	}
	
	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * 获取订单状态标题
	 * @return
	 */
	public String getOrderStatusTitle() {
		OrderStatusEnum statusEnum = OrderStatusEnum.getByStatus(this.getOrderStatus());
		return statusEnum == null?null:statusEnum.getTitle();
	}
	
	/**
	 * 获取支付类型标题
	 * @return
	 */
	public String getOrderPayModeTitle() {
		OrderPayModeEnum  mode = OrderPayModeEnum.getByMode(this.getPaymentMode());
		return mode!=null?mode.getRemark():null;
	}
	
	/**
	 * 获取购买类型标题
	 * @return
	 */
	public String getOrderPayTypeTitle() {
		OrderPayTypeEnum orderPayTypeEnum = OrderPayTypeEnum.getByType(getPaymentType());
		return orderPayTypeEnum !=null?orderPayTypeEnum.getTitle():null;
	}
	
	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	

}
