package com.micro.pmo.moudle.car.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarExtensionMapper;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.moudle.car.entity.CarExtension;
import com.micro.pmo.moudle.car.service.FlushService;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月12日
 */
@Service
public class FlushServiceImpl implements FlushService {

	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private CusCounterService cusCounterService;

	@Autowired
	private CarExtensionMapper carExtensionMapper;
	
	@Override
	public void flushCar(Integer carId) {
		CarExtension carExtension = carExtensionMapper.getValidExtension(carId, 1);
		if(carExtension == null || carExtension.getExtensionExpirationDate().getTime() < new Date().getTime()){
			 throw new PmoException(ResultState.CAR_FLUSH_ERROR);
		}
		carMapper.updateFlushDateByCarId(carId, new Date());
	}
	

	@Override	
	public CounterInfoVO getFlushCounterInfo() {
		return cusCounterService.getCounterInfo(CounterTypeEnum.flush);
	}
 
 

}
