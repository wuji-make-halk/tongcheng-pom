package com.micro.pmo.moudle.order.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.car.entity.Refund;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

public interface OrderService {
	
	/**
	 * 根据订单ID查询订单
	 * @author dsn
	 * @param order
	 * @return
	 */
	public Order getOrderByOrderId(String orderId) ;
 
	/**
	 * 创建订单并执行支付
	 * @author dsn
	 * @param order
	 * @return
	 */
	public PayInfoVO createOrder(Order order);

	/**
	 * 保存订单信息 不进行支付
	 * @param order
	 */
	void saveOrderNotPay(Order order);
	
	/**
	 * 对订单进行支付
	 * @param order
	 * @return
	 */
	PayInfoVO payOrder(Order order);
	/**
	 * 处理微信支付回调
	 * @param notify
	 */
	public void wxPayNotify(WxPayOrderNotifyResult notifyResult);
	
	/**
	 * 处理微信退款
	 * @param notifyResult
	 */
	public void wxRefundNotify(WxPayRefundNotifyResult notifyResult);
 
	
	/**
	 * 退款
	 * @param order
	 */
	public void refund(Order order ,Refund refund);
 
	/**
	 * 退款
	 * @param order
	 */
	public void refund(Order order);
}
