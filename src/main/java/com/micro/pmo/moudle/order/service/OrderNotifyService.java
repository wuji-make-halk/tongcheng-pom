package com.micro.pmo.moudle.order.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月25日 
*/
public interface OrderNotifyService {
	
	/**
	 * 处理支付回调的业务操作
	 * @param notifyResult
	 */
	public void notifyPay(WxPayOrderNotifyResult notifyResult);
	/**
	 * 支付回调成功的业务操作
	 * @param notifyResult
	 */
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult);
	/**
	 * 支付回调失败的业务操作
	 * @param notifyResult
	 */
	public void notifyPayError(WxPayOrderNotifyResult notifyResult);
	
	/**
	 * 处理退款回调的业务操作
	 * @param notifyResult
	 */
	public void notifyRefund(WxPayRefundNotifyResult notifyResult);
	/**
	 * 退款回调成功的业务操作
	 * @param notifyResult
	 */
	public void notifyRefundSuccess(WxPayRefundNotifyResult notifyResult);
	/**
	 * 退款失败的业务操作
	 * @param notifyResult
	 */
	public void notifyRefundError(WxPayRefundNotifyResult notifyResult);
	/**
	 * 注册方法
	 */
	void register();
}
