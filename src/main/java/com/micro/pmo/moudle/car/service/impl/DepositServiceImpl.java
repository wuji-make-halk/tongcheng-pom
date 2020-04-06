package com.micro.pmo.moudle.car.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.commons.utils.sms.AliyunSmsUtil;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CusAccountMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.DepositMapper;
import com.micro.pmo.mapper.OrderDepositMapper;
import com.micro.pmo.mapper.OrderMapper;
import com.micro.pmo.moudle.account.entity.CusAccount;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.OrderDeposit;
import com.micro.pmo.moudle.car.entity.Refund;
import com.micro.pmo.moudle.car.enu.OrderDepositStatusEnum;
import com.micro.pmo.moudle.car.service.DepositService;
import com.micro.pmo.moudle.car.vo.InputDepositBuyVO;
import com.micro.pmo.moudle.car.vo.InputDepositPayVO;
import com.micro.pmo.moudle.car.vo.UserOrderDepositVO;
import com.micro.pmo.moudle.config.admin.entity.Deposit;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月24日
 */
@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private DepositMapper depositMapper;

	@Autowired
	private OrderDepositMapper orderDepositMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private AliyunSmsUtil aliyunSmsUtil;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CusAccountMapper cusAccountMapper;
	
	/**
	 *  待付款通知，通知卖方
	 */
	@Value("${sms.aliyun.orderTemplate}")
	private String waitPaySMSTemplate;
	
	/**
	 * 新订单提示 针对后台管理员
	 */
	@Value("${sms.aliyun.newOrderTemplate}")
	private String newOrderTemplate;
 

	@Value("${sms.aliyun.abnormalTemplate}")
	private String abnormalTemplate;
	

	/**
	 *  接收异常订单
	 */
	@Value("${sms.aliyun.adminPhone}")
	private String adminPhone;
	
	/**
	 * 接收普通短信提示（商户余额不足提示  新订单提示）
	 */
	@Value("${sms.aliyun.surplusPhone}")
	private String surplusPhone;
	
	@Override
	public void depostPaySuccess(String orderId) {
		OrderDeposit orderDeposit = orderDepositMapper.getOrderDepositByOrderId(orderId);
		
		depositPaySuccess(orderDeposit);
	}

	private void depositPaySuccess(OrderDeposit orderDeposit) {
	
		aliyunSmsUtil.asyncSendSms(newOrderTemplate, surplusPhone, orderDeposit.getOrderId());
		
		//如果是卖家支付成功则将买家和卖家的订金状态改为正在交易中
		if(orderDeposit.getType().equals(1)) {
			changeDepositOrderTrading(orderDeposit);
			return;
		}  
		// 将附属订单状态修改为等待卖家支付
		orderDepositMapper.updateStatus(orderDeposit.getOrderDepositId(), 4);
		// 获取定金的附属订单
		// 修改车辆状态为已有订金
		carMapper.updateIsOrderByCarId(orderDeposit.getCarId(), true);
		// 创建卖家订单
		createSellerDepositOrder(orderDeposit);
		
		//取消未支付的订金主订单
		orderDepositMapper.cancleNoPayOrderByCarId(orderDeposit.getCarId(),5);
		//取消未支付的订金记录
		orderDepositMapper.cancleNoPayDepositByCarId(orderDeposit.getCarId());
	}

	@Override
	public void depositRefundSuccess(String orderId) {
		OrderDeposit orderDeposit = orderDepositMapper.getOrderDepositByOrderId(orderId);
		
		depositRefundSuccess(orderDeposit);
	}

	/**
	 * 处理退款成功
	 * @param orderDeposit
	 */
	@Transactional
	private void depositRefundSuccess(OrderDeposit orderDeposit) {
		OrderDeposit otherOrderDeposit = null;
		if(orderDeposit.getType().equals(0)) {
			//如果是买家 则获取卖家订金记录,找出卖家
			otherOrderDeposit = orderDepositMapper.getOrderDepositOtherByOrderId(orderDeposit.getBuyerOrderId(),0);
		}else {
			//如果是卖家则获取买家定金记录，找出买家
			otherOrderDeposit = orderDepositMapper.getOrderDepositOtherByOrderId(orderDeposit.getBuyerOrderId(),0);
		}
		
		Integer status = null;

		if(orderDeposit.getStatus().equals(OrderDepositStatusEnum.AFFIRM.getValue()) && otherOrderDeposit.getStatus().equals(OrderDepositStatusEnum.AFFIRM.getValue())) {
			//如果双方确认交易交易 将双方状态修改为交易成功
			status = OrderDepositStatusEnum.SUCCESS.getValue();
			//车辆状态改为下架
			carMapper.updateCarStatusById(orderDeposit.getCarId(), 2);
		}else if(orderDeposit.getStatus().equals(OrderDepositStatusEnum.ERROR.getValue()) && orderDeposit.getStatus().equals(OrderDepositStatusEnum.ERROR.getValue())) {
			//如果双方交易失败 则将双方状态修改为取消交易
			status = OrderDepositStatusEnum.REFUND.getValue();
			//将车辆放放出来
			carMapper.updateIsOrderByCarId(orderDeposit.getCarId(), false);
			//双方交易异常则将车辆状态改为在售
			carMapper.updateCarStatusById(orderDeposit.getCarId(), 1);
		}else {
			//将车辆放放出来
			carMapper.updateIsOrderByCarId(orderDeposit.getCarId(), false);
			//双方交易异常则将车辆状态改为在售
			carMapper.updateCarStatusById(orderDeposit.getCarId(), 1);
			
			status = OrderDepositStatusEnum.CANCEL_TRADE.getValue();
		}
		
		orderDepositMapper.updateStatus(orderDeposit.getOrderDepositId(), status);
		
		orderDepositMapper.updateStatus(otherOrderDeposit.getOrderDepositId(), status);
	}
 
 
	
	public BigDecimal getRefundMoney(OrderDeposit orderDeposit, Deposit deposit) {
		// 获取手续费
		BigDecimal serviceCharge = getServiceCharge(orderDeposit, deposit);
		// 退款金额
		BigDecimal refundMoney = orderDeposit.getMoney().subtract(serviceCharge);
		return refundMoney;
	}

	/**
	 * 获取手续费
	 * 
	 * @param orderDeposit
	 * @param deposit
	 * @return
	 */
	public BigDecimal getServiceCharge(OrderDeposit orderDeposit, Deposit deposit) {
		return deposit.getRefundMoney();
	}

	/**
	 * 发起退款 会扣除手续费
	 * 
	 * @param orderId
	 */
	@Transactional
	public void refundDeposit(OrderDeposit orderDeposit) {

		Order order = orderMapper.getOrderById(orderDeposit.getOrderDepositId());
		
		/**
		 *初始化退款退款金额
		 */
		handleRefund(orderDeposit, order);
		
		refundDeposit(order,orderDeposit);
	}

	private void handleRefund(OrderDeposit orderDeposit, Order order) {
		Deposit deposit = depositMapper.getDeposit();
		
		// 获取退款金额
		BigDecimal refundMoney = getRefundMoney(orderDeposit, deposit);
		//服务费
		BigDecimal serviceCharge = getServiceCharge(orderDeposit, deposit);
		
		order.setRefundAmount(refundMoney);
		order.setServiceCharge(serviceCharge);
	}

	/**
	 * 发起退款
	 * @param order
	 */
	@Override
	public void refundDeposit(Order order,OrderDeposit orderDeposit) {
		
		//初始化商品名称
		Car car = carMapper.carInfoById(orderDeposit.getCarId());
		order.setProductName(car.getBrandSeries());
		order.setPayType("定金托管");
		
		// 发起订单退款
		orderService.refund(order,new Refund(orderDeposit.getCarId()));

		// 如果是账号退款则直接修改为已退款
		if (order.isAccountPay()) {
			depositRefundSuccess(order.getOrderId());
		}
	}
 	
	@Override
	public void confirmDepositOrder(String orderId, Integer status) {
		// 获取定金订单附属记录
		OrderDeposit orderDeposit = orderDepositMapper.getOrderDepositByOrderId(orderId);
		orderDeposit.setStatus(status);
		// 更新状态
		orderDepositMapper.updateDepositStatus(orderDeposit);
		// 处理双方是否确认成交等操作
		handleDepositConfirm(orderDeposit);
	}

	@Override
	@Transactional
	public PayInfoVO stickPayDepositOrder(InputDepositPayVO input) {
		// 获取卖家订单
		Order order = orderService.getOrderByOrderId(input.getOrderId());
		order.setCreateIp(input.getIp());
		order.setPaymentMode(input.getPayMode().getMode());
		
		OrderDeposit orderDeposit = orderDepositMapper.getOrderDepositByOrderId(input.getOrderId());
		
		//初始化商品名称
		Car car = carMapper.carInfoById(orderDeposit.getCarId());
		order.setProductName(car.getBrandSeries());
		order.setPayType("定金托管");
		order.setPaymentMode(input.getType());
		PayInfoVO payInfo = orderService.payOrder(order);

		
		// 更新支付类型 支付ip
		Order updateOrder = new Order(order.getOrderId(), order.getPaymentMode(), input.getIp());
		orderMapper.updateOrder(updateOrder);
		
		//如果是账号支付则直接判定为支付成功
		if(input.isAccountPay()) {
			String sellerOrderId = input.getOrderId();
			depostPaySuccess(sellerOrderId);
		}
		
		return payInfo;
	}

	@Override
	@Transactional
	public PayInfoVO payDeposit(InputDepositBuyVO input) {

		Car car = carMapper.carInfoById(input.getCarId());

		// 校验是否允许支付
		checkHandle(input, car);

		// 订单支付状态
		OrderStatusEnum orderStatus = input.isAccountPay() ? OrderStatusEnum.SUCCESS : OrderStatusEnum.UNPAID;
		
		// 定金附属订单状态
		Integer status = input.isAccountPay() ? 0 : 3;

		Customer cus = CusUtils.getCustomer();

		// 获取定金配置
		Deposit deposit = depositMapper.getDeposit();

		// 定金附属订单
		OrderDeposit orderDeposit = new OrderDeposit(deposit.getCollectMoney(), cus.getCusId(),
				input.getCarId(), new Date(), status, 0 , null , UUIDUtils.createSerialNum());

		// 主订单
		Order order = new Order(deposit.getCollectMoney(), OrderPayTypeEnum.DEPOSIT.getType(),
				cus.getCusId(), orderStatus.getStatus(), input.getIp(), input.getPayMode().getMode(),
				cus.getOpenId(),car.getBrandSeries(),"定金托管");

		// 保存订单
		PayInfoVO result = orderService.createOrder(order);
		// 设置订单id
		orderDeposit.setOrderDepositId(order.getOrderId());

		// 保存订单附属信息
		orderDepositMapper.insertOrderDeposit(orderDeposit);

		// 如果是账号支付则进行定金支付成功的操作
		if (input.isAccountPay()) {
			depostPaySuccess(order.getOrderId());
		}

		return result;
	}

	/**
	 * 创建卖家订单
	 * 
	 * @param orderId
	 * @param carId
	 */
	public void createSellerDepositOrder(OrderDeposit buyerOrderDeposit) {
		// 获取定金配置
		Deposit deposit = depositMapper.getDeposit();
		Car carInfo = carMapper.carInfoById(buyerOrderDeposit.getCarId());

		// 创建定金附属订单
		OrderDeposit orderDeposit = new OrderDeposit(deposit.getCollectMoney(),
				carInfo.getCreator(), buyerOrderDeposit.getCarId(), new Date(), OrderDepositStatusEnum.NOPAY.getValue(), 1, buyerOrderDeposit.getOrderDepositId(),buyerOrderDeposit.getOrderId());

		// 主订单
		Order order = new Order(deposit.getCollectMoney(), OrderPayTypeEnum.DEPOSIT.getType(),
				carInfo.getCreator(), OrderStatusEnum.UNPAID.getStatus());

		// 保存订单
		orderService.saveOrderNotPay(order);

		// 关联主订单id
		orderDeposit.setOrderDepositId(order.getOrderId());
		// 保存定金订单
		orderDepositMapper.insertOrderDeposit(orderDeposit);
		//买家更新数据
		orderDepositMapper.updateBuyDepositId(buyerOrderDeposit.getOrderDepositId(), orderDeposit.getOrderDepositId());
		
		
		
		//给卖家发送短信 TODO 待打开
		Customer sellerCus = customerMapper.getCustomerById(carInfo.getCreator());
		aliyunSmsUtil.asyncSendSms(waitPaySMSTemplate, sellerCus.getCusPhone(), order.getOrderId());
	}


	/**
	 * 根据卖家定金记录ID将买家和卖家的记录状态都修改为正在交易
	 * @param sellerOrderId
	 */
	private void changeDepositOrderTrading(OrderDeposit sellerOrderDeposit) {

		// 买家订单定金记录
		OrderDeposit buyerOrderDeposit = orderDepositMapper.getOrderDepositByOrderId(sellerOrderDeposit.getBuyerOrderId());
		// 修改买家定金记录为正在交易
		orderDepositMapper.updateStatus(buyerOrderDeposit.getBuyerOrderId(), 0);
		// 修改卖家定金记录为正在交易
		orderDepositMapper.updateStatus(buyerOrderDeposit.getOrderDepositId(), 0);
	}
	
	private void checkHandle(InputDepositBuyVO input, Car car) {

		if (car == null || !car.getCarStatus().equals(1)) {
			throw new PmoException(ResultState.CAR_INFO_ERROR);
		}

		Integer cusId = CusUtils.getCusId();

		Integer depositType = cusId.equals(car.getCreator()) ? 1 : 0;

		// 不能购买自己发布的车辆
		if (depositType.equals(1)) {
			throw new PmoException(ResultState.DEPOSIT_ERROR_PAY);
		}

		// 如果车辆已经交了定金则买家不能继续操作
		if (car.getIsOrder() && depositType.equals(0)) {
			throw new PmoException(ResultState.DEPOSIT_PAY_REPEAT);
		}
	}

	/**
	 *  
	 * 处理订金订单确认
	 * @param carId
	 */
	public void handleDepositConfirm(OrderDeposit orderDeposit) {

		OrderDeposit otherOrderDeposit = null;
				
		if(StringUtils.isEmpty(orderDeposit.getBuyerOrderId())) {
			otherOrderDeposit = orderDepositMapper.getOrderDepositOtherByOrderId(orderDeposit.getOrderDepositId(), 0);
		}else {
			otherOrderDeposit = orderDepositMapper.getOrderDepositOtherByOrderId(orderDeposit.getOrderDepositId(), 1);
		}
 
		if (otherOrderDeposit.getStatus().equals(OrderDepositStatusEnum.AFFIRM.getValue()) && orderDeposit.getStatus().equals(OrderDepositStatusEnum.AFFIRM.getValue())) {
			//双方确认了交易

		}else if(otherOrderDeposit.getStatus().equals(OrderDepositStatusEnum.ERROR.getValue()) && orderDeposit.getStatus().equals(OrderDepositStatusEnum.ERROR.getValue())) {
			
			//双方没有确认交易
		}else {
			//异常订单 不进行退款操作
			//TODO 发送短信通知后台
			aliyunSmsUtil.asyncSendSms(abnormalTemplate, adminPhone, orderDeposit.getOrderId());
			return;
		}
 
		//买家和卖家退款
		refundDeposit(orderDeposit);
		 
		refundDeposit(otherOrderDeposit);
	}

	@Override
	public PageInfo<UserOrderDepositVO> listUserOrderDeposit(BaseQuery baseQuery,Integer type) {
		PageHelper.startPage(baseQuery.getPageNumKey(), baseQuery.getPageSizeKey());
		Integer cusId = CusUtils.getCusId();
		List<UserOrderDepositVO> list = orderDepositMapper.listUserOrderDeposit(cusId, type);
		
		if (list.isEmpty()) {
			return new PageInfo<UserOrderDepositVO>(list);
		}
		
		//处理双方确认时对方的交易状态
		handleOtherDealStatus(type, list);

		return new PageInfo<UserOrderDepositVO>(list);
	}

	private void handleOtherDealStatus(Integer type, List<UserOrderDepositVO> list) {
		list.forEach(deposit->{
			
			if(!OrderDepositStatusEnum.TRADING.getValue().equals(deposit.getDealStatus())) {
				return;
			}
			
			Integer status = orderDepositMapper.getOtherStatusByOrderId(deposit.getOrderId(), type == 0 ? 0: 1);
			
			String role = type == 1 ? "买家":"卖家";
			
			String statusStr = null;
			
			if(OrderDepositStatusEnum.AFFIRM.getValue().equals(status)) {
				statusStr = role.concat(OrderDepositStatusEnum.AFFIRM.getRemark());
			}else if(OrderDepositStatusEnum.ERROR.getValue().equals(status)) {
				statusStr = role.concat(OrderDepositStatusEnum.ERROR.getRemark());
			}
			
			deposit.setDealStatusStr(statusStr);
		});
	}

	@Override
	@Transactional
	public void applyRefund(String orderId,Integer type) {

		Integer cusId = CusUtils.getCusId();
	
		Order order = orderService.getOrderByOrderId(orderId);
		
		
		//如果当前登录用户不是该订单的创建者
		if(order.getCusId() != cusId) {
			throw new PmoException(ResultState.DEPOSIT_STICK_NOT_SELF);
		}
		//主订单未支付状态处理成已取消
		if(order.getOrderStatus() == OrderStatusEnum.UNPAID.getStatus()){
			//买家未付款
			if(type == 0){
				//主订单取消
				orderMapper.updateOrderStatusById(5, orderId);
				//附属表取消
				orderDepositMapper.updateStatus(orderId, 7);
				return ;
			}

			//找出卖家定金订单信息 
			OrderDeposit  sellerOrderDeposit = orderDepositMapper.getOrderDepositByOrderId(orderId);
			
			//如果当前订金信息是买家的订单则不能取消
			if(sellerOrderDeposit.getType().equals(0)) {
				throw new PmoException(ResultState.PARAM_ERROR,"买家未付款不能取消");
			}
			
			//卖家附属表状态改变,变成交易已取消
			//卖家主订单取消
			orderMapper.updateOrderStatusById(5, orderId );
			//卖家附属表交易关闭
			orderDepositMapper.updateStatus(orderId, 7);
			
			//卖家未付款。因为此时买家已经付款,买家执行退款操作
			
			Order buyerOrder =  orderMapper.getOrderById(sellerOrderDeposit.getBuyerOrderId());
			//处理买家退款
			launchRefund(buyerOrder, sellerOrderDeposit.getCarId());
			
			return ;
		}
		//订单是支付成功的
		if(order.getOrderStatus() == OrderStatusEnum.SUCCESS.getStatus()){
			//买家取消订单，判断卖家的状态
			if(type == 0){
				OrderDeposit seller =  orderDepositMapper.getOrderDepositOtherByOrderId(orderId, 1);
				Order sellerOrder = orderMapper.getOrderById(seller.getOrderDepositId());
				//如果卖家已支付 则不能申请退款
				if(sellerOrder.getOrderStatus() != OrderStatusEnum.UNPAID.getStatus()){
					throw new PmoException(ResultState.DEPOSIT_BUYER_PAID);
				}
				//卖家未支付
				//卖家主订单取消
				orderMapper.updateOrderStatusById(5, seller.getOrderDepositId());
				//卖家附属表交易关闭
				orderDepositMapper.updateStatus(seller.getOrderDepositId(), 7);
				//买家发起退款
				launchRefund(order, seller.getCarId());
				return ;
			}
			throw new PmoException(ResultState.DEPOSIT_SELLER_PAID);
			
		}
		//以下状态都是不支持退款
		throw new PmoException(ResultState.ORDER_CANCEL_ERROR);

	}
	
	/**
	 * 发起退款
	 * @param order
	 * @param carId
	 */
	private void launchRefund(Order order,Integer carId){
	
		String orderId = order.getOrderId();
		
		//全额退款
		order.setRefundAmount(order.getOrderMoney());
		order.setServiceCharge(BigDecimal.ZERO);
		//退款订单id，与原订单id要不同 TODO
		Date newDate = new Date();
		String refundId = UUIDUtils.createSerialNum();

		Car car = carMapper.carInfoById(carId);
		
		order.setProductName(car.getBrandSeries());
		order.setPayType("订金托管");
		
		//退款处理 TODO
		order.setWeixinRefundId(refundId);
		order.setRefundAmount(order.getOrderMoney());
		order.setRefundTime(newDate);
		
		//发起退款操作
		orderService.refund(order , new Refund(carId));
		
		//如果订单是余额支付则直接处理退款成功的操作
		if (order.isAccountPay()) {
			//退到到账户余额
			CusAccount cusAccount = cusAccountMapper.getCusAccountById(order.getCusId());
			BigDecimal surplusMoney = cusAccount.getSurplusMoney().add(order.getOrderMoney());
			cusAccountMapper.updateSurplusMoneyById(cusAccount.getAccountId(), surplusMoney, new Date());
			//处理退款
			depositRefundSuccess(orderId);
		}
	}

	@Override
	public Kv<String, Object> depositInfo(String orderId, Integer type) {
		
		Integer cusId = CusUtils.getCusId();
		
		UserOrderDepositVO seller = null;
		UserOrderDepositVO buyer = null;
		
		Integer orderStatus = null;
		String dealStatusStr = null;
		String comOrderId = null;
		//Order order = orderMapper.getOrderById(orderId);
		Car car = null;
		if(type.equals(0)) {
			
			buyer = orderDepositMapper.getUserOrderDeposit(orderId, 0);
			
			if(buyer == null ) {
				throw new PmoException(ResultState.PARAM_ERROR,"参数异常");
			}
			comOrderId = buyer.getCommonOrderId();
			dealStatusStr = buyer.getDealStatusStr();
			orderStatus = buyer.getDealStatus();
			seller = orderDepositMapper.getUserOrderDeposit(orderId, 1);
			car = carMapper.carInfoById(buyer.getCarId());
			if(seller == null){
				Customer cus = customerMapper.getCustomerById(car.getCreator());
				seller = new UserOrderDepositVO();
				seller.setCusId(car.getCreator());
				seller.setCusHeadImg(cus.getCusAvatar());
				seller.setCusName(cus.getCusNick());
				seller.setCarId(car.getCarId());
				seller.setDepositPrice(BigDecimal.ZERO);
				seller.setType(1);
				seller.setDealStatus(3);
			}
			
		}else {
			seller =  orderDepositMapper.getUserOrderDeposit(orderId, 0);
			
			if(seller == null ) {
				throw new PmoException(ResultState.PARAM_ERROR,"参数异常");
			}
			comOrderId = seller.getCommonOrderId();
			
			orderStatus = seller.getDealStatus();
			dealStatusStr = seller.getDealStatusStr();
			buyer = orderDepositMapper.getUserOrderDeposit(seller.getBuyerOrderId(), 0);
			car = carMapper.carInfoById(buyer.getCarId());
		}
		
		//如果买家和卖家订金订单发起者都不是当前登录用户则表示当前请求为非法查看
		if(!buyer.getCusId().equals(cusId) && !seller.getCusId().equals(cusId)) {
			throw new PmoException(ResultState.PARAM_ERROR);
		}
		
		//定金配置
		Deposit depositConfig = depositMapper.getDeposit();
	
		Kv<String,Object> result = Kv.by("car", car);
		result.set("buyer", buyer)
		.set("seller", seller)
		.set("orderStatus", orderStatus)
		.set("currentOrderId", orderId)
		.set("dealStatusStr", dealStatusStr)
		.set("depositConfig", depositConfig)
		.set("comOrderId",comOrderId);
		
		return result;
	}

	
	
}
