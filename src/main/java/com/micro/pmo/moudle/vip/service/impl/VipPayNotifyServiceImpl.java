package com.micro.pmo.moudle.vip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.mapper.OrderVipMapper;
import com.micro.pmo.moudle.order.entity.OrderVip;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.impl.BaseOrderNotifyService;
import com.micro.pmo.moudle.vip.entity.Vip;
import com.micro.pmo.moudle.vip.service.CusVipService;
import com.micro.pmo.moudle.vip.service.VipService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月26日 
*/
@Service
public class VipPayNotifyServiceImpl extends BaseOrderNotifyService {

	@Autowired
	private CusVipService cusVipService;
	
	@Autowired
	private OrderVipMapper orderVipMapper;
	
	@Autowired
	private VipService vipService;
	
	@Override
	public void setPayType() {
		this.payType = OrderPayTypeEnum.VIP;
	}
	
	@Override
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult) {
		String orderId = notifyResult.getOutTradeNo();
		OrderVip orderVip = orderVipMapper.getOrderVipById(orderId);
		Vip vip = vipService.getVipById(orderVip.getVipConfigId());
		//给账号开通vip
		cusVipService.buyVip(orderVip.getOrderVipId(), vip , orderVip.getCusId());
	}

	@Override
	public void notifyPayError(WxPayOrderNotifyResult notifyResult) {
		//支付失败
	}

	@Override
	public void notifyRefundSuccess(WxPayRefundNotifyResult notifyResult) {
		
	}

	@Override
	public void notifyRefundError(WxPayRefundNotifyResult notifyResult) {
		
	}
}
