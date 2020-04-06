package com.micro.pmo.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.account.entity.CusAccount;

/**
 * 用户账号记录表
 * @author wenhaofan
 * @createtime 
 */
public interface CusAccountMapper {
 
 	/**
 	* 保存用户账号
 	* @param  用户账号
	* @return
 	**/
 	public int insertCusAccount(CusAccount cusAccount);

	
	/**
	* 根据ID更新用户账号
	* @param 用户账号
	* @return
	**/
	public int updateCusAccountById(CusAccount cusAccount);
	
	/**
	* 根据ID获取用户账号
	* @param 用户账号
	* @return
	**/
	public CusAccount getCusAccountById(int cusAccountId);
	
	/**
	* 查询获取用户账号
	* @return
	**/
	public List<CusAccount> cusAccountList();
	
	/**
	 * 获取账号余额
	 * @param cusId
	 * @return
	 */
	public BigDecimal getSurplusMoney(Integer cusId);
	
	/***
	 * 更新金额
	 * @return
	 */
	@Update("UPDATE cus_account SET surplus_money = #{surplusMoney},gmt_lastdeal = #{gmtLastdeal} WHERE account_id = #{surplusMoney}")
	public int updateSurplusMoneyById(@Param("cusAccountId")Integer cusAccountId,@Param("surplusMoney")BigDecimal surplusMoney,@Param("gmtLastdeal")Date gmtLastdeal);
}