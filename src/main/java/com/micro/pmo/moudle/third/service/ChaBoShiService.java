package com.micro.pmo.moudle.third.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

public interface ChaBoShiService {

	/**
	 * 查博士回调
	 * @param data
	 * @return
	 */
	Kv<String, Object> chaboshiNotify(Kv<String, Object> data);
    /**
     * 购买查博士
     * @param input
     * @return
     */
    PayInfoVO payChaboshi(ThirdBuyVO input);

    /**
     * 根据订单查询
     * @param payOrderId
     * @return
     */
    ThirdApiResult getByPayOrderId(String payOrderId);
    /**
     * 分页查询
     * @param query
     * @return
     */
    PageInfo<ThirdApiResult> page(ThirdQuery query);
    
    /**
     * 购买成功的回调
     * @param orderId
     */
    void paySuccess(String orderId);
    
    /**
     * 使用赠送次数查询
     * @param vin
     * @return
     */
    ThirdApiResult useSend(String vin);
    
}
