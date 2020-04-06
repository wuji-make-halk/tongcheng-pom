package com.micro.pmo.moudle.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult.ReqInfo;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.DateTimeUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.commons.utils.sms.AliyunSmsUtil;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.OrderMapper;
import com.micro.pmo.mapper.RefundMapper;
import com.micro.pmo.moudle.account.entity.CusAccount;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.account.vo.AccountPayRefundVO;
import com.micro.pmo.moudle.car.entity.Refund;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderNotifyService;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.service.WxPayService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private OrderNotifyServiceFactory orderNotifyServiceFactory;

	@Autowired
	private CusAccountService cusAccountService;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private RefundMapper refundMapper;

	@Autowired
	private AliyunSmsUtil aliyunSmsUtil;

	/**
	 * 退款模板
	 */
	@Value("${sms.aliyun.refundTemplate}")
	private String refundTemplate;

	/**
	 * 通知后台管理员的手机号
	 */
	@Value("${sms.aliyun.surplusPhone}")
	private String surplusPhone;

	@Override
	public Order getOrderByOrderId(String orderId) {
		return orderMapper.getOrderById(orderId);
	}

	@Override
	public PayInfoVO createOrder(Order order) {

		// 处理订单基础属性
		hanldeOrder(order);

		// 保存订单
		orderMapper.insertOrder(order);

		// 支付订单
		return payOrder(order);
	}

	@Override
	@Transactional
	public PayInfoVO payOrder(Order order) {
		PayInfoVO payInfo = new PayInfoVO(OrderPayModeEnum.getByMode(order.getPaymentMode()),
				OrderPayTypeEnum.getByType(order.getPaymentType()));

		if (isWxPay(order)) {
			// 如果发起的是小程序支付则获取当前登录用户的openID
			if (order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_JSAPI.getMode())) {
				String openId = customerMapper.getOpenIdByCusId(CusUtils.getCusId());

				if (StringUtils.isEmpty(openId)) {
					throw new PmoException(ResultState.WEIXIN_AUTHORIZE_NO);
				}

				order.setOpenId(openId);
			}

			// 处理微信支付
			Object obj = WXPay(order);
			payInfo.setInfo(obj);

			// 修改订单中的预支付订单号
			orderMapper.updateOrder(order);
		} else if (order.getPaymentMode().equals(OrderPayModeEnum.ACCOUNT_PAY.getMode())) {
			// 不能使用账号支付来给账号充值
			if (order.getPaymentType().equals(OrderPayTypeEnum.PAY_ACCOUNT.getType())) {
				throw new PmoException(ResultState.PARAM_ERROR, "下单支付类型错误");
			}
			// 处理账号支付
			accountPay(order);
		} else {
			throw new PmoException(ResultState.PARAM_ERROR, "下单支付类型错误");
		}

		return payInfo;
	}

	/**
	 * 保存订单 不进行支付操作
	 * 
	 * @param order
	 */
	@Override
	public void saveOrderNotPay(Order order) {
		// 处理订单基础属性
		hanldeOrder(order);
		// 保存订单
		orderMapper.insertOrder(order);
	}

	/**
	 * 处理订单基础属性
	 * 
	 * @param order
	 */
	public void hanldeOrder(Order order) {
		order.setCreateTime(new Date());
		order.setRefundAmount(BigDecimal.ZERO);
		// 设置订单id
		order.setOrderId(getOrderId(order.getCusId()));
	}

	/**
	 * 使用账号来支付
	 * 
	 * @param order
	 */
	private void accountPay(Order order) {

		Integer cusId = order.getCusId();

		CusAccount cusAccount = cusAccountService.getCusAccountById(cusId);

		BigDecimal payMoney = order.getOrderMoney();
		// 账号不存在情况
		if (cusAccount == null) {
			throw new PmoException(ResultState.WX_PAY_ACCOUNT_SHORT_OF_FUND, "账号余额不足");
		}
		BigDecimal surplusMoney = cusAccount.getSurplusMoney();

		// 如果余额不够则抛出异常
		if (surplusMoney.compareTo(payMoney) == -1) {
			throw new PmoException(ResultState.WX_PAY_ACCOUNT_SHORT_OF_FUND, "账号余额不足");
		}

		AccountPayRefundVO payInput = new AccountPayRefundVO(cusId, order.getOrderMoney(), order.getOrderRemark(),
				order.getOrderId(), order.getProductName(), order.getPayType());

		// 进行账号扣款
		cusAccountService.payAccount(payInput);

		order.setOrderStatus(OrderStatusEnum.SUCCESS.getStatus());
		// 修改订单状态为支付成功
		orderMapper.updateOrderStatusById(order.getOrderStatus(), order.getOrderId());
	}

	private boolean isWxPay(Order order) {
		return order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_APP.getMode())
				|| order.getPaymentMode().equals(OrderPayModeEnum.WX_PAY_JSAPI.getMode());
	}

	/**
	 * 调用微信支付
	 * 
	 * @param <T>
	 * @param order
	 * @return
	 */
	private <T> T WXPay(Order order) {

		T result = wxPayService.createOrder(order);

		// 支付方式
		Integer payMode = order.getPaymentMode();
		// 获取预支付订单id
		String prepayId = getPrepayId(result, payMode);
		order.setWeixinPrepayId(prepayId);

		return result;
	}

	/**
	 * 获取预支付订单id
	 * 
	 * @param <T>
	 * @param result
	 * @param payMode
	 * @return
	 */
	private <T> String getPrepayId(T result, Integer payMode) {
		String prepayId = null;
		// APP支付
		if (payMode.equals(OrderPayModeEnum.WX_PAY_APP.getMode())) {
			WxPayAppOrderResult appResult = (WxPayAppOrderResult) result;
			prepayId = appResult.getPrepayId();
			// 小程序/H5支付
		} else if (payMode.equals(OrderPayModeEnum.WX_PAY_JSAPI.getMode())) {
			WxPayMpOrderResult appResult = (WxPayMpOrderResult) result;
			prepayId = appResult.getPackageValue().substring(10, appResult.getPackageValue().length());
		}
		return prepayId;
	}

	private String getOrderId(Integer cusId) {
		return DateTimeUtils.getDateNumber() + cusId + "";
	}

	/**
	 * 
	 */
	public void refund(Order order, Refund refundData) {
		
		//保存退款申请信息
		saveRefundData(order, refundData);

		// 如果是账号支付则调用账号退款
		if (order.isAccountPay()) {

			AccountPayRefundVO accountPayRefundVO = new AccountPayRefundVO(order.getCusId(), order.getRefundAmount(),
					order.getOrderRemark(), order.getOrderId(), order.getProductName(), order.getPayType());

			accountRefund(order, accountPayRefundVO);

			return;
		}

		// 执行微信退款
		wxRefund(order);
	}

	private void saveRefundData(Order order, Refund refundData) {
		Refund refund = handleRefundData(order, refundData);

		// 保存退款申请
		refundMapper.insertRefund(refund);
	}

	private Refund handleRefundData(Order order, Refund refundData) {
		// 如果是余额支付则状态为已退款，否则为申请中
		Integer refundStatus = order.isAccountPay() ? 2 : 1;

		String weixinRefundId = UUIDUtils.createSerialNum();
		order.setWeixinRefundId(weixinRefundId);
		
		Integer carId =refundData != null ? refundData.getCarId() : null;
		
		Refund refund = new Refund(order.getWeixinRefundId(), order.getOrderMoney(), order.getRefundAmount(),
				order.getCusId(), carId, order.getOrderId(), new Date(), refundStatus);
		return refund;
	}

	public void wxRefund(Order order) {
		boolean isRefund = order.getRefundAmount().compareTo(BigDecimal.ZERO) < 1;
		if(isRefund) {
			order.setOrderStatus(OrderStatusEnum.REFUNDED.getStatus());
		} 
		
		//更新订单状态
		orderMapper.updateOrder(order);
		
		if(!isRefund) {
			// 发起退款申请
			wxPayService.refund(order);
		}
	}

	public void accountRefund(Order order, AccountPayRefundVO accountPayRefundVO) {
		// 增加账号余额
		cusAccountService.refundAccount(accountPayRefundVO);

		// 修改订单状态为已退款
		order.setOrderStatus(OrderStatusEnum.REFUNDED.getStatus());
		order.setRefundTime(new Date());
		
		orderMapper.updateOrder(order);
	}

	@Override
	public void wxPayNotify(WxPayOrderNotifyResult notifyResult) {

		boolean isSuccess = notifyResult.getReturnCode().equals("SUCCESS");

		String orderId = notifyResult.getOutTradeNo();
		Order order = getOrderByOrderId(orderId);

		//如果订单已经变成了非待支付的状态则直接返回 不进行业务处理
		if(order == null || !order.getOrderStatus().equals(OrderStatusEnum.UNPAID.getStatus())) {
			return;
		}
		// 订单状态处理
		orderStatusHandle(isSuccess, order);
		// 更新订单状态
		orderMapper.updateOrderStatusById(order.getOrderStatus(), order.getOrderId());

		if (isSuccess) {
			// 设置交易订单id
			order.setWeixinTransactionId(notifyResult.getTransactionId());
		} else {
			// TODO 处理支付失败的情况
		}

		new Thread(() -> {
			try {
				OrderNotifyService orderNotifyService = orderNotifyServiceFactory
						.getOrderNotifyService(OrderPayTypeEnum.getByType(order.getPaymentType()));
				orderNotifyService.notifyPay(notifyResult);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}).start();

	}

	private void orderStatusHandle(boolean isSuccess, Order order) {
		if (isSuccess) {
			order.setOrderStatus(OrderStatusEnum.SUCCESS.getStatus());
		} else {
			order.setOrderStatus(OrderStatusEnum.ERROR.getStatus());
		}
	}

	@Override
	public void wxRefundNotify(WxPayRefundNotifyResult notifyResult) {

		boolean isSuccess = notifyResult.getReturnCode().equals("SUCCESS");

		ReqInfo reqInfo = notifyResult.getReqInfo();
		String orderId = reqInfo.getOutTradeNo();
		Order order = getOrderByOrderId(orderId);

		//如果已处理了退款 则不再继续处理
		if(order == null || order.getOrderStatus().equals(OrderStatusEnum.REFUNDED.getStatus())) {
			return;
		}
		
		if (isSuccess) {
			// 修改退款信息
			order.setWeixinRefundId(reqInfo.getRefundId());
			order.setRefundAmount(BigDecimal.valueOf(reqInfo.getRefundFee()));
			// 更新订单
			Order updateOrder = new Order(orderId, reqInfo.getRefundId(), BigDecimal.valueOf(reqInfo.getRefundFee()).divide(BigDecimal.valueOf(100)), new Date(), OrderStatusEnum.REFUNDED.getStatus());
			orderMapper.updateOrder(updateOrder);
			// 修改退款表状态
			refundMapper.updateRefundStatusById(reqInfo.getOutRefundNo(), 2);
		} else {
			// 更新订单为退款失败状态
			orderMapper.updateOrderStatusById(OrderStatusEnum.REFUNDED_ERROR.getStatus(), orderId);
			// 修改退款表状态
			refundMapper.updateRefundStatusById(reqInfo.getRefundId(), 3);

			// 异步通知后台管理员 退款失败
			aliyunSmsUtil.asyncSendSms(refundTemplate, surplusPhone, orderId);
		}

		new Thread(() -> {
			try {
				// 调用不同模块的退款业务处理
				OrderNotifyService orderNotifyService = orderNotifyServiceFactory
						.getOrderNotifyService(OrderPayTypeEnum.getByType(order.getPaymentType()));
				orderNotifyService.notifyRefund(notifyResult);
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public void refund(Order order) {
		this.refund(order, null);
	}

}
