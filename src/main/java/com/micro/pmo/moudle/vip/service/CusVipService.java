package com.micro.pmo.moudle.vip.service;

import java.util.Map;

import com.micro.pmo.moudle.order.vo.BaseInputBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.vip.entity.Vip;

/**
 * vip充值Service
 * 
 * @author wenhaofan
 * @createtime
 */

public interface CusVipService {

	/**
	 *  支付回调后开通vip
	 * @param orderId
	 * @param vip
	 * @param cusId
	 */
	public void buyVip(String orderId, Vip vip , Integer cusId) ;
	/***
	 * 判断是否是vip
	 * 
	 * @param cusId
	 * @return
	 */
	public Boolean cusVipJudge(Integer cusId);

	/**
	 * 购买vip
	 * @param vipConfig
	 * @param num
	 * @return
	 */
	public PayInfoVO buyVip(BaseInputBuyVO buyInfo);
	
	/***
	 * vip详情处理
	 * @return
	 */
	public Map<String,Object> vipInfo();

}