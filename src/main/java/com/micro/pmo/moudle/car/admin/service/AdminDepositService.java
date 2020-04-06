package com.micro.pmo.moudle.car.admin.service;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderVO;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositRefundVO;
import com.micro.pmo.moudle.config.admin.entity.Deposit;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderQuery;

public interface AdminDepositService {

	/**
	 * 分页查询定金
	 * 
	 * @param query
	 * @return
	 */
	PageInfo<AdminDepositOrderVO> pageDepositOrder(AdminDepositOrderQuery query);

	/**
	 * 根据订单获取 买家 卖家 车辆详情
	 * 
	 * @param orderId
	 * @return
	 */
	Kv<String, Object> getOrderDepositInfo(String orderId);

	/**
	 * 对异常订单进行退款操作
	 * 
	 * @param depositRefund
	 */
	void depositRefund(AdminDepositRefundVO depositRefund);

	/**
	 * 更新订金配置
	 * 
	 * @param deposit
	 */
	void updateDepositConfig(Deposit deposit);

	/**
	 * 获取订金配置
	 * 
	 * @return
	 */
	Deposit getDepositConfig();
}
