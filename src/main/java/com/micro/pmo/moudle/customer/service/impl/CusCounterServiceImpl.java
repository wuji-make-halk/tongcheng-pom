package com.micro.pmo.moudle.customer.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.mapper.CounterRecordMapper;
import com.micro.pmo.mapper.CusCounterMapper;
import com.micro.pmo.mapper.OrderCounterExtendMapper;
import com.micro.pmo.moudle.car.service.CollectCarService;
import com.micro.pmo.moudle.config.admin.configEnum.CounterRecordTypeEnum;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.customer.entity.CounterRecord;
import com.micro.pmo.moudle.customer.entity.CusCounter;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.entity.OrderCounterExtend;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.extension.service.PromotionService;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.service.InsuranceService;

/**
 * UsercounterRecordService
 * 
 * @author
 * @createtime
 */
@Service
public class CusCounterServiceImpl implements CusCounterService {

	@Autowired
	private CounterConfigMapper counterConfigMapper;

	@Autowired
	private CusCounterMapper cusCounterMapper;

	@Autowired
	private CounterRecordMapper counterRecordMapper;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderCounterExtendMapper orderExtendMapper;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private CollectCarService collectCarService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Override
	public CounterInfoVO getCounterInfo(CounterTypeEnum type) {
		if (type == null) {
			return new CounterInfoVO();
		}
		Integer cusId = CusUtils.getCustomer().getCusId();
		// 1.用户统计次数
		CusCounter cusCounter = cusCounterMapper.getValidCusCounterByTypeAndCusId(type, cusId);

		CounterConfig counterConfig = counterConfigMapper.getValidCounterConfigByType(type);

		Integer surplusCount = cusCounter == null ? 0 : cusCounter.getSurplusCount();
		// 2 如果查询推广信息则购买价格
		return new CounterInfoVO(surplusCount, type.getUnit(), counterConfig.getConfigId(),
				counterConfig.getConfigPrice().stripTrailingZeros());
	}

	/***
	 * 扣除次数
	 */
	@Override
	@Transactional
	public CounterConfig reduceSend(CounterTypeEnum type, Integer number) {
		if (type == null || number == null) {
			return null;
		}
		Integer cusId = CusUtils.getCustomer().getCusId();

		CusCounter cusCounter = cusCounterMapper.getValidCusCounterByTypeAndCusId(type, cusId);
		// 判断次数
		if (number <= 0 || cusCounter == null || cusCounter.getSurplusCount() == null
				|| cusCounter.getSurplusCount() < number) {
			throw new PmoException(ResultState.SEND_USE_UP);
		}

		CounterRecord counterRecord = new CounterRecord(cusId, type, new Date(), CounterRecordTypeEnum.USE.getType());

		cusCounter.setSurplusCount(cusCounter.getSurplusCount() - number);

		// 设置新的使用次数
		Integer useCount = cusCounter.getUseCount() == null ? 0 : cusCounter.getUseCount();
		cusCounter.setUseCount(useCount + number);

		// 更新剩余次数
		cusCounterMapper.updateCusCounterById(cusCounter);

		// 保存操作记录
		counterRecordMapper.insertCounterRecord(counterRecord);
		// 返回配置信息
		CounterConfig counterConfig = counterConfigMapper.getValidCounterConfigByType(type);

		return counterConfig;
	}
 
	@Override
	@Transactional
	public void addCounter(Integer cusId, String orderId, CounterTypeEnum type, Integer num) {

		CounterConfig config = counterConfigMapper.getBuyInfoByType(type);

		if (config == null) {
			throw new NullPointerException();
		}
 
		// 根据类型和用户id获取计数表
		CusCounter cusCounter = cusCounterMapper.getValidCusCounterByTypeAndCusId(config.getConfigType(), cusId);
		
		//如果购买的是天数单位则统一计算为一次
		Integer totalCount = config.getConfigType().isTime() ? 1 : num;
		Integer surplusCount = config.getConfigType().isTime() ? 1 : num;
		Integer buyCount = config.getConfigType().isTime() ? 1 : num;
		
		if(cusCounter == null) {
			cusCounter = new CusCounter();
			cusCounter.setSendCount(0);
		}else {
			//累加原有的次数
			totalCount+=cusCounter.getTotalCount();
			surplusCount+=cusCounter.getSurplusCount();
			buyCount+=cusCounter.getBuyCount();
		}

		//设置次数信息
		cusCounter.setTotalCount(totalCount);
		cusCounter.setSurplusCount(surplusCount);
		cusCounter.setBuyCount(buyCount);
	
		cusCounter.setCounterType(type);
		cusCounter.setCusId(cusId);
		
		if(cusCounter.getCounterId() == null ) {
			cusCounter.setGmtCreate(new Date());
			//如果没有记录则新增
			cusCounterMapper.insertCusCounter(cusCounter);
		}else {
			//否则修改
			cusCounterMapper.updateCusCounterById(cusCounter);
		}
	}
	
