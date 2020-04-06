package com.micro.pmo.tools.moudle.vip;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.micro.pmo.moudle.vip.service.CusVipService;
import com.micro.pmo.tools.moudle.common.BaseJunit;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月4日 
*/
public class TestBuyVipService extends BaseJunit{

	@Autowired
	private CusVipService CusVipService;
	
	@Test
	public void TestBuyVipService() {
		//assertTrue(CusVipService.buyVip(1, 1,5));;
	}
	
}
