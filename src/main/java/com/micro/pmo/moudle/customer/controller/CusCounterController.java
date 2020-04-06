package com.micro.pmo.moudle.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月10日
 */

@Validated
@RestController
@RequestMapping("api-app/cus/send")
public class CusCounterController {

	@Autowired
	private CusCounterService cusCounterService;

	/**
	 *  获取查询成交价的赠送信息
	 * @param sendType
	 * @return
	 */
	@GetMapping("info/query-price")
	public Result queryPriceInfo() {
		CounterInfoVO result = cusCounterService.getCounterInfo(CounterTypeEnum.queryPrice);
		return Result.success(result);
	}


	/**
	 * 使用查询成交价赠送权限
	 * @param sendType
	 * @return
	 */
	@GetMapping("use/query-price")
	public Result usequeryPrice() {
		
		return Result.success();
	}
}
