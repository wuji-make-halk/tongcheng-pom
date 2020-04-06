package com.micro.pmo.moudle.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.micro.pmo.moudle.account.accountenum.AccountLogEnum;
import com.micro.pmo.moudle.account.entity.CusAccountLog;
import com.micro.pmo.moudle.account.service.CusAccountLogService;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.impl.BaseOrderNotifyService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月25日 
*/
@Service
public class CusAccountOrderNotifyServcice extends BaseOrderNotifyService {
	
	@Autowired
	private CusAccountService cusAccountService;
	
	@Autowired
	private CusAccountLogService cusAccountLogService;

	public void setPayType() {
		this.payType = OrderPayTypeEnum.PAY_ACCOUNT;
	}
	
	@Override
	public void notifyPaySuccess(WxPayOrderNotifyResult notifyResult) {
		String orderId = notifyResult.getOutTradeNo();
		cusAccountService.paySuccess(orderId);
	}

	@Override
	public void notifyPayError(WxPayOrderNotifyResult notifyResult) {
		String orderId = notifyResult.getOutTradeNo();
		//更新记录类型为充值失败
		CusAccountLog log = new CusAccountLog(AccountLogEnum.DEPOSIT_ERRO.getFlowType(), orderId);
		cusAccountLogService.updateAccountByOrderId(log);
	}

	@Override
	public void notifyRefundSuccess(WxPayRefundNotifyResult notifyResult) {
		
	}

	@Override
	public void notifyRefundError(WxPayRefundNotifyResult notifyResult) {
		
	}

}
