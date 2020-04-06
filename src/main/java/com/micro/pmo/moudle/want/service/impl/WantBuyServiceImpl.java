package com.micro.pmo.moudle.want.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.WantBuyMapper;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.want.entity.WantBuy;
import com.micro.pmo.moudle.want.service.WantBuyService;

@Service
public class WantBuyServiceImpl implements WantBuyService{
	
	@Autowired
	private WantBuyMapper wantBuyMapper;
	
	
	@Override
	@Transactional
	public int intserWantBuy(WantBuy wantBuy) {
		Integer cusId = null;
		if(CusUtils.isLogin()){
			cusId = CusUtils.getCusId();
		}
		wantBuy.setCreator(cusId);
		wantBuy.setCreateTime(new Date());
		wantBuyMapper.intserWantBuy(wantBuy);
		return 0;
	}

	@Override
	public PageInfo<WantBuy> wantBuyPage(Integer pageNumKey,Integer pageSizeKey) {
		Customer cus = CusUtils.getCustomer();
		if(cus.getStoreId() == null){
			throw new PmoException(ResultState.STORE_ERROR);
		}
		//店长可以看全部的，员工各看各的
		Integer cusId = null;
		if(cus.getCusType() == 1){
			cusId = cus.getCusId();
		}
		if(pageNumKey == null) pageNumKey = 10;
		if(pageSizeKey == null) pageSizeKey = 1;
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<WantBuy> wantBuyList = wantBuyMapper.wantBuyByStoreIdAndCusId(cus.getStoreId(), cusId);
		PageInfo<WantBuy> pageInfo = new PageInfo<WantBuy>(wantBuyList);
		return pageInfo;
	}

	
}
