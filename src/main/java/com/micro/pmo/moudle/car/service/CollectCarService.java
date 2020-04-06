package com.micro.pmo.moudle.car.service;

import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月6日 
*/
public interface CollectCarService {

	/**
	 * 赠送预定收车时间
	 * @param configId
	 * @param cusId
	 */
	void giveCollectCar(Integer month,Integer cusId);
	
	/**
	 * 支付成功回调
	 * @param orderId
	 */
	void paySuccess(String orderId);
	
	/**
	 * 购买预定收车
	 * @param input
	 * @return
	 */
	PayInfoVO buyCollectCar(InputCusCounterBuyVO input);
	
}