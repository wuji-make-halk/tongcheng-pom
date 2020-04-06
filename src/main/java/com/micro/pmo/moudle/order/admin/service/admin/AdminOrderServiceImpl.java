package com.micro.pmo.moudle.order.admin.service.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.mapper.OrderMapper;
import com.micro.pmo.moudle.order.admin.service.AdminOrderService;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderQueryVO;
import com.micro.pmo.moudle.order.admin.vo.AdminOrderVO;
import com.micro.pmo.moudle.user.utils.UserUtils;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	
	@Override
	public PageInfo<AdminOrderVO> orderPage(AdminOrderQueryVO query) {
		if(StringUtils.isBlank(query.getCityName())){
			query.setCityName(UserUtils.getUserCity());
		}
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		
		List<AdminOrderVO> orderList =  orderMapper.cusOrderListNotDeposit(query);
		
		PageInfo<AdminOrderVO> page = new PageInfo<AdminOrderVO>(orderList);
 		
		return page;
	}

}
