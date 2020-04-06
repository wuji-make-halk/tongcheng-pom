package com.micro.pmo.moudle.car.service;

import java.math.BigDecimal;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.entity.SellCar;

/**
 * 买车记录Service
 */
public interface SellCarService{
	
	/**买车信息写入***/
	public void inserSellCar(SellCar sellCar);
	
	/***买车信息修改***/
	public void updateSellCar(SellCar sellCar);
	
	/***买车详情***/
	public SellCar sellCarInfo(Integer sellCarId);
	
	/**买车列表**/
	public PageInfo<SellCar> sellCarList(String sellCarStatus,Integer pageNumKey,Integer pageSizeKey);
	/**修改买车状态**/
	public void updateSellCarStatus(Integer sellCarId,Integer sellStatus,BigDecimal realPrice);
}
