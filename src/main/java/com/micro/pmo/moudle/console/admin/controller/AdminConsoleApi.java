package com.micro.pmo.moudle.console.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.console.admin.service.AdminConsoleService;
import com.micro.pmo.moudle.console.admin.vo.AdminConsoleVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
@RestController
@RequestMapping("api-pc/console")
public class AdminConsoleApi {

	@Autowired
	private AdminConsoleService adminConsoleService;
	
	/**
	 * 统计 
	 * @return
	 */
	@GetMapping("statistic")
	public Result statistic() {
		AdminConsoleVO console = adminConsoleService.getConsoleInfo();
		return Result.success(console);
	}
	
}
