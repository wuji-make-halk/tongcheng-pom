package com.micro.pmo.moudle.account.service;

import java.math.BigDecimal;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.account.accountenum.AccountLogEnum;
import com.micro.pmo.moudle.account.entity.CusAccount;
import com.micro.pmo.moudle.account.vo.AccountPayRefundVO;
import com.micro.pmo.moudle.account.vo.InputAccountTopupVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/** 
 * 用户账号业务
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月17日 
*/
public interface CusAccountService {

 
	/**
	 * 账号退款
	 * @param cusId 用户id
	 * @param money 充值金额
	 * @param remark 描述
	 * @param orderId 订单id
	 */
	public void refundAccount(AccountPayRefundVO accountPayVO);
	
	/**
	 * 账号使用支付
	 * @param cusId 用户id
	 * @param money 使用金额
	 * @param remark 描述
	 * @param orderId 订单id
	 */
	public void payAccount(AccountPayRefundVO accountPayVO);
 
	/**
	 * 保存用户账号
	 * @param cusAccount
	 */
	void createCusAccount(Integer cusId);

	/**
	 * 更新用户账号
	 * @param cusAccount
	 */
	void updateCusAccountById(CusAccount cusAccount);

	/**
	 * 根据主键获取用户账号
	 * @param id
	 * @return
	 */
	CusAccount getCusAccountById(Integer id);

	/**
	 * 分页查询用户账号
	 * @param query
	 * @return
	 */
	PageInfo<CusAccount> cusAccountPage(BaseQuery query);
	
	/**
	 * 账号充值
	 * @param money
	 * @return
	 */
	PayInfoVO topupAccount(InputAccountTopupVO input);

	/**
	 * 支付充值成功回调
	 * @param refundAmount
	 * @param orderId
	 */
	void updateAccount(CusAccount account, BigDecimal money, AccountLogEnum logType);
 
	/**
	 * 支付充值成功回调
	 * @param orderId
	 */
	void paySuccess(String orderId);
}