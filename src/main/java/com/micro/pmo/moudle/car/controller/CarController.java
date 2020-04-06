package com.micro.pmo.moudle.car.controller;

import java.util.Map;

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
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.service.CarService;
import com.micro.pmo.moudle.car.vo.CarLowerVo;
import com.micro.pmo.moudle.car.vo.FindCarVO;

@Validated
@RestController
@RequestMapping("api-app/car")
public class CarController {
	
	@Autowired
	private CarService carService;

	/**
	 * 保存车辆信息
	 * @param car
	 * @return
	 */
	@PostMapping()
	public Result saveCar(@Valid @RequestBody Car car){
		carService.saveCar(car);
		return Result.success(car);
	}
	/***
	 * 修改车辆信息
	 * @param car
	 * @return
	 */
	@PutMapping("update")
	public Result updateCar(@Valid @RequestBody Car car){
		//必有参数校验
		if(car.getCarId() == null){
			return Result.failure(ResultState.PARAM_ERROR,"参数错误：[carId] 车辆id不能为空");
		}
		carService.updateCar(car);
		return Result.success();
	}

	/***
	 * 车辆管理分页查询
	 * @param carStatus 状态
	 * @param keyword 关键字
	 * @return
	 */
	@GetMapping("list")
	public Result carList(@NotNull(message = "状态不能为空") Integer carStatus,String keyword,
			Integer pageNumKey,Integer pageSizeKey){
		PageInfo<FindCarVO> info = carService.carList(carStatus, keyword, pageNumKey, pageSizeKey);
		return Result.success(info);
	}
	
	
	/**
	 * 修改车辆信息使用或查看详情
	 * @param carId
	 * @return
	 */
	@GetMapping("info")
	public Result carInfo(@NotNull(message = "车辆id不能为空") Integer carId){
		Car car = carService.carInfo(carId);
		return Result.success(car);
	}
	
	/***
	 * 用于平台客户正常查看
	 * @param carId
	 * @return
	 */
	@GetMapping("show-info")
	public Result carShowInfo(@NotNull(message = "车辆id不能为空") Integer carId){
		Map<String,Object> carInfo = carService.showCarInfo(carId);
		return Result.success(carInfo);
	}
	
	/***
	 * 个人车源list,分享也会使用
	 * @param creator
	 * @return
	 */
	@GetMapping("cus-car-list")
	public Result cusCarList(@NotNull(message = "用户id不能为空") Integer creator,Integer pageNumKey,Integer pageSizeKey){
		PageInfo<FindCarVO> vos = carService.cusCarList(creator,pageNumKey,pageSizeKey);
		return Result.success(vos);
	}
	
	/***
	 * 车辆下架
	 * @param lowerVo
	 * @return
	 */
	@PostMapping("lower")
	public Result carLower(@Valid @RequestBody CarLowerVo lowerVo){
		carService.saveCarDeal(lowerVo);
		return Result.success();
	}
	/***
	 * 车辆签署电子界面
	 * @param carId
	 * @return
	 */
	@GetMapping("deposit-info")
	public Result depositInfo(@NotNull(message = "车辆id不能为空") Integer carId){
		Map<String,Object> map = carService.depositCarInfo(carId);
		return Result.success(map);
	}
 
	
	/**
	 * 获取猜你喜欢的车辆
	 * @return
	 */
	@GetMapping("favorite-list")
	public Result favoriteCarList() {
		return Result.success(carService.favoriteCarList());
	}

 
	/**
	 * 获取当前用户创建的车辆统计 根据状态
	 * @return
	 */
	@GetMapping("car-count-status")
	public Result carCountByStatus() {
		return Result.success(carService.carCountByStatus());
	}

}
 