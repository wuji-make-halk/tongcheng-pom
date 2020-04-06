package com.micro.pmo.moudle.car.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarBidMapper;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarBid;
import com.micro.pmo.moudle.car.service.CarBidService;
import com.micro.pmo.moudle.car.vo.BidCustomerVo;
import com.micro.pmo.moudle.car.vo.BidVo;
import com.micro.pmo.moudle.customer.utils.CusUtils;

@Service
public class CarBidServiceImpl implements CarBidService {

    @Autowired
    private CarBidMapper carBidMapper;
    @Autowired
    private CarMapper carMapper;

    @Transactional
    @Override
    public void insertBid(BigDecimal binPrice, Integer carId) {
    	Car car = carMapper.carInfoById(carId);
    	Integer cusId = CusUtils.getCusId();
    	if(cusId == car.getCreator()){
    		throw new PmoException(ResultState.DEPOSIT_PAY_NOT_PAY,"不能对自己的车辆进行出价");
    	}
        CarBid carBid = new CarBid();
        carBid.setBinPrice(binPrice);
        carBid.setCarId(carId);
        carBid.setCreateTime(new Date());
        carBid.setCreator(cusId);
         carBidMapper.insertBid(carBid);
 
    }

    @Override
    public List<BidVo> findList() {
        //找寻属于自己的车辆ids
        List<Integer> carIds = carMapper.carBelongsToCus(CusUtils.getCusId());
        //用户没有自己车辆直接返回
        if(CollectionUtils.isEmpty(carIds)){
        	return Lists.newArrayList();
        }
        List<BidVo> bidVoList = carBidMapper.getBidlistByCarIds(carIds);
        return bidVoList;
    }


    @Override
    public  Map<String,Object> findBigDetails(Integer carId) {
        Car carInfo = carBidMapper.getCarInfoById(carId);
        List<BidCustomerVo> bidCustomerVo = carBidMapper.getBidCustomer(carId);
        Map<String,Object> map = Maps.newHashMap();
        map.put("carInfo", carInfo);
        map.put("cusBids", bidCustomerVo);
        return map;
    }

}
