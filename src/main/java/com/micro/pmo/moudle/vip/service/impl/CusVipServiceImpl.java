package com.micro.pmo.moudle.vip.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.mapper.CusVipMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.OrderVipMapper;
import com.micro.pmo.moudle.customer.entity.CusVip;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.entity.OrderVip;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.vip.entity.Vip;
import com.micro.pmo.moudle.vip.service.CusVipService;
import com.micro.pmo.moudle.vip.service.VipService;

/**
 * 
 * 
 * @author wenhaofan
 * @createtime
 */
@Service
public class CusVipServiceImpl implements CusVipService {

	@Autowired
	private CusVipMapper cusVipMapper;

	@Autowired
	private VipService vipService;
	
	@Autowired
	private CustomerMapper cusMapper;
 
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderVipMapper orderVipMapper;
	
	/**
	 *  开通vip
	 * @param orderId
	 * @param vipId
	 * @param cusId
	 */
	@Transactional
	@Override
	public void buyVip(String orderId, Vip vip , Integer cusId) {
		//获取用户的有效vip记录
		Integer month = vip.getVipDuration();
		
		//获取用户的vip记录
		CusVip beforeCusVip = cusVipMapper.getValidCusVip(cusId);
		
		//根据以往的Vip开通记录计算新的Vip到期时间
		Date endStart = getEndDate(month, beforeCusVip);
	 
		CusVip cusVip = new CusVip(endStart, orderId, cusId,new Date(),0, cusId);
		
		// 如果有vip记录则更新剩余有效时间
		if (beforeCusVip != null) {
			beforeCusVip.setVipExpirationDate(endStart);
			cusVipMapper.updateVipExpirationDateById(beforeCusVip);
		}else {
			cusVipMapper.insertCusVip(cusVip);
		}
		
		//更新缓存
		Customer cusOld = cusMapper.getCusAllById(cusId);
		if(cusOld == null || StringUtils.isBlank(cusOld.getCusToken())){
			return ;
		}
		//删除缓存
		CusUtils.removeCusCache(cusOld.getCusToken());
		//更新缓存
		CusUtils.putCusCache(cusOld.getCusToken());
	}

	/**
	 * 获取新的vip结束时间
	 * 
	 * @param month
	 * @param beforeCusVip
	 * @return
	 */
	private Date getEndDate(Integer month, CusVip beforeCusVip) {
		Date newEndDate = null;
		
		//如果没有之前的VIP记录 则直接将下一个的时间作为VIP截止时间
		if(beforeCusVip==null) {
			return  DateUtil.datePlusMonth(new Date(),month);	
		}
  
		Date endDate = beforeCusVip.getVipExpirationDate();
		
		//如果vip未过期则在vip截止时间上加上新增的时间
		if (endDate.after(new Date())) {
			newEndDate = DateUtil.datePlusMonth(endDate,month);
			return newEndDate;
		} 
		 
		return newEndDate = DateUtil.datePlusMonth(new Date(), month);
	}

	/**
	 * 判断是否是vip
	 */
	@Override
	public Boolean cusVipJudge(Integer cusId) {
		Date dateNow = new Date();
		//vip时间，判断是否是vip
		Date vipDate = cusVipMapper.getEndDate(cusId);
		if(vipDate == null || vipDate.getTime() < dateNow.getTime()){
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public PayInfoVO buyVip(BaseInputBuyVO buyInfo) {
		
		Vip vip = vipService.getVipById(buyInfo.getConfigId());
		
		Customer cus = CusUtils.getCustomer();
 
		//如果是账号支付则订单状态为已支付，否则为待支付
		OrderStatusEnum status = buyInfo.isAccountPay()?OrderStatusEnum.SUCCESS: OrderStatusEnum.UNPAID;	
 
		Order order =  new Order(vip.getVipPrice(), OrderPayTypeEnum.VIP.getType(), 
				cus.getCusId(), status.getStatus(),buyInfo.getIp(),
				buyInfo.getPayMode().getMode(),cus.getOpenId(),"VIP购买","支付");
		
		//创建订单
		PayInfoVO orderResult =	orderService.createOrder(order);
		
		OrderVip orderVip = new OrderVip(order.getOrderId(),cus.getCusId(),vip.getVipPrice(),buyInfo.getConfigId(),
				1,vip.getVipPrice(),new Date());
		 
		//保存订单附属信息
		orderVipMapper.insertOrderVip(orderVip);

		//如果是账号支付则对账号进行vip时长增加操作
		if(buyInfo.getType().equals(OrderPayModeEnum.ACCOUNT_PAY.getMode())) {
			buyVip(order.getOrderId(), vip, cus.getCusId());
		}
		
		return orderResult;
	}

	@Override
	public Map<String, Object> vipInfo() {
		//用户id
		Integer cusId = CusUtils.getCusId();
		Date vipDate = cusVipMapper.getEndDate(cusId);
		Map<String, Object> map = Maps.newHashMap(); 
		map.put("failureTime", vipDate);
		if(vipDate == null || vipDate.getTime() < new Date().getTime()){
			map.put("isVip", false);
		}else{
			map.put("isVip", true);
		}
		
		return map;
	}

}