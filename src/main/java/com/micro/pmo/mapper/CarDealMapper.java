package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.car.entity.CarDeal;
import com.micro.pmo.moudle.car.vo.DealInfoVo;
import com.micro.pmo.moudle.car.vo.DealVo;
import org.apache.ibatis.annotations.Param;

/**
 * 车辆成交价Mapper接口
 */
public interface CarDealMapper{

	
	
    /**
     * 查询成交数量
     * @param dealVo
     * @return
     */
    public int findDealSize(DealVo dealVo);


    /**
     * 根据车辆类型,查询DealInfoVo
     * @param dealVo
     * @return
     */
    public List<DealInfoVo> findDealInfoList(DealVo dealVo);

    /**
     * 插入成交价
     * @param carDeal
     * @return
     */
    public int insertCarDeal(CarDeal carDeal);


    /**
     * 根据描述ID获取成交信息详情
     * @param dealId
     * @return
     */
    public DealInfoVo getDealInfo(@Param("dealId") Integer dealId);

    /**
     * 根据车辆id获取成交信息详情列表
     * @param carId
     * @return
     */
    public List<CarDeal> listCarDealByCarId(Integer carId);
    
    /***
     * carId 和 dealType 获取 成交价
     * @param carId
     * @param dealType
     * @return
     */
    public CarDeal getCarDealByIdAndType(@Param("carId")Integer carId,@Param("dealType")Integer dealType);
}