	@Override
	public void saveBuyLog(Integer cusId, String orderId, Integer num, CounterTypeEnum type) {
		CounterConfig config = counterConfigMapper.getBuyInfoByType(type);
		saveBuyLog(cusId, orderId, num, config);
	}
	
 
	public void saveBuyLog(Integer cusId, String orderId, Integer num, CounterConfig config) {
		// 购买记录信息
		CounterRecord sendRecord = new CounterRecord(cusId, config.getConfigType(), new Date(), config.getConfigId(),
				num, CounterRecordTypeEnum.BUY.getType(), orderId);

		// 保存购买记录
		counterRecordMapper.insertCounterRecord(sendRecord);
	}
	
	@Override
	@Transactional
	public PayInfoVO buy(InputCusCounterBuyVO input,CounterTypeEnum counterType) {
		CounterConfig config =counterConfigMapper.getBuyInfoByType(counterType);
		
		Customer cus = CusUtils.getCustomer();
		
		BigDecimal orderMoney = config.getConfigPrice().multiply(new BigDecimal(input.getNum()));
		
		//如果是账号支付则订单状态为已支付，否则为待支付
		OrderStatusEnum status = input.isAccountPay()?OrderStatusEnum.SUCCESS: OrderStatusEnum.UNPAID;	
		Order order =  new Order(orderMoney, OrderPayTypeEnum.EXTENSION.getType(), 
				cus.getCusId(), status.getStatus(),input.getIp(),
				input.getPayMode().getMode(),
				cus.getOpenId(),config.getConfigType().getConfigName(),"支付");
		
		//创建订单
		PayInfoVO orderResult = orderService.createOrder(order);
		
		OrderCounterExtend orderExtend = new OrderCounterExtend(order.getOrderId(),cus.getCusId(),config.getConfigPrice(),input.getConfigId(),
				input.getNum(),orderMoney,new Date(),counterType,input.getCarId());
		 
		//保存订单附属信息
		orderExtendMapper.insertOrderCounterExtend(orderExtend);
		//如果是账号支付则调用支付成功方法
		if (input.isAccountPay()) {
			 paySuccess(order.getOrderId());
		}
		orderResult.setOrderId(order.getOrderId());
		return orderResult;
	}

	@Override
	@Transactional
	public void paySuccess(String orderId) {
		
		OrderCounterExtend order = orderExtendMapper.getOrderCounterExtendById(orderId);
		
		//添加购买日志
		saveBuyLog(order.getCusId(), orderId, order.getBuyNum(), order.getType());

		if(order.getType().equals(CounterTypeEnum.promotion)) {
			//调用推广购买成功的方法
			promotionService.promotionPaySuccess(orderId, order.getCarId(), order.getBuyNum());
		}else if(order.getType().equals(CounterTypeEnum.flush)) {
			//调用刷新购买成功方法
			promotionService.flushPaySuccess(orderId, order.getCarId(), order.getBuyNum());
		}else if (order.getType().equals(CounterTypeEnum.collectCar)) {
			//调用预定收车
			collectCarService.paySuccess(orderId);
		}else if (order.getType().equals(CounterTypeEnum.queryPrice)) {
			//新增查询成交价次数
			addCounter(order.getCusId(), orderId, CounterTypeEnum.queryPrice, order.getBuyNum());
		}else if(order.getType().equals(CounterTypeEnum.insurance)) {
			//调用保险支付成功方法
			insuranceService.paySuccess(orderId);
		}
		
	}
}