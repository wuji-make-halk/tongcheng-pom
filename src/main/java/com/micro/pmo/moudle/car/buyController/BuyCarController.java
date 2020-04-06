package com.micro.pmo.moudle.car.buyController;

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
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.car.entity.BuyCar;
import com.micro.pmo.moudle.car.service.BuyCarService;
import com.micro.pmo.moudle.car.vo.InputUpdateStatusVO;

@Validated
@RestController
@RequestMapping("api-app/car/buy")
public class BuyCarController {

	@Autowired
	private BuyCarService buyCarService;
	
	@PostMapping("")
	public Result saveBuyCar(@Valid @RequestBody BuyCar buyCar){
		buyCarService.inserBuyCar(buyCar);
		return Result.success();
	}
	
	@PutMapping("update")
	public Result updateBuyCar(@Valid @RequestBody BuyCar buyCar){
		if(buyCar.getBuyCarId() == null){
			return Result.failure(ResultState.PARAM_ERROR,"参数错误：[buyCarId] 收车id不能为空");
		}
		buyCarService.updateBuyCar(buyCar);
		return Result.success();
	}
	
	@GetMapping("info")
	public Result sellCarInfo(@NotNull(message="收车id不能为空") Integer buyCarId){
		BuyCar buyCar = buyCarService.buyCarInfo(buyCarId);
		return Result.success(buyCar);
	}
	
	@GetMapping("list")
	public Result sellCarList(@NotNull(message="状态不能为空") String buyStatus,Integer pageNumKey,Integer pageSizeKey){
		PageInfo<BuyCar> info = buyCarService.buyCarList(buyStatus, pageNumKey, pageSizeKey);
		return Result.success(info);
	}
	
	@PutMapping("update-status")
	public Result sellCarUpdateStatus(@Valid @RequestBody InputUpdateStatusVO updateInfo){
		if(updateInfo.getBuyStatus() == 1 && updateInfo.getClinchPrice() == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[buyStatus] 参数不能为空");
		}
		buyCarService.updateBuyCarStatus(updateInfo);
		return Result.success();
	}

	
}
