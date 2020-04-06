package com.micro.pmo.moudle.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.micro.pmo.moudle.order.service.OrderService;

/**
 * @author Binary Wang
 */
@RestController
@RequestMapping("api-pc/wx/pay/notify")
public class WxPayController {

	private static final Logger log = LoggerFactory.getLogger(WxPayController.class);

	@Autowired
	private WxPayService wxService;

	@Autowired
	private OrderService orderService;

	/***
	 * 订单支付回调
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("order")
	public String parseOrderNotifyResult(@RequestBody String xmlData) {
		try {
			log.info("微信支付回调进入");
			final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
			log.info("微信支付回调，订单号：{},返回状态:{}", notifyResult.getOutTradeNo(), notifyResult.getResultCode());
			// 处理返回状态
			orderService.wxPayNotify(notifyResult);
		} catch (WxPayException e) {
			log.error("获取微信支付回调参数异常,异常原因{}", e.getMessage());
			e.printStackTrace();
			return WxPayNotifyResponse.fail("微信支付解析参数失败");
		}
		return WxPayNotifyResponse.success("支付回调成功");

	}

	/**
	 * 退款回调
	 * 
	 * @param xmlData
	 * @return
	 * @throws WxPayException
	 */
	@RequestMapping("refund")
	public String parseRefundNotifyResult(@RequestBody String xmlData) {

		try {
			log.info("微信退款回调进入");
			final WxPayRefundNotifyResult notifyResult = this.wxService.parseRefundNotifyResult(xmlData);
			log.info("微信退款回调返回状态：" + notifyResult.getResultCode());
			orderService.wxRefundNotify(notifyResult);
		} catch (WxPayException e) {
			log.error("获取微信退款回调参数异常,异常原因{}", e.getMessage());
			e.printStackTrace();
			return WxPayNotifyResponse.fail("微信退款回调解析参数失败");
		}
		return WxPayNotifyResponse.success("退款回调成功");
	}

}
