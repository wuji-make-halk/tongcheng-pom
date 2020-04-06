package com.micro.pmo.moudle.car.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.entity.BuyCar;
import com.micro.pmo.moudle.car.vo.InputUpdateStatusVO;

/**
 * 收车Service
 */
public interface BuyCarService{
	
	/**收车信息写入***/
	public void inserBuyCar(BuyCar buyCar);
	
	/***收车信息修改***/
	public void updateBuyCar(BuyCar buyCar);
	
	/***收车详情***/
	public BuyCar buyCarInfo(Integer buyCarId);
	
	/**收车列表**/
	public PageInfo<BuyCar> buyCarList(String buyStatus,Integer pageNumKey,Integer pageSizeKey);
	/**修改收车状态**/
	public void updateBuyCarStatus(InputUpdateStatusVO info);
	
}
