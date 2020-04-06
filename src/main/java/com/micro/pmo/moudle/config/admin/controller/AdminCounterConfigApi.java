package com.micro.pmo.moudle.config.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.config.admin.service.AdminCounterConfigService;

/**
 * 
 * @author
 * 
 */
@Validated
@RestController
@RequestMapping("api-pc/config/send")
public class AdminCounterConfigApi {

	@Autowired
	AdminCounterConfigService counterConfigService;

	/**
	 * 保存一条赠送配置
	 * 
	 * @param counterConfig
	 * @return
	 */
	@PostMapping("save")
	public Result saveCounterConfig(@Valid @RequestBody CounterConfig counterConfig) {
		counterConfigService.saveCounterConfig(counterConfig);
		return Result.success();
	}

	/**
	 * 修改赠送配置
	 * 
	 * @param counterConfig
	 * @return
	 */
	@PutMapping("update")
	public Result updateCounterConfig(@Valid @RequestBody CounterConfig counterConfig) {
		counterConfigService.updateCounterConfigById(counterConfig);
		return Result.success();
	}
 
	/**
	 * 查询配置列表
	 * 
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("list")
	public Result listConfig(BaseQuery query) {
		return Result.success(counterConfigService.listConfig(query));
	}

}