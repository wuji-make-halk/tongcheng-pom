package com.micro.pmo.moudle.car.admin.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.car.admin.service.AdminDepositService;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderQuery;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositRefundVO;
import com.micro.pmo.moudle.config.admin.entity.Deposit;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/
@Validated
@RestController
@RequestMapping("api-pc/deposit")
public class AdminDepositApi {

	@Autowired
	private AdminDepositService adminDepositService;
	
	/**
	 * 分页查询订金订单
	 * @param query
	 * @return
	 */
	@GetMapping("page")
	public Result pageDepositOrder(AdminDepositOrderQuery query) {
		return Result.success(adminDepositService.pageDepositOrder(query));
	}
	
	/**
	 * 订金详情
	 * @param orderId
	 * @return
	 */
	@GetMapping("info")
	public Result depositInfo(@NotNull(message = "订单id不能为空")String orderId) {
		return Result.success(adminDepositService.getOrderDepositInfo(orderId));
	}
	
	/**
	 * 订金退款
	 * @return
	 */
	@PutMapping("refund")
	public Result refund(@Valid @RequestBody AdminDepositRefundVO input) {
		adminDepositService.depositRefund(input);
		return Result.success();
	}
	
	/**
	 * 修改订金配置
	 * @param deposit
	 * @return
	 */
	@PutMapping("config/update")
	public Result updateConfig(@Valid @RequestBody Deposit deposit) {
		adminDepositService.updateDepositConfig(deposit);
		return Result.success();
	}
	
	/**
	 * 获取订金配置详情
	 * @return
	 */
	@GetMapping("config/info")
	public Result configInfo() {
		Deposit config = adminDepositService.getDepositConfig();
		return Result.success(config);
	}
}
