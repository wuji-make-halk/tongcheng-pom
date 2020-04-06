package com.micro.pmo.mapper;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.entity.CollectCar;

/**
 * 预定收车有效时间 
 * @author wenhaofan
 * @createtime 
 */
public interface CollectCarMapper {
 
 	/**
 	* 保存预定收车有效时间
 	* @param  cusCollectCar
	* @return
 	**/
 	public int insertCollectCar(CollectCar cusCollectCar);
	
	/**
	* 根据ID更新预定收车有效时间
	* @param cusCollectCar
	* @return
	**/
	public int updateExpirationDateById(CollectCar cusCollectCar);
	
	/**
	* 根据ID获取预定收车有效时间
	* @param cusCollectCarId
	* @return
	**/
	public CollectCar getCollectCarByCusId(@Param("cusId")int cusId);
}