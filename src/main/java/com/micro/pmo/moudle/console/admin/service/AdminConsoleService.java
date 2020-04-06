package com.micro.pmo.moudle.console.admin.service;

import com.micro.pmo.moudle.console.admin.vo.AdminConsoleVO;
/** 
 * 控制台业务层
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
public interface AdminConsoleService {

	/**
	 * 获取控制台统计参数
	 * @return
	 */
	public AdminConsoleVO getConsoleInfo();
	
}
