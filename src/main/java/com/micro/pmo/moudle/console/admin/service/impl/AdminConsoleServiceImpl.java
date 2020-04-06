package com.micro.pmo.moudle.console.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.mapper.AdminConsoleMapper;
import com.micro.pmo.moudle.console.admin.service.AdminConsoleService;
import com.micro.pmo.moudle.console.admin.vo.AdminConsoleVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
@Service
public class AdminConsoleServiceImpl implements AdminConsoleService {

	@Autowired
	private AdminConsoleMapper adminConsoleMapper;
	
	@Override
	public AdminConsoleVO getConsoleInfo() {
		return adminConsoleMapper.statistic();
	}

}
