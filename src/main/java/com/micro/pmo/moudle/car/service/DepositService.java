package com.micro.pmo.moudle.car.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.car.entity.OrderDeposit;
import com.micro.pmo.moudle.car.vo.InputDepositBuyVO;
import com.micro.pmo.moudle.car.vo.InputDepositPayVO;
import com.micro.pmo.moudle.car.vo.UserOrderDepositVO;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月24日
 */
public interface DepositService {

	/**
	 * 定金支付成功后的操作
	 * 
	 * @param orderId
	 */
	public void depostPaySuccess(String orderId);

	/**
	 * 定金退款成功后的操作
	 * 
	 * @param orderId
	 */
	public void depositRefundSuccess(String orderId);

	/**
	 * 确认定金订单
	 * 
	 * @param order
	 */
	public void confirmDepositOrder(String orderId, Integer status);

	/**
	 * 买家 支付定金
	 * 
	 * @param carId
	 */
	public PayInfoVO payDeposit(InputDepositBuyVO input);

	/**
	 * 通过订单ID 继续支付
	 * 
	 * @param orderId
	 * @param payMode
	 * @return
	 */
	PayInfoVO stickPayDepositOrder(InputDepositPayVO input);

	/**
	 * 发起退款 主要后台操作退款时使用
	 * 
	 * @param order
	 */
	void refundDeposit(Order order,OrderDeposit orderDeposit);

	/**
	 * 根据类型获取当前用户的买家和卖家订金列表
	 * 
	 * @param type
	 * @return
	 */
	PageInfo<UserOrderDepositVO> listUserOrderDeposit(BaseQuery baseQuery,Integer type);

	/**
	 * 申请退款
	 * 
	 * @param type 0 买家取消订单  1 卖家取消订单 
	 * @param orderId
	 */
	void applyRefund(String orderId,Integer type);
	
	/**
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 */
	Kv<String,Object> depositInfo(String orderId , Integer type);
} 
