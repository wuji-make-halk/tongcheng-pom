package com.micro.pmo.moudle.car.admin.vo;
import com.micro.pmo.moudle.car.enu.OrderDepositStatusEnum;
import com.micro.pmo.moudle.order.entity.Order;
/**
 * 
 * 买家和卖家的定金信息
 * 
 * @author 作者:fanwenhao
 *
 */
public class AdminDepositOrderVO extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 0为买家 1为卖家
	 */
	private Integer type;

	 /**
	 * 确认状态 0为正在交易， 1 确认交易 ，2为交易异常 ,3为未支付 ， 4为等待卖家支付，5 交易失败已退款 ，6 交易完成，7 申请退款成功，8取消交易
	 */
	private Integer dealStatus;

	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*车辆id
	*/
	private Integer  carId;
 
	/**
	 * 用户昵称
	 */
	private String cusNick;
	
	/**
	 * 用户手机号
	 */
	private String cusPhone;
	
	/***
	 * 公用id
	 */
	private String comOrderId;
	
	/***
	 * 买家或买家订单id
	 */
	private String buyerOrderId;
	
	
 
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

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

 
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 *  0为正在交易，1 确认交易 2交易异常 3为未支付 ， 4为等待卖家支付，5 已退款 ，6 交易完成，7 交易关闭
	 * @return
	 */
	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	
	public String getDealStatusStr() {
		OrderDepositStatusEnum statusEnum = OrderDepositStatusEnum.getByMode(getDealStatus());
		return statusEnum == null ? null : statusEnum.getRemark();
	}

	public String getComOrderId() {
		return comOrderId;
	}

	public void setComOrderId(String comOrderId) {
		this.comOrderId = comOrderId;
	}

	public String getBuyerOrderId() {
		return buyerOrderId;
	}

	public void setBuyerOrderId(String buyerOrderId) {
		this.buyerOrderId = buyerOrderId;
	}
	

}