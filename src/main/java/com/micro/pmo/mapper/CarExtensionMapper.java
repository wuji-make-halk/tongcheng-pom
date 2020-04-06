package com.micro.pmo.mapper;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.entity.CarExtension;

/**
 * 车辆推广Mapper接口
 */
public interface CarExtensionMapper {

	/*** 保存CarExtension ***/
	public int insertCarExtension(CarExtension carExtension);

	/*** 根据ID删除CarExtension，假删除 ***/
	public int deleteCarExtensionById(int carExtensionId);

	/**
	 * 根据ID更新CarExtension
	 * 
	 * @param carExtension
	 * @return
	 **/
	public int updateCarExtensionById(CarExtension carExtension);

	/*** 根据ID获取CarExtension ***/
	public CarExtension getCarExtensionById(int carExtensionId);

	/**
	 * 根据carID 和 type 获取有效的推广记录
	 * 
	 * @param carId
	 * @return
	 */
	public CarExtension getValidExtension(@Param("carId") Integer carId, @Param("type") Integer type);
}
