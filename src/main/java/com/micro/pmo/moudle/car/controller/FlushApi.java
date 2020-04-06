package com.micro.pmo.moudle.car.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.car.service.FlushService;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.extension.service.PromotionService;
import com.micro.pmo.moudle.extension.vo.InputExtensionBuyVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月12日
 */
@Validated
@RestController
@RequestMapping("api-app/car/flush")
public class FlushApi {

	@Autowired
	private FlushService flushService;
 
	@Autowired
	private PromotionService promotionService;
	
	
	/***
	 * 刷新车辆顺序
	 * @param map
	 * @return
	 */
	@PutMapping("time")
	public Result flushCarTime(@RequestBody Map<String,Integer> map){
		Integer carId = map.get("carId");
		if(carId == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[carId]车辆id不能为空");
		}
		flushService.flushCar(carId);
		return Result.success();
	}
	
	/**
	 * 获取刷新的赠送信息
	 * 
	 * @return
	 */
	@GetMapping("info")
	public Result flushInfo() {
		CounterInfoVO result = flushService.getFlushCounterInfo();
		return Result.success(result);
	}

	/**
	 * 使用刷新赠送权限
	 * 
	 * @return
	 */
	@PutMapping("used")
	public Result useflush(@RequestBody Map<String,Integer> map) {
		Integer carId = map.get("carId");
		if(carId == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[carId]车辆id不能为空");
		}
		promotionService.useSendFlush(carId);
	 
		return Result.success();
	}
	
	/**
	 * 购买刷新
	 * @param request
	 * @param data
	 * @return
	 */
	@PostMapping("buy")
	public Result buy(HttpServletRequest request, @RequestBody @Valid  InputExtensionBuyVO input ) {
		String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
		return Result.success(promotionService.buyFlush(input));
	}

}
