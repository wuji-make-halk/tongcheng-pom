package com.micro.pmo.moudle.brand.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.brand.admin.service.AdminBrandService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月2日 
*/
@Validated
@RestController
@RequestMapping("api-pc/brand")
public class AdminBrandApi {
	
	@Autowired
	private AdminBrandService adminBranService;
	
	/**
	 *  品牌车型列表
	 * @param type
	 * @param parentId
	 * @return
	 */
	@GetMapping("list")
	public Result list(@RequestParam(required = false,defaultValue =  "0")Integer type,Integer parentId) {
		return Result.success(adminBranService.branList(type, parentId));
	}
	
}
