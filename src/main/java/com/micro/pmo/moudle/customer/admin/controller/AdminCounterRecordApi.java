package com.micro.pmo.moudle.customer.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.customer.admin.service.AdminCounterRecordService;
import com.micro.pmo.moudle.customer.admin.service.AdminCusCounterService;
import com.micro.pmo.moudle.customer.admin.vo.SendVO;
import com.micro.pmo.moudle.customer.entity.CounterRecord;

/**
 * counterRecord  
 * @author 
 * 
 */
@Validated
@RestController
@RequestMapping("api-pc/customer/send")
public class AdminCounterRecordApi{
 	
 	@Autowired
	AdminCounterRecordService counterRecordService;
 	
 	@Autowired
 	AdminCusCounterService adminCusCounterService;
	
 	/**
 	 *  赠送
 	 * @param sendVO
 	 * @return
 	 */
	@PostMapping("")
	public Result savecounterRecord(@Valid @RequestBody SendVO sendVO) {
		adminCusCounterService.send(sendVO);
		return Result.success();
	}
 
	/**
	 * 分页查询操作记录
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
 	@GetMapping("page")
 	public Result pagecounterRecord(Integer pageNum,Integer pageSize){
 		PageInfo<CounterRecord> info = counterRecordService.counterRecordPage(pageNum, pageSize);
 		return Result.success(info); 
 	}

 
}