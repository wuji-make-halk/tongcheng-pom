package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.customer.entity.OrderCounterExtend;

/**
 * 订单扩展表
 * @author wenhaofan
 * @createtime 
 */
public interface OrderCounterExtendMapper {
 
 	/**
 	* 保存订单扩展记录
 	* @param  orderExtend
	* @return
 	**/
 	public int insertOrderCounterExtend(OrderCounterExtend orderExtend);

	/**
	* 根据ID更新订单扩展记录
	* @param orderExtend
	* @return
	**/
	public int updateOrderCounterExtendById(OrderCounterExtend orderExtend);
	
	/**
	* 根据ID获取订单扩展记录
	* @param orderExtendId
	* @return
	**/
	public OrderCounterExtend getOrderCounterExtendById(String orderExtendId);
	
	/**
	* 查询获取订单扩展记录
	* @return
	**/
	public List<OrderCounterExtend> orderExtendList();
}