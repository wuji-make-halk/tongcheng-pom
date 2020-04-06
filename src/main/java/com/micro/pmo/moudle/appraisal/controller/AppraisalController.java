package com.micro.pmo.moudle.appraisal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.appraisal.entity.Appraisal;
import com.micro.pmo.moudle.appraisal.service.AppraisalService;

@Validated
@RestController
@RequestMapping("api-app/appraisal")
public class AppraisalController {

	@Autowired
	private AppraisalService appraisalService;
	
	/***
	 * 保存车估价信息
	 * @param appraisal
	 * @return
	 */
	@PostMapping("")
	public Result insertAppraisal(@Validated @RequestBody Appraisal appraisal){
		appraisalService.intserAppraisal(appraisal);
		return Result.success();
	}
	/**
	 * 车估价列表
	 * @param storeId
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("list")
	public Result appraisalList(Integer pageNumKey,Integer pageSizeKey){
		PageInfo<Appraisal>	info = appraisalService.appraisalPage(pageNumKey, pageSizeKey);
		
		return Result.success(info);
	}
}
