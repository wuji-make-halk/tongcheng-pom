package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.order.admin.vo.AdminOrderQueryVO;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderVO;
import com.micro.pmo.moudle.order.entity.Order;

/**
 * 订单Mapper接口
 */
public interface OrderMapper{
	
	/**
	 * 更新订单支付方式，预支付订单号
	 * @param order
	 * @return
	 */
	public int updateOrder(Order order);
 
	
	/**
	 * 根据订单ID查询订单
	 * @author dsn
	 * @param order
	 * @return
	 */
	public Order getOrderById(@Param("orderId")String orderId) ;
	
	
	/**
	 * 改变订单状态
	 * @author dsn
	 * @param order
	 * @return
	 */
	public int updateOrderStatusById(@Param("status")Integer status,@Param("orderId")String orderId) ;
	
	/**
	 * 插入订单信息
	 * @author dsn
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order);
	
	/**
	 * 根据用户id去获取openId
	 * @param cusId
	 * @return
	 */
	public String getOpenIdById(Integer cusId);
	
	/**
	 * 
	 * 分页查询订单 不包含定金
	 * 
	 * @param admin
	 * @return
	 */
	public List<AdminOrderVO> cusOrderListNotDeposit(AdminOrderQueryVO admin);
}
