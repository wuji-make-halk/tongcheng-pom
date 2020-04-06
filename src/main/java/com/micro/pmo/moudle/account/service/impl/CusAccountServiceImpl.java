package com.micro.pmo.moudle.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CusAccountMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.account.accountenum.AccountLogEnum;
import com.micro.pmo.moudle.account.entity.CusAccount;
import com.micro.pmo.moudle.account.entity.CusAccountLog;
import com.micro.pmo.moudle.account.service.CusAccountLogService;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.account.vo.AccountPayRefundVO;
import com.micro.pmo.moudle.account.vo.InputAccountTopupVO;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayModeEnum;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * CusAccountService
 * 
 * @author wenhaofan
 * @createtime
 */
@Service
public class CusAccountServiceImpl implements CusAccountService {

	@Autowired
	private CusAccountMapper cusAccountMapper;

	@Autowired
	private CusAccountLogService cusAccountLogService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerMapper cusMapper;
 
	@Autowired
	private CusAccountService cusAccountService;
	
	@Override
	@Transactional
	public void refundAccount(AccountPayRefundVO input) {
		boolean isRefund = input.getMoney() == null || input.getMoney().compareTo(BigDecimal.ZERO) < 1;
		//如果为0则不进行操作
		if(isRefund) {
			return;
		}
		
		CusAccount account = cusAccountMapper.getCusAccountById(input.getCusId());
		//更新账号金额
		updateAccount(account, input.getMoney(), AccountLogEnum.REFUND);
		// 创建退款操作记录
		CusAccountLog accountLog = new CusAccountLog(input.getCusId(),AccountLogEnum.REFUND.getFlowType(), 
				input.getMoney(), account.getSurplusMoney(), input.getOrderId(), input.getRemark(),1,
				input.getProductName(),input.getPayType());
		cusAccountLogService.saveCusAccountLog(accountLog);
	}

	@Override
	@Transactional
	public void payAccount(AccountPayRefundVO input) {
		CusAccount account = cusAccountMapper.getCusAccountById(input.getCusId());
		// 更新账号金额
		updateAccount(account, input.getMoney(),AccountLogEnum.PAY);
		// 创建支付操作记录
		CusAccountLog accountLog = new CusAccountLog(input.getCusId(),AccountLogEnum.PAY.getFlowType(), 
				input.getMoney(), account.getSurplusMoney(), input.getOrderId(), input.getRemark(),2,
				input.getProductName(),input.getPayType());
		cusAccountLogService.saveCusAccountLog(accountLog);
	}
	
	@Override
	@Transactional
	public PayInfoVO topupAccount(InputAccountTopupVO input) {
		Customer cus = CusUtils.getCustomer();
		 
		Order order = new Order(input.getMoney(), OrderPayTypeEnum.PAY_ACCOUNT.getType(), cus.getCusId(),
				OrderStatusEnum.UNPAID.getStatus(),input.getIp(),input.getPayMode().getMode(),cus.getOpenId(),"账号余额充值","余额充值");
		// 创建保存订单
		PayInfoVO orderResult = orderService.createOrder(order);

	
		
		return orderResult;
	}
	
 
	/**
	 * 更新用户账号信息
	 * 
	 * @param cusId
	 * @param money
	 * @param remark
	 * @param logType
	 * @param logSource
	 * @param orderId
	 */
	@Transactional
	public void updateAccount(CusAccount account, BigDecimal money, AccountLogEnum logType) {
		if (money == null ) {
			throw new NullPointerException();
		}
		//处理账号剩余金额
		accountMoneyHandle(money, logType, account);

		// 更新账户
		updateCusAccountById(account);
	}

	private void accountMoneyHandle(BigDecimal money, AccountLogEnum logType, CusAccount account) {
		BigDecimal totalMoney = account.getTotalMoney();
		BigDecimal surplusMoney = account.getSurplusMoney();

		// 根据操作类型计算金额
		if (logType.getOperateType() == 1) {
			surplusMoney = surplusMoney.subtract(money);
		} else if (logType.getOperateType() == 0) {
			surplusMoney = surplusMoney.add(money);
			totalMoney = totalMoney.add(money);
		}

		account.setTotalMoney(totalMoney);
		account.setSurplusMoney(surplusMoney);
	}

	@Override
	public void createCusAccount(Integer cusId) {
		CusAccount cusAccount = new CusAccount();
		cusAccount.setGmtCreate(new Date());
		cusAccount.setAccountId(cusId);
		cusAccount.setFreezeMoney(BigDecimal.ZERO);
		cusAccount.setTotalMoney(BigDecimal.ZERO);
		cusAccount.setSurplusMoney(BigDecimal.ZERO);
		cusAccountMapper.insertCusAccount(cusAccount);
	}

	@Override
	public void updateCusAccountById(CusAccount cusAccount) {
		cusAccount.setGmtLastdeal(new Date());
		cusAccountMapper.updateCusAccountById(cusAccount);
		//获取用户token
		Customer cusOld = cusMapper.getCusAllById(cusAccount.getAccountId());
		if(cusOld == null || StringUtils.isBlank(cusOld.getCusToken())){
			return ;
		}
		//删除缓存
		CusUtils.removeCusCache(cusOld.getCusToken());
		//更新缓存
		CusUtils.putCusCache(cusOld.getCusToken());
		
	}

	@Override
	public CusAccount getCusAccountById(Integer id) {
		return cusAccountMapper.getCusAccountById(id);
	}

	@Override
	public PageInfo<CusAccount> cusAccountPage(BaseQuery query) {
		// 分页处理
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		List<CusAccount> list = cusAccountMapper.cusAccountList();
		PageInfo<CusAccount> info = new PageInfo<>(list);
		return info;
	}
 
	/**
	 * 获取支付类型
	 * 
	 * @param logSource
	 * @param payMode
	 * @return
	 */
	private Integer getLogSource(Integer payMode) {
		Integer logSource = null;
		// 判断是否是微信支付
		if (OrderPayModeEnum.WX_PAY_APP.getMode().equals(payMode)
				|| OrderPayModeEnum.WX_PAY_JSAPI.getMode().equals(payMode)) {
			logSource = 0;
			// 判断是否是账号支付
		} else if (OrderPayModeEnum.ACCOUNT_PAY.getMode().equals(payMode)) {
			logSource = 2;
		}
		return logSource;
	}
	
	public void paySuccess(String orderId) {
		Order order = orderService.getOrderByOrderId(orderId);
		CusAccount account  = cusAccountMapper.getCusAccountById(order.getCusId());
		//更新余额
		cusAccountService.updateAccount(account, order.getOrderMoney(), AccountLogEnum.DEPOSIT);
	 
		Integer payMode = order.getPaymentMode();
		// 获取支付类型
		Integer logSource = getLogSource(payMode);
		//更新记录类型为已充值
		// 创建充值操作记录
		CusAccountLog accountLog = new CusAccountLog(account.getAccountId(), logSource, order.getOrderMoney(), 
				account.getSurplusMoney(), order.getOrderId(), 
				order.getOrderRemark(),AccountLogEnum.DEPOSITING.getFlowType(),
				"账号余额充值","余额充值");
		cusAccountLogService.saveCusAccountLog(accountLog);
	}

}