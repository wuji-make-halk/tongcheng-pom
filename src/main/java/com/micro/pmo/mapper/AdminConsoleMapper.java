package com.micro.pmo.mapper;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/

import com.micro.pmo.moudle.console.admin.vo.AdminConsoleVO;

public interface AdminConsoleMapper {

	/**
	 * 统计 统计 本月成交金额， 成交总金额，本月成交总数，成交总数，本月车辆发布总数 平台 ，车辆发布总数 平台，本月注册总数 ，注册用户总数
	 * @return
	 */
	public AdminConsoleVO statistic();
	
}
