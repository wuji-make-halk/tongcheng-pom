package com.micro.pmo.moudle.vip.service;

import java.math.BigDecimal;
import java.util.Map;

import com.micro.pmo.moudle.vip.entity.Vip;

/**
 * vip配置表Service
 */
public interface VipService {
	/**
	 * 获取Vip购买列表
	 * 
	 * @return
	 */
	Map<String, Object> vipList();

	/**
	 * 
	  * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	Vip getVipById(Integer id);

	/**
	 * 获取用户vip剩余月数
	 * @param userId
	 * @return
	 */
	public BigDecimal getSurplusMonth(Integer userId);

}
