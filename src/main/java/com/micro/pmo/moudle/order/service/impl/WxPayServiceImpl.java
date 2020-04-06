package com.micro.pmo.moudle.order.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.WxPayServiceApacheHttpImpl;
import com.github.pagehelper.util.StringUtil;
import com.micro.pmo.commons.config.WxPayProperties;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.service.WxPayService;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月25日
 */
@Service
public class WxPayServiceImpl extends WxPayServiceApacheHttpImpl implements WxPayService {

	/**
	 * The Config.
	 */
	@Autowired
	protected WxPayConfig config;

	@Autowired
	private WxPayProperties properties;
	
	@Value("${wx.pay.mchId}")
	private String mchId;
	
	@Value("${wx.refund.notifyUrl}")
	private String refundNotifyUrl;


	@Override
	public <T> T createOrder(Order order) {
		WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();

		String body = StringUtil.isEmpty(order.getProductName())?"商品购买":order.getProductName();
		String nonceStr = UUIDUtils.getUUID();
		String outTradeNo = order.getOrderId();
		// 获取交易金额 以 分 为单位
		Integer fen = getFen(order.getOrderMoney());

		String tradeType = null;
		// 支付回调
		String notifyUrl = null;
		// APPID
		String appId = null;

		// 根据不同的支付方式设置不同的 请求类型 APPID 和支付回调地址
		if (order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_JSAPI.getMode())) {
			// 获取openId
			request.setOpenid(order.getOpenId());
			tradeType = TradeType.JSAPI;
			appId = properties.getJsapiAppId();
			notifyUrl = properties.getJsapiNotifyUrl();
		} else if (order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_APP.getMode())) {
			tradeType = TradeType.APP;
			appId = properties.getAppAppId();
			notifyUrl = properties.getAppNotifyUrl();
		}

		request.setBody(body);
		request.setNonceStr(nonceStr);
		request.setOutTradeNo(outTradeNo);
		request.setTotalFee(fen);
		request.setSpbillCreateIp(order.getCreateIp());
		request.setTradeType(tradeType);
		request.setNotifyUrl(notifyUrl);
		request.setAppid(appId);

		try {
			return this.createOrder(request);
		} catch (WxPayException e) {
			e.printStackTrace();
			throw new PmoException(ResultState.WX_PAY_ORDER_CREATE_ERROR);
		}
	}

	private int getFen(BigDecimal money) {
		return money.multiply(BigDecimal.valueOf(100)).intValue();
	}

	@Override
	public WxPayRefundResult refund(Order order) {
		WxPayRefundRequest request = new WxPayRefundRequest();
		// 设置订单号  
		request.setOutTradeNo(order.getOrderId());
		// 设置订单金额
		request.setTotalFee(getFen(order.getOrderMoney()));
		//退款金额
		request.setRefundFee(getFen(order.getRefundAmount()));
		//退款结果通知url notifyUrl
		request.setNotifyUrl(refundNotifyUrl);
		// 退款单号
		request.setOutRefundNo(order.getWeixinRefundId());
		
		String appId = null;
		// 根据不同的支付方式设置不同的 请求类型 APPID 和支付回调地址
		request.setAppid( getAppId(order, appId));
		
		try {
			return refund(request);
		} catch (WxPayException e) {
			e.printStackTrace();
			throw new PmoException(ResultState.WX_PAY_ORDER_REFUND_ERROR);
		}
	}

	private String getAppId(Order order, String appId) {
		if (order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_JSAPI.getMode())) {
			appId = properties.getJsapiAppId();
		} else if (order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_APP.getMode())) {
			appId = properties.getAppAppId();
		}
		return appId;
	}

	@Override
	public WxPayConfig getConfig() {
		return this.config;
	}
}
