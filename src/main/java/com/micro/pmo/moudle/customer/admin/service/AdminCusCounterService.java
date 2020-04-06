package com.micro.pmo.moudle.customer.admin.service;

import com.micro.pmo.moudle.customer.admin.vo.SendVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月9日 
*/
public interface AdminCusCounterService {

 
	/**
	 * 赠送 并单独处理预定收车
	 * @param sendVO
	 */
	void send(SendVO sendVO);
	
  
}