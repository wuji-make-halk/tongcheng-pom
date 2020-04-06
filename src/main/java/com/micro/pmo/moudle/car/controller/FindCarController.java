package com.micro.pmo.moudle.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.car.service.CarService;
import com.micro.pmo.moudle.car.vo.FindCarQueryVO;
import com.micro.pmo.moudle.car.vo.FindCarVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月8日
 */
@RestController
@RequestMapping("api-app/car")
public class FindCarController {

	@Autowired
	private CarService carService;

	/**
	 * 查找车辆信息
	 * 
	 * @param query
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@PostMapping("find")
	public Result findCar(@RequestBody FindCarQueryVO query) {
		PageInfo<FindCarVO> page = carService.findCar(query);
		return Result.success(page);
	}

}
