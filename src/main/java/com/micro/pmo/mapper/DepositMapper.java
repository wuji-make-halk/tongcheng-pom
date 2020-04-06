package com.micro.pmo.mapper;

import com.micro.pmo.moudle.config.admin.entity.Deposit;

/**
 * 订金配置表Mapper接口
 */
public interface DepositMapper{
	
	/**获取配置订金**/
	public Deposit getDeposit();
	
	/**
	 * 更新订金配置
	 * @param deposit
	 */
    void updateDeposit(Deposit deposit);
}
