package com.micro.pmo.moudle.car.sellController;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.car.entity.SellCar;
import com.micro.pmo.moudle.car.service.SellCarService;

@Validated
@RestController
@RequestMapping("api-app/car/sell")
public class SellCarController {

	@Autowired
	private SellCarService sellCarService;
	
	@PostMapping()
	public Result saveSellCar(@Valid @RequestBody SellCar sellCar){
		sellCarService.inserSellCar(sellCar);
		return Result.success();
	}
	
	@PutMapping("update")
	public Result updateSellCar(@Valid @RequestBody SellCar sellCar){
		if(sellCar.getSellId() == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[sellId] 买车id不能为空");
		}
		sellCarService.updateSellCar(sellCar);
		return Result.success();
	}
	
	@GetMapping("info")
	public Result sellCarInfo(@NotNull(message="买车id不能为空") Integer sellCarId){
		SellCar sellCar = sellCarService.sellCarInfo(sellCarId);
		return Result.success(sellCar);
	}
	
	@GetMapping("list")
	public Result sellCarList(@NotNull(message="状态不能为空") String sellCarStatus,Integer pageNumKey,Integer pageSizeKey){
		PageInfo<SellCar> info = sellCarService.sellCarList(sellCarStatus,pageNumKey, pageSizeKey);
		return Result.success(info);
	}
	
	@SuppressWarnings("unused")
	@PutMapping("update-status")
	public Result sellCarUpdateStatus(@RequestBody Kv<Object,Object> data){
		
		Integer sellCarId = data.getInt("sellCarId");
		if(sellCarId ==null) {
			return Result.failure(ResultState.PARAM_ERROR, "sellCarId 参数不能为空");
		}
		
		Integer sellStatus = data.getInt("sellStatus");
		if(sellCarId ==null) {
			return Result.failure(ResultState.PARAM_ERROR, "状态d不能为空");
		}
		
		BigDecimal realPrice=	data.getBigDecimal("realPrice");
 
		if(sellStatus == 1 && realPrice == null){
			return Result.failure(ResultState.PARAM_ERROR, "sellStatus 参数不能为空");
		}
		
		sellCarService.updateSellCarStatus(sellCarId, sellStatus, realPrice);
		return Result.success();
	}
	
}
