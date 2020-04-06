package com.micro.pmo.moudle.order.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.OrderNotifyService;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月26日
 */
public abstract class BaseOrderNotifyService implements OrderNotifyService {

	@Autowired
	protected OrderNotifyServiceFactory factory;

	protected OrderPayTypeEnum payType;

	/**
	 * 设置付款类型
	 */
	public abstract void setPayType();

	@Override
	public void notifyPay(WxPayOrderNotifyResult notifyResult) {
		if (isSuccess(notifyResult.getReturnCode())) {
			notifyPaySuccess(notifyResult);
		} else {
			notifyPayError(notifyResult);
		}
	}

	@Override
	public void notifyRefund(WxPayRefundNotifyResult notifyResult) {
		if (isSuccess(notifyResult.getReturnCode())) {
			notifyRefundSuccess(notifyResult);
		} else {
			notifyRefundError(notifyResult);
		}
	}

	public boolean isSuccess(String returnCode) {
		return returnCode == null ? false : returnCode.equals("SUCCESS");
	}

	@Override
	@PostConstruct
	public void register() {

		setPayType();

		if (this.payType == null) {
			throw new Error("请初始化支付类型---" + getClass().getName());
		}

		factory.register(payType, this);
	}

}
