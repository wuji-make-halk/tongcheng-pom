package com.micro.pmo.moudle.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.impl.BaseOrderNotifyService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月26日 
*/
@Service
public class CounterPayNotifyServiceImpl extends BaseOrderNotifyService{

	
	@Autowired
	private CusCounterService cusCounterService;
 
	@Override
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult) {
		String orderId = notifyResult.getOutTradeNo();

		cusCounterService.paySuccess(orderId);
		
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
		this.payType = OrderPayTypeEnum.FLUSH;
	}

	@Override
	public void register() {
		//推广
		factory.register(OrderPayTypeEnum.EXTENSION, this);
		//刷新
		factory.register(OrderPayTypeEnum.FLUSH, this);
		//收车
		factory.register(OrderPayTypeEnum.RESERVE, this);
		//第三方保险
		factory.register(OrderPayTypeEnum.THIRD_INSURE, this);
		//第三方维修记录
		factory.register(OrderPayTypeEnum.THIRD_REPAIRE, this);
		//查询成交价
		factory.register(OrderPayTypeEnum.QUERY_DEAL, this);
	}
}
