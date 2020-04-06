package com.micro.pmo.moudle.car.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarDealMapper;
import com.micro.pmo.moudle.car.service.CarDealService;
import com.micro.pmo.moudle.car.vo.DealInfoVo;
import com.micro.pmo.moudle.car.vo.DealVo;
import com.micro.pmo.moudle.car.vo.InputCarDealVO;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

@Service
public class CarDealServiceImpl implements CarDealService {

    @Autowired
    private CarDealMapper carDealMapper;
    @Autowired
    private CusCounterService cusCounterService;

    @Override
	public void saveCarDeal(InputCarDealVO carDeal) {
    	carDeal.setCreator(CusUtils.getCusId());
    	carDeal.setCreateTime(new Date());
    	
		carDealMapper.insertCarDeal(carDeal);
	}
    
	/**
         *  根据车辆类型交易数量
     * @param dealVo 
     * @return
     */
    @Override
    public CounterInfoVO findByIdAndCarTime(DealVo dealVo) {
    	carOldBoadTimeHandle(dealVo);
        int dealSize = carDealMapper.findDealSize(dealVo);
        CounterInfoVO vo = cusCounterService.getCounterInfo(CounterTypeEnum.queryPrice);
        vo.setDealSize(dealSize);
        return vo;
    }
    /***
     * 上牌时间处理
     * @param dealVo
     */
    private void carOldBoadTimeHandle(DealVo dealVo){
    	Integer carOldBoadTime = dealVo.getCarOldBoadTime();
    	if(carOldBoadTime == null || carOldBoadTime == 0){
    		return ;
    	}
		Date maxDate = new Date();

		if(carOldBoadTime == 7){
			carOldBoadTime = 6;
		}
		//上牌时间处理
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(maxDate);//设置起时间
	    cal.add(Calendar.YEAR, -carOldBoadTime);//减少几年
	    Date minDate = cal.getTime();
	
		dealVo.setMaxDate(maxDate);
		dealVo.setMinDate(minDate);
    }
    
    /**
         * 根据车辆类型查询list
     * @param dealVo
     * @return
     */
    @Override
    public List<DealInfoVo> findDealInfoList(DealVo dealVo) {
    	carOldBoadTimeHandle(dealVo);
    	List<DealInfoVo> list = carDealMapper.findDealInfoList(dealVo);
    	if(CollectionUtils.isEmpty(list)){
    		return list;
    	}
    	 CounterInfoVO vo = cusCounterService.getCounterInfo(CounterTypeEnum.queryPrice);
    	 //次数不够抛出异常
    	if(vo.getSurplusSize() < 1){
    		throw new PmoException(ResultState.FREQUENCY_ERROR);
    	}
    	//使用掉次数
    	cusCounterService.reduceSend(CounterTypeEnum.queryPrice, 1);
        return list;
    }


    /**
     * 根据描述Id获取详情
     * @param dealId
     * @return
     */
    @Override
    public DealInfoVo findDealDetails(Integer dealId) {
        DealInfoVo dealInfoVo = carDealMapper.getDealInfo(dealId);
        return dealInfoVo;
    }

	@Override
	public PayInfoVO buyQueryDeal(InputCusCounterBuyVO input) {
 
		if (input.getNum() != null && input.getNum() <=0) {
			throw new PmoException(ResultState.PARAM_ERROR,"购买次数不能为空");
		}
		
		return cusCounterService.buy(input, CounterTypeEnum.queryPrice);
	}

 
}
