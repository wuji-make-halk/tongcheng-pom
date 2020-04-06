package com.micro.pmo.moudle.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.service.OrderNotifyService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月25日 
*/
@Component
public class OrderNotifyServiceFactory {

	private Map<OrderPayTypeEnum,OrderNotifyService> services = new HashMap<>();
	
	/**
	 * 注册回调业务
	 * @param type
	 * @param service
	 */
	public void register(OrderPayTypeEnum type,OrderNotifyService service) {
		services.put(type, service);
	}
	
	public OrderNotifyService getOrderNotifyService(OrderPayTypeEnum type) {
		return services.get(type);
	}
}
