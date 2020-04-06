package com.micro.pmo.moudle.car.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CollectCarMapper;
import com.micro.pmo.mapper.OrderCounterExtendMapper;
import com.micro.pmo.moudle.car.entity.CollectCar;
import com.micro.pmo.moudle.car.service.CollectCarService;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.entity.OrderCounterExtend;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * CollectCarService
 * @author wenhaofan 
 * @createtime 
 */
@Service
public class CollectCarServiceImpl implements CollectCarService {

	@Autowired
	private CollectCarMapper collectCarMapper;
 
	@Autowired
	private OrderCounterExtendMapper orderCounterExtendMapper;
	
	@Autowired
	private CusCounterService cusCounterService;
	
	@Override
	public void giveCollectCar(Integer month,Integer cusId) {
		
		//获取用户的预定收车记录
		CollectCar collectCar = collectCarMapper.getCollectCarByCusId(cusId);
		
		//根据以往的预定收车开通记录计算新的预定收车到期时间
		Date endStart = getEndDate(month, collectCar);
		
		//如果不存在则新建记录
		if(collectCar == null ) {
			collectCar = new CollectCar(endStart,  cusId,new Date());
		}
		
		//初始化失效时间
		collectCar.setExpirationDate(endStart);
		
		// 如果有预定收车记录则更新剩余有效时间
		if (collectCar.getCollectId() != null ) {
			collectCarMapper.updateExpirationDateById(collectCar);
		}else {
			//新增一条预定收车时间
			collectCarMapper.insertCollectCar(collectCar);
		}
		
	}
	
	/**
	 * 获取新的vip结束时间
	 * 
	 * @param month
	 * @param beforeCusVip
	 * @return
	 */
	private Date getEndDate(Integer month, CollectCar beforeCusVip) {
		Date newEndDate = null;
		
		//如果没有之前的VIP记录 则直接将下一个的时间作为VIP截止时间
		if(beforeCusVip==null) {
			return  DateUtil.datePlusMonth(new Date(),month);	
		}
  
		Date endDate = beforeCusVip.getExpirationDate();
		
		//如果vip未过期则在vip截止时间上加上新增的时间
		if (endDate.after(new Date())) {
			newEndDate = DateUtil.datePlusMonth(endDate,month);
			return newEndDate;
		} 
		 
		return newEndDate = DateUtil.datePlusMonth(new Date(), month);
	}

	@Override
	public void paySuccess(String orderId) {
		OrderCounterExtend  order = orderCounterExtendMapper.getOrderCounterExtendById(orderId);
		giveCollectCar(order.getBuyNum(), order.getCusId());
	}

	@Override
	public PayInfoVO buyCollectCar(InputCusCounterBuyVO input) {
		
		if(input.getNum() == null || input.getNum() <= 0) {
			throw new PmoException(ResultState.PARAM_ERROR,"购买月数不能小于1");
		}

		PayInfoVO result = cusCounterService.buy(input, CounterTypeEnum.collectCar);
  
		return result;
	}
	

}