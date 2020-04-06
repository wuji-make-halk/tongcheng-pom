package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月12日 
*/
public interface AdminCarMapper {
	/**
	 * 根据id删除车辆信息
	 * @param carId
	 */
	public void deleteCarByCarId(Integer carId) ;
	/**
	 * 查询车辆列表
	 * @return
	 */
	public List<Car> carList(AdminCarQueryVO query);
	
	/**
	 * 根据id获取详细信息
	 * @param carId
	 * @return
	 */
	public Car getCarInfoById(Integer carId);
	
	 
}
