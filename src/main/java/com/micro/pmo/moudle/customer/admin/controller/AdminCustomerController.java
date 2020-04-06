package com.micro.pmo.moudle.customer.admin.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.customer.admin.service.AdminCusService;
import com.micro.pmo.moudle.customer.admin.vo.AdminCusQuery;
import com.micro.pmo.moudle.customer.admin.vo.CusLocationVo;
import com.micro.pmo.moudle.customer.entity.Customer;

@Validated
@RestController
@RequestMapping("api-pc/cus")
public class AdminCustomerController {
	
	@Autowired
	private AdminCusService adminCusService;
	
	
	/**
	 * 查询所有用户 默认查询第一页 10条数据
	 * @author dsn
	 * @return
	 */
	@GetMapping("list")
 	public Result cusList( AdminCusQuery query){
		PageInfo<Customer> selectCustomerAll = adminCusService.cusList(query);
		return Result.success(selectCustomerAll);
	}
	
	/**
	 * 根据id获取用户详情
	 * @param cusId
	 * @return
	 */
	@GetMapping("info")
	public Result info(@NotNull(message ="用户id不能为空") Integer cusId) {
		Map<String,Object> cus = adminCusService.getCusByCusId(cusId);
		return Result.success(cus);
	}
	
	/**
	 * 修改用户
	 * @param request
	 * @return
	 */
	@PutMapping("update-location")
	public Result updateCus(@Valid @RequestBody CusLocationVo cusLocationVo){
		//修改客户所在地址
		adminCusService.updateCus(cusLocationVo);
		
		return Result.success();
	}
	
	/**
	 * 赠送vip
	 * @param cusId
	 * @param dayNum
	 * @return
	 */
	@PutMapping("give-vip")
	public Result giveVip(Integer cusId,Integer dayNum) {
		adminCusService.giveVip(cusId, dayNum);
		return Result.success();
	}
}
