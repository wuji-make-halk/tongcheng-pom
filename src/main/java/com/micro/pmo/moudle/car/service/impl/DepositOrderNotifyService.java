package com.micro.pmo.moudle.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.car.service.DepositService;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.impl.BaseOrderNotifyService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月25日 
*/
@Service
public class DepositOrderNotifyService extends BaseOrderNotifyService{

	@Autowired
	private DepositService depositService;
	
	@Override
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult) {
		String orderId = notifyResult.getOutTradeNo();
		depositService.depostPaySuccess(orderId);
	}

	@Override
	public void notifyPayError(WxPayOrderNotifyResult notifyResult) {
		
	}

	@Override
	public void notifyRefundSuccess(WxPayRefundNotifyResult notifyResult) {
		String orderId = notifyResult.getReqInfo().getOutTradeNo();
		
		depositService.depositRefundSuccess(orderId);
	}

	@Override
	public void notifyRefundError(WxPayRefundNotifyResult notifyResult) {
		
		
	}

	@Override
	public void setPayType() {
		this.payType = OrderPayTypeEnum.DEPOSIT;
	}

}
