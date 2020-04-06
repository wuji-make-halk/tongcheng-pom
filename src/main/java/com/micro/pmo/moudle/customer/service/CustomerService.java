package com.micro.pmo.moudle.customer.service;


import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.vo.InputCusUpdateVO;
import com.micro.pmo.moudle.customer.vo.LoginPhone;


public interface CustomerService{
	
	/**检查电话号码**/
	public Boolean checkCus(String phone); 
	
	/***用户登录***/
	public Customer cusLogin(LoginPhone loginPhone);
 
	/***
	 * 获取用户信息
	 * @return
	 */
	public Customer cusInfo();
	
	/**
	 * 修改当前登录用户信息
	 * @param cusId
	 * @param cusStatus
	 * @return
	 */
	public int updateCurrentCus(InputCusUpdateVO inputCusUpdateVO);
	

	public void cusLoginOut();
}
