package com.micro.pmo.moudle.order.admin.service;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderQueryVO;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderVO;

public interface AdminOrderService {

	/**
	 * 订单分页
	 * @param query
	 * @return
	 */
	PageInfo<AdminOrderVO> orderPage(AdminOrderQueryVO query);
	
}
