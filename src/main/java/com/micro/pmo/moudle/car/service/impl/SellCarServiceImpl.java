package com.micro.pmo.moudle.car.service.impl;

import java.math.BigDecimal;
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
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.mapper.SellCarMapper;
import com.micro.pmo.moudle.car.entity.SellCar;
import com.micro.pmo.moudle.car.service.SellCarService;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;

@Service
public class SellCarServiceImpl implements SellCarService{

	@Autowired
	private SellCarMapper sellCarMapper;

	@Autowired
	private CusStoreMapper cusStoreMapper;
	
	@Override
	@Transactional
	public void inserSellCar(SellCar sellCar) {
		Customer cus = CusUtils.getCustomer();
		sellCar.setCreator(cus.getCusId());
		sellCar.setCreateTime(new Date());
		sellCar.setSellStatus(0);
		sellCarMapper.inserSellCar(sellCar);
	}

	@Override
	@Transactional
	public void updateSellCar(SellCar sellCar) {
		SellCar oldsellCar = sellCarMapper.sellCarInfo(sellCar.getSellId());
		if(oldsellCar == null || oldsellCar.getSellStatus() != 0){
			throw new PmoException(ResultState.CAR_STATUS_ERROR,"买车只有在初始状态下可修改");
		}
		sellCarMapper.updateSellCar(sellCar);
	}

	@Override
	public SellCar sellCarInfo(Integer sellCarId) {
		return sellCarMapper.sellCarInfo(sellCarId);
	}

	@Override
	public PageInfo<SellCar> sellCarList(String sellCarStatus,Integer pageNumKey,Integer pageSizeKey) {
		if(pageNumKey == null) pageNumKey = 10;
		if(pageSizeKey == null) pageSizeKey = 1;
		Customer cus = CusUtils.getCustomer();
		List<Integer> cusIds = Lists.newArrayList();
		if(cus.getCusType() == 0){
			cusIds = cusStoreMapper.findeCusIdsById(cus.getStoreId());
		}else{
			cusIds.add(cus.getCusId());
		}
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<SellCar> sellCars = sellCarMapper.sellCarList(cusIds,sellCarStatus);
		PageInfo<SellCar> info = new PageInfo<>(sellCars);
		return info;
	}

	@Override
	@Transactional
	public void updateSellCarStatus(Integer sellCarId, Integer sellStatus,BigDecimal realPrice) {
		sellCarMapper.updateSellCarStatus(sellCarId, sellStatus, realPrice);
	}
	
	
	
}
