package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderVO;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderQuery;
import com.micro.pmo.moudle.car.entity.OrderDeposit;
import com.micro.pmo.moudle.car.vo.UserOrderDepositVO;

/**
 * OrderDeposit
 * 
 * @author wenhaofan
 * @createtime
 */
public interface OrderDepositMapper {

	/**
	 * 保存订金订单附属表
	 * 
	 * @param orderDeposit
	 * @return
	 **/
	public int insertOrderDeposit(OrderDeposit orderDeposit);

	/**
	 * 根据ID更新订金订单状态
	 * 
	 * @param orderDeposit
	 * @return
	 **/
	public int updateDepositStatus(OrderDeposit orderDeposit);

	/**
	 * 根据订单id更新状态
	 * 
	 * @param orderDeposit
	 * @return
	 */
	public int updateStatus(@Param("orderId") String orderId, @Param("status") Integer status);

	/**
	 * 获取订单的订金附属记录
	 * 
	 * @param orderDeposit
	 * @return
	 */
	public OrderDeposit getOrderDeposit(OrderDeposit orderDeposit);

	/**
	 * 分页查询买家订金订单
	 * 
	 * @return
	 */
	List<AdminDepositOrderVO> listBuyerDepositOrder(AdminDepositOrderQuery query);

	/**
	 * 根据订单id获取 订金附属订单
	 * 
	 * @param orderDepositId
	 * @return
	 **/
	public OrderDeposit getOrderDepositByOrderId(@Param("orderId") String orderId);

	/**
	 * 根据买家订单id获取对应的卖家定金记录
	 * 
	 * @param buyerOrderId
	 * @return
	 */
	public AdminDepositOrderVO getSellerDepositByBuyerOrderId(@Param("orderId")String buyerOrderId);
 

	/**
	 * 根据订单获取定金订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	public AdminDepositOrderVO getOrderDepositInfoByOrderId(@Param("orderId")String orderId);

	/**
	 * 获取用户定金列表
	 * 
	 * @param cusId
	 * @param type
	 * @return
	 */
	List<UserOrderDepositVO> listUserOrderDeposit(@Param("cusId") Integer cusId, @Param("type") Integer type);

	/**
	 * 获取订金详情
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 */
	UserOrderDepositVO getUserOrderDeposit(@Param("orderId") String orderId, @Param("type") Integer type);
	
	
	/***
	 * 找寻订单，通过类型 处理
	 * @param orderId
	 * @param type
	 * @return
	 */
	OrderDeposit getOrderDepositOtherByOrderId(@Param("orderId") String orderId, @Param("type") Integer type);

	/**
	 * 根据车辆id取消未支付的订单
	 * 
	 * @param carId
	 */
	void cancleNoPayOrderByCarId(@Param("carId")Integer carId,@Param("status")Integer status);

	/**
	 * 根据车辆id取消未支付的订金订单
	 * 
	 * @param carId
	 */
	void cancleNoPayDepositByCarId(Integer carId);
	
	/***
	 * 修改buyer用户
	 * @param orderDepositId
	 * @param buyerOrderId
	 */
	@Update("UPDATE order_deposit SET buyer_order_id = #{buyerOrderId} WHERE order_deposit_id = #{orderDepositId} ")
	void updateBuyDepositId(@Param("orderDepositId")String orderDepositId,@Param("buyerOrderId")String buyerOrderId);
	
	/**
	 * 获取状态
	 * @param orderId
	 * @param type
	 * @return
	 */
	Integer getOtherStatusByOrderId(@Param("orderId")String orderId,@Param("type")Integer type);
}