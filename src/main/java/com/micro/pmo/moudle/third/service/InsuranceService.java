package com.micro.pmo.moudle.third.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

public interface InsuranceService {
	
	/**
	 * 处理回调通知
	 * @param data
	 * @return
	 */
	Kv<String,Object> insuranceNotify(Kv<String,Object> data);
	
	/**
	 * 通过VIN获取保险报告
	 * @param vin
	 * @return
	 */
	ThirdApiResult getInsuranceByVin(String vin);
	
	/**
	 * 购买查询报告
	 * @param input
	 * @return
	 */
	PayInfoVO payInsurance(ThirdBuyVO input);
	
	/**
	 * 通过resultId去获取记录
	 * @param id
	 * @return
	 */
	ThirdApiResult getInsuranceById(Integer id);
	
	/**
	 * 通过支付订单ID获取记录
	 * @param payOrderId
	 * @return
	 */
	ThirdApiResult getInsuranceByPayOrderId(String payOrderId);
	
    /**
     * 支付成功回调
     * @param orderId
     * @return
     */
	void paySuccess(String orderId) ;
	
	/**
	 * 分页查询保险记录
	 * @return
	 */
	PageInfo<ThirdApiResult> page(ThirdQuery query);
	
	/**
	 * 使用赠送次数查询
	 * @param vin
	 * @return
	 */
	Kv<String, Object> useSend(String vin);
}
