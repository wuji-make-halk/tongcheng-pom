package com.micro.pmo.moudle.vip.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.vip.service.CusVipService;
import com.micro.pmo.moudle.vip.service.VipService;
import com.micro.pmo.moudle.vip.vo.InputBuyVipVO;

/**
 * SysExtension
 * 
 */
@Validated
@RestController
@RequestMapping("api-app/vip")
public class VipApi {

	@Autowired
	VipService vipService;

	@Autowired
	CusVipService cusVipService;

	@GetMapping("list")
	public Result vipList() {
		Map<String, Object> result = vipService.vipList();
		return Result.success(result);
	}

	/**
	 * 购买vip
	 * @param request
	 * @param data
	 * @return
	 */
	@PostMapping("buy")
	public Result buyVip(HttpServletRequest request, @RequestBody @Valid  InputBuyVipVO input ) {
		String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
		return Result.success(cusVipService.buyVip(input));
	}

	/***
	 * vip详情处理
	 * @return
	 */
	@GetMapping("info")
	public Result vipInfo(){
	 	Map<String,Object> map = cusVipService.vipInfo();
	 	return Result.success(map);
	}
	
}