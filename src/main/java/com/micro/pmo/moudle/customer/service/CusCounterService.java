package com.micro.pmo.moudle.customer.service;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月9日
 */
public interface CusCounterService {
	
	/**
	 * 支付成功
	 * @param orderId
	 */
	void paySuccess(String orderId);

	/**
	 * 获取次数信息（包含价格）
	 * @param type
	 * @return
	 */
	CounterInfoVO getCounterInfo(CounterTypeEnum type);

	/**
	 * 添加次数信息
	 * @param cusId
	 * @param orderId
	 * @param type
	 * @param num
	 */
	void addCounter(Integer cusId,String orderId,CounterTypeEnum type,Integer num);

	/**
	 * 根据类型减少指定用户赠送次数
	 * 
	 * @param type
	 * @param cusId
	 * @return 使用记录id
	 */
	CounterConfig reduceSend(CounterTypeEnum type, Integer number);

	
	/**
	 * 购买
	 * @param configId
	 * @param count
	 */
	PayInfoVO buy(InputCusCounterBuyVO input,CounterTypeEnum counterType);
	
	/**
	 * 保存购买日志
	 * @param cusId
	 * @param orderId
	 * @param num
	 * @param type
	 */
	void saveBuyLog(Integer cusId, String orderId, Integer num, CounterTypeEnum type);
 
}