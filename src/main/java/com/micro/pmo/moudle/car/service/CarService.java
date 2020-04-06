package com.micro.pmo.moudle.car.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.vo.CarCountByStatusVO;
import com.micro.pmo.moudle.car.vo.CarLowerVo;
import com.micro.pmo.moudle.car.vo.FindCarQueryVO;
import com.micro.pmo.moudle.car.vo.FindCarVO;

/**
 * 车辆信息Service
 */
public interface CarService{
	
	/**保存车辆信息**/
	public void saveCar(Car car);
	
	/**修改车辆信息**/
	public void updateCar(Car car);
	
	/**车辆的基本信息，用于修改***/
	public Car carInfo(Integer carId);
	
	/***车辆管理分页列表***/
	public PageInfo<FindCarVO> carList(Integer carStatus,String keyword,Integer pageNumKey,Integer pageSizeKey);
	
	/**用户进入，区分分享链接/直接进入车辆详情:*/
	public Map<String,Object> showCarInfo(Integer carId);
	
	/***平台个人车辆列表***/
	public PageInfo<FindCarVO> cusCarList(Integer cusId,Integer pageNumKey,Integer pageSizeKey);
	
	/**
	  * 分页查询
	 * @param query
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	PageInfo<FindCarVO> findCar(FindCarQueryVO query); 
	
	/***
	 * 车辆下架
	 * @param lowerVo
	 */
	public void saveCarDeal(CarLowerVo lowerVo);
	/***
	 * 订金支付详情页
	 */
	public Map<String,Object> depositCarInfo(Integer carId);

	/**
	 * 随机获取最新发布的平台车辆中的前20条中的5条
	 * @return
	 */
	public List<FindCarVO> favoriteCarList();
	
	/***
	 * 分享平台车源列表
	 * @param carIds
	 * @return
	 */
	public List<FindCarVO> shareCarListByCarIds(String carIds);
	
	/**
	 * 根据车辆状态统计该用户的车辆次数
	 * @param cusId
	 * @return
	 */
	public CarCountByStatusVO carCountByStatus();
}
