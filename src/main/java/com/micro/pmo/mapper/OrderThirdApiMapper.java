package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.third.entity.OrderThird;

/**
 * OrderThirdApi 
 * @author wenhaofan
 * @createtime 
 */
public interface OrderThirdApiMapper {
 
 	/**
 	* 保存OrderThirdApi
 	* @param  orderThirdApi
	* @return
 	**/
 	public int insertOrderThirdApi(OrderThird orderThirdApi);
 	
    /**
       * 根据ID删除OrderThirdApi，假删除
    * @param orderThirdApiId
	* @return
    **/
	public int deleteOrderThirdApiById(Integer orderThirdApiId);
	
	/**
	* 根据ID更新OrderThirdApi
	* @param orderThirdApi
	* @return
	**/
	public int updateOrderThirdApiById(OrderThird orderThirdApi);
	
	/**
	* 根据ID获取OrderThirdApi
	* @param orderThirdApiId
	* @return
	**/
	public OrderThird getOrderThirdApiById(String orderThirdApiId);
	
	/**
	* 查询获取OrderThirdApi
	* @return
	**/
	public List<OrderThird> orderThirdApiList();
}