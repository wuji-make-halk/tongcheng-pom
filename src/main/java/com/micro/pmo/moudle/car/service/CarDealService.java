package com.micro.pmo.moudle.car.service;


import java.util.List;

import com.micro.pmo.moudle.car.vo.DealInfoVo;
import com.micro.pmo.moudle.car.vo.DealVo;
import com.micro.pmo.moudle.car.vo.InputCarDealVO;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * 车辆成交价Service
 */
public interface CarDealService{

	/**
	 * 保存车辆成交价信息
	 * @param carDeal
	 */
	void saveCarDeal(InputCarDealVO carDeal);
	
    /**
     * 根据carType和carOldBoadTime查询上牌数量
     * @param dealVo
     * @return
     */
    public CounterInfoVO findByIdAndCarTime(DealVo dealVo);

    /**
     * 根据车辆类型查询数据
     * @param dealVo
     * @return
     */
    public List<DealInfoVo> findDealInfoList(DealVo dealVo);


    /**
     * 根据成交Id获取详情
     * @param dealId
     * @return
     */
    public DealInfoVo findDealDetails(Integer dealId);

    /**
         *  购买查询成交价次数
     * @param <T>
     * @param num 购买次数
     * @return
     */
    PayInfoVO buyQueryDeal(InputCusCounterBuyVO input);
}
