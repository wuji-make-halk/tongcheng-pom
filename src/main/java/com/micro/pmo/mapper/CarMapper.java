package com.micro.pmo.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.vo.CarCountByStatusVO;
import com.micro.pmo.moudle.car.vo.CarInfoVo;
import com.micro.pmo.moudle.car.vo.FindCarQueryVO;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.car.vo.ReserveCarVo;
import com.micro.pmo.moudle.extension.vo.ExtensionCarVO;

/**
 * 车辆信息Mapper接口
 */
public interface CarMapper{
	
	/**写入车辆信息***/
	public int insertCar(Car car);
	
	/***修改车辆信息***/
	public int updateCarById(Car car);
	
	/**获取车辆信息**/
	public Car carInfoById(@Param("carId") Integer carId);
	
	/***修改车辆状态***/
	@Update("UPDATE car_info SET car_status = #{carStatus} WHERE car_id = #{carId}")
	public int updateCarStatusById(@Param("carId") Integer carId,@Param("carStatus")Integer carStatus);
	
	/**
	 * 根据状态获取推广列表车辆
	 * @param status
	 * @return
	 */
	public List<ExtensionCarVO> extensionCarList(@Param("status")Integer status,@Param("cusId")Integer cusId);
	
	/**
	 *  根据 carId 修改推广状态
	 * @param status
	 * @param carId
	 * @return
	 */
	public int updateIsPromotionByCarId(@Param("isPromotion")Integer isPromotion,@Param("carId")Integer  carId);
	
	/**
	  *  根据carId修改更新刷新时间
	 * @param carId
	 * @param flushDate
	 */
	public void updateFlushDateByCarId(@Param("carId")Integer carId,@Param("flushDate")Date flushDate);
	
	/**
	 * 根据条件查询平台的在售车辆信息
	 * @param query
	 * @return
	 */
	List<FindCarVO> findCar(FindCarQueryVO query);
	
	/**
	 * 平台用户查看车辆或微店车辆详情，找车
	 * @param carId
	 * @param platform
	 * @param microShop
	 * @return
	 */
	public CarInfoVo showCarInfoById(@Param("carId")Integer carId,@Param("platform")Integer platform,@Param("microShop")Integer microShop);
	
	/***
	 * 平台用户查看车辆或微店车,匹配车辆
	 * @param carId,name
	 * @return
	 */
	public List<FindCarVO> showCarInfosByName(@Param("name")String name,@Param("carId")Integer carId);
	
	/***
	 * 微店匹配车辆
	 * @param carName
	 * @param carId
	 * @return
	 */
	public List<FindCarVO> getStoreMatchedCar(@Param("storeId")Integer storeId,@Param("carId")Integer carId);
	
	/***
	 * 分享平台车源列表
	 * @param carIds
	 * @return
	 */
	public List<FindCarVO> shareCarListByCarIds(@Param("carIds")String carIds);
	
	/****
	 * 查询个人车数总量
	 * @param cusId
	 * @return
	 */
	public Integer carSizeByCusId(@Param("cusId")Integer cusId,@Param("cusIds")List<Integer> cusIds);
	
	/***
	 * 个人平台车源
	 * @param cusId
	 * @return
	 */
	public List<FindCarVO> getCarByCusId(@Param("cusId")Integer cusId,@Param("cusIds")List<Integer> cusIds);
	
	/***
	 * 管理助手车辆列表查询
	 * @param cusIds
	 * @param cusId
	 * @param carStatus
	 * @param keyword
	 * @return
	 */
	public List<FindCarVO> getCarListByStatusAndId(@Param("cusIds")List<Integer> cusIds,@Param("cusId")Integer cusId,@Param("carStatus")Integer carStatus,@Param("keyword")String keyword);
	
	/***
	 * 订金车辆详情
	 * @param carId
	 * @return
	 */
	public Car getDepositCarById(@Param("carId")Integer carId);
	
	/**
	 * 随机获取最新发布的平台车辆中的前20条中的4条 不包含当前用户发布的车辆
	 * @return
	 */
	public List<FindCarVO> favoriteCarList(@Param("cusId")Integer cusId);
	
	/**
	 * 根据车辆状态统计该用户的车辆次数
	 * @param cusIds
	 * @return
	 */
	public CarCountByStatusVO carCountByStatus(@Param("cusIds")List<Integer> cusIds);
	
	/**
	 * 修改是否已交定金 根据车辆id
	 * @param carId
	 * @param isOrder
	 */
	public void updateIsOrderByCarId(@Param("carId")Integer carId,@Param("isOrder")boolean isOrder);
	
	/***
	 * 预订收车匹配
	 * @param boadTime
	 * @param carType
	 * @param carColor
	 * @param carMileage
	 * @return
	 */
	public List<ReserveCarVo> getCarMatched(@Param("boadTime")Integer boadTime,@Param("carType")String carType,@Param("carColor")String carColor,@Param("carMileage")BigDecimal carMileage);
	
	/***
	 * 查找属于自己的车辆id
	 * @param cusId
	 * @return
	 */
	public List<Integer> carBelongsToCus(@Param("cusId")Integer cusId);
}
