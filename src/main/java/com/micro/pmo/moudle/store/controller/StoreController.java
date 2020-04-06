package com.micro.pmo.moudle.store.controller;

import java.util.List;
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
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.service.StoreService;
import com.micro.pmo.moudle.store.vo.StaffVo;

@Validated
@RestController
@RequestMapping("api-app/store")
public class StoreController {

	@Autowired
	private StoreService storeService;
	
	/***
	 * 保存微店
	 * @param store
	 * @return
	 */
	@PostMapping("")
	public Result saveStore(@Valid @RequestBody Store store){
		store = storeService.saveStore(store);
		return Result.success(store);
	}
	/***
	 * 修改微店
	 * @param store
	 * @return
	 */
	@PutMapping("update")
	public Result updateStore(@Valid @RequestBody Store store){
		if(store.getStoreId() == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：参数[storeId] 修改时微店id不能为空");
		}
		store = storeService.updateStore(store);
		return Result.success(store);
	}
	/***
	 * 微店详情
	 * @param storeId
	 * @return
	 */
	@GetMapping("info")
	public Result storeInfo(@NotNull(message="微店id参数不能为空") Integer storeId){
		Store store = storeService.storeInfo(storeId);
		return Result.success(store);
	}
	/***
	 * 添加员工
	 * @param staffVo
	 * @return
	 */
	@PostMapping("add-staff")
	public Result storeAddCus(@Valid @RequestBody StaffVo staffVo){
		storeService.storeAddCus(staffVo);
		return Result.success();
	}
	/***
	 * 员工列表
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("staff-list")
	public Result staffList(Integer pageNumKey,Integer pageSizeKey,String keyword){
		PageInfo<CusStore> info = storeService.staffList(pageNumKey, pageSizeKey,keyword);
		return Result.success(info);
	}
	/***
	 * 微店在售车辆列表
	 * @param keyword
	 * @return
	 */
	@GetMapping("car-list")
	public Result carList(@NotNull(message="微店id参数不能为空")Integer storeId,String keyword){
		List<FindCarVO> cars = storeService.storeCarList(storeId, keyword);
		return Result.success(cars);
	}
	/***
	 * 微店车辆
	 * @param storeId
	 * @param carId
	 * @return
	 */
	@GetMapping("car-info")
	public Result carInfo(@NotNull(message="微店id不能为空")Integer storeId,
			@NotNull(message="车辆id参数不能为空")Integer carId){
		Map<String, Object> map = storeService.storeCarInfo(storeId, carId);
		return Result.success(map);
	}
	
	/***
	 * 微店车辆
	 * @param storeId
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("car-page")
	public Result carPage(@NotNull(message="微店id参数不能为空")Integer storeId,Integer pageNumKey,Integer pageSizeKey){
		PageInfo<FindCarVO> info = storeService.carPage(storeId, pageNumKey, pageSizeKey);
		return Result.success(info);
	}
	
}
