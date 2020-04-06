package com.micro.pmo.moudle.account.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.account.entity.CusAccountLog;

/** 
 * 用户账号业务
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月17日 
*/
public interface CusAccountLogService {

	/**
	 * 保存用户账号流水
	 * @param cusAccountLog
	 */
	void saveCusAccountLog(CusAccountLog cusAccountLog);

	/**
	 * 根据id获取用户账号流水
	 * @param id
	 * @return
	 */
	CusAccountLog getCusAccountLogById(Integer id);

	/**
	 * 分页查询用户账号流水
	 * @param query
	 * @return
	 */
	PageInfo<CusAccountLog> cusAccountLogPage(BaseQuery query);
	
	/**
	 * 更新类型 以及剩余金额
	 * @param cusAccountLog
	 */
	void updateAccountByOrderId(CusAccountLog log);
}