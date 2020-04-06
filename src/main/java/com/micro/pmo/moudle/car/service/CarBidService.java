package com.micro.pmo.moudle.car.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.micro.pmo.moudle.car.vo.BidVo;

/**
 * 车出价Service
 */
@Service
public interface CarBidService{

    /**
     * 添加车出价信息
     * @param binPrice
     * @param carId
     */
    public void insertBid(BigDecimal binPrice, Integer carId);


    /**
     * 出价列表
     * @return
     */
    public List<BidVo> findList();

    /**
     * 出价详情
     * @param carId
     * @return
     */
    public  Map<String,Object> findBigDetails(Integer carId);
}
