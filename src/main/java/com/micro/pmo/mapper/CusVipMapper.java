package com.micro.pmo.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.customer.entity.CusVip;


/**
 * cus vip表Mapper接口
 */
public interface CusVipMapper {
	/**
	 *  根据ID修改 假删除
	 * @param cusVip
	 * @return
	 */
	public int deleteCusVipById(@Param("cusVipId")Integer cusVipId);
	
	/**
	 * 获取用户的vip到期时间
	 * @param cusId
	 * @return
	 */
	public Date getEndDate(@Param("cusId") Integer cusId);
	
	/**
	 * 保存CusVip
	 * @param cusVip
	 * @return
	 */
 	public int insertCusVip(CusVip cusVip);
 
 	/**
 	 * 获取用户最后一条vip开通记录
 	 * @param cusId
 	 * @return
 	 */
 	public CusVip getValidCusVip(@Param("cusId")Integer cusId);
 	
 	
 	/**
 	 * 更新vip到期时间
 	 * @param cusVip
 	 * @return
 	 */
 	public int updateVipExpirationDateById(CusVip cusVip);
}