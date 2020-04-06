package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarBid;
import com.micro.pmo.moudle.car.vo.BidCustomerVo;
import com.micro.pmo.moudle.car.vo.BidVo;

/**
 * 车出价Mapper接口
 */
public interface CarBidMapper{


    /**
     * 添加车辆出价
     * @param carBid
     * @return
     */
    public int insertBid(CarBid carBid);

    /**
     * 查看所属车辆出价列表
     * @return
     */
    public List<BidVo> getBidlistByCarIds(@Param("carIds") List<Integer> carIds);

    /**
     * 根据车辆id获取详情
     * @param carId
     * @return
     */
    public Car getCarInfoById(@Param("carId") Integer carId);


    /**
     * 获取出价人信息
     * @param carId
     * @return
     */
    public List<BidCustomerVo> getBidCustomer(@Param("carId") Integer carId);


}
