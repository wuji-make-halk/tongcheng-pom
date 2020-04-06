package com.micro.pmo.moudle.third.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.impl.BaseOrderNotifyService;
import com.micro.pmo.moudle.third.service.ChaBoShiService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月14日 
*/
@Service
public class ChaBoshiNotifyService extends BaseOrderNotifyService {

	@Autowired
	private ChaBoShiService chaBoShiService;
	
	@Override
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult) {
		chaBoShiService.paySuccess(notifyResult.getOutTradeNo());
	}

	@Override
	public void notifyPayError(WxPayOrderNotifyResult notifyResult) {
	}

	@Override
	public void notifyRefundSuccess(WxPayRefundNotifyResult notifyResult) {
		 
	}

	@Override
	public void notifyRefundError(WxPayRefundNotifyResult notifyResult) {
	}

	@Override
	public void setPayType() {
		 this.payType = OrderPayTypeEnum.THIRD_REPAIRE;
	}

 
}
