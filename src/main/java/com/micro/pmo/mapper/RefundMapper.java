package com.micro.pmo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.car.entity.Refund;

/**
 * Refund 
 * @author wenhaofan
 * @createtime 
 */
public interface RefundMapper {
 
 	/**
 	* 保存Refund
 	* @param  refund
	* @return
 	**/
 	public int insertRefund(Refund refund);


	/**
	* 根据ID获取Refund
	* @param refundId
	* @return
	**/
	public Refund getRefundById(int refundId);
	
	/**
	 * 修改退款状态
	 * @param refundId
	 * @param refundStatus
	 * @return
	 */
	@Update("UPDATE refund SET refund_status = #{refundStatus} WHERE refund_id = #{refundId}")
	public int updateRefundStatusById(@Param("refundId")String refundId,@Param("refundStatus")Integer refundStatus);
 
}