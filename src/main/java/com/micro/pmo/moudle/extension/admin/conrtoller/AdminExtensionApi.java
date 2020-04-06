package com.micro.pmo.moudle.extension.admin.conrtoller;

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
import com.micro.pmo.moudle.extension.admin.service.AdminExtensionService;
import com.micro.pmo.moudle.extension.entity.Extension;

/**
 * Extension  
 * 
 */
@Validated
@RestController
@RequestMapping("api-pc/extension")
public class AdminExtensionApi{
 	
 	@Autowired
	AdminExtensionService extensionService;
 
 
 	/**
 	 *  保存推广配置
 	 * @param extension
 	 * @return
 	 */
	@PostMapping("save")
	public Result saveExtension(@Valid @RequestBody Extension extension) {
		extensionService.saveExtension(extension);
		return Result.success();
	}
	 
	/**
	 * 更新推广配置
	 * @param extension
	 * @return
	 */
	@PutMapping("update")
	public Result updateExtension(@Valid @RequestBody Extension extension) {
		extensionService.updateExtensionById(extension);
		return Result.success();
	}
  
	/**
	 * 分页查询推广配置
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
 	@GetMapping("list")
 	public Result listSysExtension(BaseQuery query){
 		return Result.success(extensionService.extensionList(query)); 
 	}
}