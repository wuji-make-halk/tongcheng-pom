package com.micro.pmo.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.entity.SellCar;

/**
 * 买车记录Mapper接口
 */
public interface SellCarMapper{
	
	/**买车信息写入***/
	public int inserSellCar(SellCar sellCar);
	
	/***买车信息修改***/
	public int updateSellCar(SellCar sellCar);
	
	/***买车详情***/
	public SellCar sellCarInfo(@Param("sellCarId")Integer sellCarId);
	
	/**买车列表**/
	public List<SellCar> sellCarList(@Param("cusIds")List<Integer> cusIds,@Param("status")String status);
	
	/**修改买车状态***/
	public int updateSellCarStatus(@Param("sellCarId")Integer sellCarId,@Param("sellStatus")Integer sellStatus,@Param("realPrice")BigDecimal realPrice);
}
