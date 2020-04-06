package com.micro.pmo.moudle.car.service;

import com.micro.pmo.moudle.customer.vo.CounterInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月12日
 */
public interface FlushService {

	/**
	 * 刷新车辆
	 * 
	 * @param carId
	 */
	void flushCar(Integer carId);

	/**
	 * 获取 刷新统计信息
	 * 
	 * @return
	 */
	CounterInfoVO getFlushCounterInfo();
 

}
