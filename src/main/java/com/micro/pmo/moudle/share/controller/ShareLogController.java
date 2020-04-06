package com.micro.pmo.moudle.share.controller;

import java.util.List;
import java.util.Map;

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
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.share.entity.ShareLog;
import com.micro.pmo.moudle.share.service.ShareLogService;
import com.micro.pmo.moudle.share.vo.ShareLogVo;
/***
 * 分享记录
 * @author raoBo
 *
 */
@Validated
@RestController
@RequestMapping("api-app/share-log")
public class ShareLogController {

	@Autowired
	private ShareLogService shareLogService;
	
	/***
	 * 分享记录保存
	 * @param shareLog
	 * @return
	 */
	@PostMapping()
	public Result saveShareLog(@Valid @RequestBody ShareLog shareLog){
		shareLogService.saveShareLog(shareLog);
		return Result.success();
	}
	
	/***
	 * 本周分享次数
	 * @return
	 */
	@GetMapping("week")
	public Result weekCounts(){
		 Map<String,Object> count = shareLogService.weekCounts();
		return Result.success(count);
	}
	/***
	 * 微店员工分享列表
	 * @param query
	 * @return
	 */
	@GetMapping("page")
	public Result shareLogPage(){
		List<ShareLogVo> info = shareLogService.findShareLog();
		return Result.success(info);
	}
	
}
