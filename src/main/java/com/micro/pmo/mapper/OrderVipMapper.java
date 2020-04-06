package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.order.entity.OrderVip;

/**
 * OrderVip 
 * @author wenhaofan
 * @createtime 
 */
public interface OrderVipMapper {
 
 	/**
 	* 保存OrderVip
 	* @param  orderVip
	* @return
 	**/
 	public int insertOrderVip(OrderVip orderVip);
 
	
	/**
	* 根据ID更新OrderVip
	* @param orderVip
	* @return
	**/
	public int updateOrderVipById(OrderVip orderVip);
	
	/**
	* 根据ID获取OrderVip
	* @param orderVipId
	* @return
	**/
	public OrderVip getOrderVipById(String orderVipId);
	
	/**
	* 查询获取OrderVip
	* @return
	**/
	public List<OrderVip> orderVipList();
}