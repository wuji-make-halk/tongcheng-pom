package com.micro.pmo.tools.moudle.app.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.car.vo.UserOrderDepositVO;
import com.micro.pmo.tools.moudle.common.BaseJunit;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月6日 
*/
public class TestUserOrderDeposit extends BaseJunit{

	@Test
	public void testUserOrderDeposit() {
		
		List<UserOrderDepositVO> depositList = new ArrayList() {{
			UserOrderDepositVO orderDeposit = new UserOrderDepositVO();
			
			orderDeposit.setCarFactoryTime(new Date());
			orderDeposit.setBrandSeries("ARCFOXLITE2017款 原力限量版");
			orderDeposit.setCarImg("http://img.schyxgl.com/20190716172225xwxX1CjoMt.png");
			orderDeposit.setCarMileage(BigDecimal.valueOf(78.00));
			orderDeposit.setCityLocation("成都市");
			orderDeposit.setDealStatus(0);
			orderDeposit.setDepositPrice(BigDecimal.valueOf(800));
			orderDeposit.setInterPrice(BigDecimal.valueOf(8000));
			orderDeposit.setOrderId("545456565");
//			orderDeposit.setSellerHeadImg("http://img.schyxgl.com/default.png");
//			orderDeposit.setSellerName("测试卖家");
			orderDeposit.setType(0);
			add(orderDeposit);
		}};
		
		System.out.println(JSONObject.toJSONString(Result.success(depositList)));
		
	}
	
}
