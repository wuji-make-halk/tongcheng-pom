package com.micro.pmo.moudle.car.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.micro.pmo.moudle.car.enu.OrderDepositStatusEnum;

/**
 * 订单定金附属表 
 * @author wenhaofan 
 * 
 */
public class OrderDeposit  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*订单id
	*/
	private String  orderDepositId;
	/**
	* 支付价格
	*/
	private BigDecimal  money;
	/**
	*用户id
	*/
	private Integer  cusId;
	/**
	*车辆id
	*/
	private Integer  carId;
	/**
	*创建时间
	*/
	private Date  gmtCreate;
	
	 /**
	 * 确认状态  0为正在交易， 1 确认交易 ，2为交易异常 ,3为未支付 ， 4为等待卖家支付，5 交易失败已退款 ，6 交易完成，7 申请退款成功，8取消交易
	 */
	private Integer status;
	
	/**
	 *  0为买家记录，1为卖家记录
	 */
	private Integer type;
	
	/**
	 * 买家订单id
	 */
	private String buyerOrderId;
	
	/**
	 *  共有订单ID,用于处理关联关系
	 */
	private String orderId;
	
	public OrderDeposit() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderDeposit(Integer carId, Integer type) {
		super();
		this.carId = carId;
		this.type = type;
	}

	public String getBuyerOrderId() {
		return buyerOrderId;
	}

	public void setBuyerOrderId(String buyerOrderId) {
		this.buyerOrderId = buyerOrderId;
	}

	public OrderDeposit(Integer carId, Integer status, Integer type) {
		super();
		this.carId = carId;
		this.status = status;
		this.type = type;
	}



	public OrderDeposit(BigDecimal money, Integer cusId, Integer carId, Date gmtCreate,Integer status,Integer type ,String buyerOrderId,String orderId) {
		super();
		this.money = money;
		this.cusId = cusId;
		this.carId = carId;
		this.gmtCreate = gmtCreate;
		this.status = status;
		this.type = type;
		this.orderId = orderId;
		this.buyerOrderId = buyerOrderId;
	}
	
	public OrderDeposit(BigDecimal money, Integer cusId, Integer carId, Date gmtCreate,Integer status,Integer type,String buyerOrderId) {
		super();
		this.money = money;
		this.cusId = cusId;
		this.carId = carId;
		this.gmtCreate = gmtCreate;
		this.status = status;
		this.type = type;
		this.buyerOrderId = buyerOrderId;
	}

	/**
	 *确认状态 0为正在交易，1 确认交易 2交易异常 3为未支付 ， 4为等待卖家支付，5 已退款 ，6 交易完成，7 交易关闭
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 状态标识
	 * @return
	 */
	public String getStatusStr() {
		OrderDepositStatusEnum statusEnum = OrderDepositStatusEnum.getByMode(getStatus());
		return statusEnum == null ? null : statusEnum.getRemark();
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public OrderDeposit setOrderDepositId(String orderDepositId) {
		this.orderDepositId=orderDepositId;
		return this;
	}

	public String getOrderDepositId() {
		return this.orderDepositId;
	}

	public OrderDeposit setMoney(BigDecimal money) {
		this.money=money;
		return this;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public OrderDeposit setCusId(Integer cusId) {
		this.cusId=cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public OrderDeposit setCarId(Integer carId) {
		this.carId=carId;
		return this;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public OrderDeposit setGmtCreate(Date gmtCreate) {
		this.gmtCreate=gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}
}