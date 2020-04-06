package com.micro.pmo.moudle.account.controller;

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
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.account.service.CusAccountLogService;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.account.vo.InputAccountTopupVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月2日 
*/
@Validated
@RestController
@RequestMapping("api-app/account")
public class AccountApi {

	@Autowired
	private CusAccountService cusAccountService;
	
	@Autowired
	private CusAccountLogService cusAccountLogService;
	
	/**
	 * 账号充值
	 * @return
	 */
	@PostMapping("topup")
	public Result topup(HttpServletRequest request,@Valid @RequestBody InputAccountTopupVO input) {
		input.setIp(CommonUtils.getIpAddr(request));
		return Result.success(cusAccountService.topupAccount(input));
	}
	
	/**
	 * 账号流水列表
	 * @param query
	 * @return
	 */
	@GetMapping("log-list")
	public Result logList(BaseQuery query) {
		return Result.success(cusAccountLogService.cusAccountLogPage(query));
	}
	
}
