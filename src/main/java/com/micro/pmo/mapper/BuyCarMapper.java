package com.micro.pmo.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.entity.BuyCar;

/**
 * 收车Mapper接口
 */
public interface BuyCarMapper{
	
	
	/**收车信息写入***/
	public void inserBuyCar(BuyCar buyCar);
	
	/***收车信息修改***/
	public void updateBuyCar(BuyCar buyCar);
	
	/***收车详情***/
	public BuyCar buyCarInfo(@Param("buyCarId")Integer buyCarId);
	
	/**收车列表**/
	public List<BuyCar> buyCarList(@Param("buyStatus")String buyStatus,@Param("cusIds")List<Integer> cusIds);
	/**修改收车状态**/
	public void updateBuyCarStatus(@Param("buyCarId")Integer buyCarId,@Param("buyStatus")Integer buyStatus,@Param("clinchPrice")BigDecimal clinchPrice);
}
