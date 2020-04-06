package com.micro.pmo.moudle.car.service.impl;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.BuyCarMapper;
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.moudle.car.entity.BuyCar;
import com.micro.pmo.moudle.car.service.BuyCarService;
import com.micro.pmo.moudle.car.vo.InputUpdateStatusVO;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;

@Service
public class BuyCarServiceImpl implements BuyCarService{

	@Autowired
	private BuyCarMapper buyCarMapper;
	
	@Autowired
	private CusStoreMapper cusStoreMapper;
	
	@Override
	@Transactional
	public void inserBuyCar(BuyCar buyCar) {
		Customer cus = CusUtils.getCustomer();
		buyCar.setCreator(cus.getCusId());
		buyCar.setBuyStatus(0);
		buyCar.setCreateTime(new Date());
		buyCarMapper.inserBuyCar(buyCar);
	}

	@Override
	@Transactional
	public void updateBuyCar(BuyCar buyCar) {
		BuyCar oldbuyCar = buyCarMapper.buyCarInfo(buyCar.getBuyCarId());
		if(oldbuyCar == null || oldbuyCar.getBuyStatus() != 0){
			throw new PmoException(ResultState.PARAM_ERROR,"收车信息修改只可是初始状态");
		}
		
		buyCarMapper.updateBuyCar(buyCar);
		
	}

	@Override
	public BuyCar buyCarInfo(Integer buyCarId) {
		BuyCar buyCar = buyCarMapper.buyCarInfo(buyCarId);
		return buyCar;
	}

	@Override
	public PageInfo<BuyCar> buyCarList(String buyStatus, Integer pageNumKey, Integer pageSizeKey) {
		if(pageNumKey == null) pageNumKey = 1;
		if(pageSizeKey == null) pageSizeKey = 10;
		Customer cus = CusUtils.getCustomer();
		List<Integer> cusIds = Lists.newArrayList();
		if(cus.getCusType() == 0){
			cusIds = cusStoreMapper.findeCusIdsById(cus.getStoreId());
		}else{
			cusIds.add(cus.getCusId());
		}
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<BuyCar> buyCars = buyCarMapper.buyCarList(buyStatus, cusIds);
		PageInfo<BuyCar> info = new PageInfo<>(buyCars);
		return info;
	}

	@Override
	@Transactional
	public void updateBuyCarStatus(InputUpdateStatusVO info) {
		buyCarMapper.updateBuyCarStatus(info.getBuyCarId(), info.getBuyStatus(), info.getClinchPrice());
		
	}

	
}
