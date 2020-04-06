package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.account.entity.CusAccountLog;

/**
 * 用户账号流水 
 * @author wenhaofan
 * @createtime 
 */
public interface CusAccountLogMapper {
 
 	/**
 	* 保存用户账号流水 
 	* @param  cusAccountLog
	* @return
 	**/
 	public int insertCusAccountLog(CusAccountLog cusAccountLog);

	/**
	* 根据ID获取CusAccountLog
	* @param cusAccountLogId
	* @return
	**/
	public CusAccountLog getCusAccountLogById(int cusAccountLogId);
	
	/**
	* 查询获取CusAccountLog
	* @return
	**/
	public List<CusAccountLog> cusAccountLogList(Integer cusId);
	
	/**
	 * 根据订单id获取账号记录
	 * @param orderId
	 * @return
	 */
	public CusAccountLog getCusAccountLogByOrderId(String orderId);
	
	/**
	 * 更新账号记录类型
	 * @param cusAccountLog
	 */
	public void updateAccountByOrderId(CusAccountLog log);
}