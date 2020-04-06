package com.micro.pmo.moudle.store.admin.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.store.admin.service.AdminStoreService;
import com.micro.pmo.moudle.store.admin.vo.AdminStoreQueryVO;
import com.micro.pmo.moudle.store.entity.Store;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月15日
 */
@Validated
@RestController
@RequestMapping("api-pc/store")
public class AdminStoreApi {

	@Autowired
	private AdminStoreService adminStoreService;
	
	/**
	 * 分页查询微店
	 * 
	 * @param query
	 * @return
	 */
	@GetMapping("list")
	public Result storePage(AdminStoreQueryVO query) {
		PageInfo<Store> page = adminStoreService.storePage(query);
		return Result.success(page);
	}

	/**
	 * 根据id获取微店详情
	 * @param storeId
	 * @return
	 */
	@GetMapping("info")
	public Result storeInfo(@NotNull(message = "微店id不能为空") Integer storeId) {
		Store store = adminStoreService.getStoreByStoreId(storeId);
		return Result.success(store);
	}

	/**
	 * 获取微店下的人员列表
	 * @param storeId
	 * @return
	 */
	@GetMapping("cus-list")
	public Result cusListByStoreId(@NotNull(message = "微店id不能为空") Integer storeId) {
		List<Customer> cusList = adminStoreService.cusListByStoreId(storeId);
		return Result.success(cusList);
	}

}
