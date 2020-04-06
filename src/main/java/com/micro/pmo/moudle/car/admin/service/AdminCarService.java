package com.micro.pmo.moudle.car.admin.service;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月12日 
*/

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarDeal;

public interface AdminCarService {
	/**
	 * 根据id删除车辆信息
	 * @param carId
	 */
	public void deleteCarByCarId(Integer carId) ;
	/**
	 * 查询车辆列表
	 * @return
	 */
	public PageInfo<Car> carList(AdminCarQueryVO query);
	
	/**
	 * 根据id获取详细信息
	 * @param carId
	 * @return
	 */
	public Kv<String,Object> getCarInfoById(Integer carId);
	
	/**
	 * 保存成交价记录
	 */
	public void saveCarDeal(CarDeal carDeal);
	
	/**
	 * 根据车辆id获取成交价记录
	 * @param carId
	 * @return
	 */
	public List<CarDeal> listCarDealByCarId(Integer carId);
}
