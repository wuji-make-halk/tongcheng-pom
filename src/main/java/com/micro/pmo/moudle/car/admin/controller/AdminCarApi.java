package com.micro.pmo.moudle.car.admin.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.car.admin.service.AdminCarService;
import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarDeal;
import com.micro.pmo.moudle.car.vo.InputCarDealVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月12日 
*/
@Validated
@RestController
@RequestMapping("api-pc/car")
public class AdminCarApi {

	@Autowired
	private  AdminCarService adminCarService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@GetMapping("page")
	public Result carPage(AdminCarQueryVO query) {
		PageInfo<Car>  page = adminCarService.carList(query);
		return Result.success(page);
	}
	
	/**
	 *  假删除
	 * @return
	 */
	@DeleteMapping("delete")
	public Result delete(@NotNull(message="车辆id不能为空")Integer carId) {
		adminCarService.deleteCarByCarId(carId);
		return Result.success();
	}
	
	/**
	 * 根据id获取车辆详情
	 * @param carId
	 * @return
	 */
	@GetMapping("info")
	public Result info(@NotNull(message="车辆id不能为空")Integer carId) {
		Kv<String,Object> result = adminCarService.getCarInfoById(carId);
		return Result.success(result);
	}
	
	/**
	 * 根据车辆id获取成交价详情
	 * @param carId
	 * @return
	 */
	@GetMapping("deal-list")
	public Result getCarDealByCarId(@NotNull(message="车辆id不能为空")Integer carId) {
		List<CarDeal> carDealList = adminCarService.listCarDealByCarId(carId);
		return Result.success(carDealList);
	}
	
	/**
	 * 保存成交价信息
	 * @param carDeal
	 * @return
	 */
	@PostMapping("save-deal")
	public Result saveCarDeal(@Valid @RequestBody InputCarDealVO carDeal) {
		adminCarService.saveCarDeal(carDeal);
		return Result.success();
	}
	
}
