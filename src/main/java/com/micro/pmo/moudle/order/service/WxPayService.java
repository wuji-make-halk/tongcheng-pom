package com.micro.pmo.moudle.order.service;

import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.micro.pmo.moudle.order.entity.Order;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月25日 
*/
public interface WxPayService {
	
	/**
	 * 创建微信订单
	 * @param order
	 * @return
	 */
	public <T>T createOrder(Order order) ;
	
	/**
	 *  订单退款
	 * @param order 订单信息
	 * @return
	 */
	public WxPayRefundResult refund(Order order);
}
