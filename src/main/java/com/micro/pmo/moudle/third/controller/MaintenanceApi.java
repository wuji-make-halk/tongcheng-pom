package com.micro.pmo.moudle.third.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.service.ChaBoShiService;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月12日 
*/
/**
 * @author autu
 *
 */
@Validated
@RestController
@RequestMapping("api-app/maintenance")
public class MaintenanceApi {

	@Autowired
	private ChaBoShiService chaBoShiService;
    
	@Autowired
	private CusCounterService cusCounterService;
	/**
     * 购买保险查询
     * @param input
     * @return
     */
    @PostMapping("pay")
    public Result pay(@Valid @RequestBody ThirdBuyVO input,HttpServletRequest request){
    	String ip = CommonUtils.getIpAddr(request);
    	input.setIp(ip);
    	PayInfoVO r = chaBoShiService.payChaboshi(input);
        return Result.success(r);
    }
    
    /**
     * 通过订单ID去查询维保信息
     * @return
     */
    @GetMapping("info/payOrderId")
    public Result getInsurance(@NotNull(message = "订单ID不能为空")String orderId) {
    	ThirdApiResult apiResult = chaBoShiService.getByPayOrderId(orderId);
    	return Result.success(apiResult);
    }
    
   
    /**
     * 分页查询维保记录
     * @param query
     * @return
     */
    @PostMapping("list")
    public Result listSearchLog(@RequestBody ThirdQuery query) {
    	return Result.success(chaBoShiService.page(query));
    }
 
    /**
     * 获取维保查询赠送次数
     * @return
     */
    @GetMapping("send-info")
	public Result sendInfo() {
		return Result.success(cusCounterService.getCounterInfo(CounterTypeEnum.maintenance));
	}
    
    /**
         *  使用赠送的次数去查询
     * @return
     */
    @PutMapping("use-send")
    public Result useSend(@RequestBody Kv<String,String> data) {	
    	String vin = data.getStr("vin");
    	
    	if (StringUtils.isEmpty(vin)) {
			return Result.failure(ResultState.PARAM_ERROR,"车架号不能为空");
		}
    	
    	return Result.success(chaBoShiService.useSend(vin));
    }
}
