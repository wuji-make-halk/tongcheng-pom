package com.micro.pmo.moudle.order.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.order.admin.service.AdminOrderService;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderQueryVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/
@Validated
@RestController
@RequestMapping("api-pc/order")
public class AdminOrderApi {

	@Autowired
	private AdminOrderService orderService;
	
	/**
	 * 订单分页查询
	 * @param query
	 * @return
	 */
	@GetMapping("page")
	public Result list(AdminOrderQueryVO query) {
		return Result.success(orderService.orderPage(query));
	}
	
}
