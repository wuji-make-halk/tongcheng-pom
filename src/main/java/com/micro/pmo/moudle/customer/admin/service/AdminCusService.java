package com.micro.pmo.moudle.customer.admin.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.customer.admin.vo.AdminCusQuery;
import com.micro.pmo.moudle.customer.admin.vo.CusLocationVo;
import com.micro.pmo.moudle.customer.entity.Customer;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
public interface AdminCusService {

	/**
	 * 查询用户列表
	 * @param query
	 * @return
	 */
	public PageInfo<Customer> cusList(AdminCusQuery query);
	
	/**
	 * 修改用户
	 * @param customer
	 */
	public void updateCus(CusLocationVo cusLocationVo);
	
	/**
	 * 根据id获取用户
	 * @param cusId
	 * @return
	 */
	public Map<String,Object> getCusByCusId(Integer cusId);
	
	/**
	  *  赠送vip
	 * @param cusId
	 * @param dayNum
	 */
	void giveVip(Integer cusId,Integer dayNum);
}
