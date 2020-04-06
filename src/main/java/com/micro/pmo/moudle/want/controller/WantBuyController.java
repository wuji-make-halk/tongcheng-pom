package com.micro.pmo.moudle.want.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.want.entity.WantBuy;
import com.micro.pmo.moudle.want.service.WantBuyService;

@Validated
@RestController
@RequestMapping("api-app/want")
public class WantBuyController {

	@Autowired
	private WantBuyService wantBuyService;
	
	/***
	 * 写入数据
	 * @param wantBuy
	 * @return
	 */
	@PostMapping()
	public Result insterWantBuy(@Validated @RequestBody WantBuy wantBuy){
		wantBuyService.intserWantBuy(wantBuy);
		return Result.success();
	}
	/***
	 * 查询列表
	 * @param wantBuy
	 * @return
	 */
	@GetMapping("list")
	public Result wantList(Integer pageNumKey,Integer pageSizeKey){
		PageInfo<WantBuy> wantBuys = wantBuyService.wantBuyPage(pageNumKey, pageSizeKey);
		return Result.success(wantBuys);
	}
	
}
