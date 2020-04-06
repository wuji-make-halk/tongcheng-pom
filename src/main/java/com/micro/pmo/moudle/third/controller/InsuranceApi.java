package com.micro.pmo.moudle.third.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.service.InsuranceService;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

/**
 * @Author WangQiLong
 * @Date 2019/7/24 上午10:32
 * 车300app接口
 **/
@Validated
@RestController
@RequestMapping("api-app/insurance")
public class InsuranceApi {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private CusCounterService cusCounterService;
    
    /**
     * 获取保险查询赠送信息
     * @return
     */
    @GetMapping("send-info")
    public Result sendInfo() {
    	return Result.success(cusCounterService.getCounterInfo(CounterTypeEnum.insurance));
    }
    
    /**
	  *  使用赠送的次数
	 * @return
	 */

	@PutMapping("use-send")
	public Result useSend(@RequestBody Map<String,String> map) {
		String vin = map.get("vin");
		if(StringUtils.isBlank(vin)){
			return Result.failure(ResultState.PARAM_ERROR,"参数异常:校验错误:参数[vin]车架号不能为空。");
		}
		return Result.success(insuranceService.useSend(vin));
	}
    
    /**
     * 购买保险查询
     * @param input
     * @return
     */
    @PostMapping("pay")
    public Result payInsurance(@Valid @RequestBody ThirdBuyVO input,HttpServletRequest request){
    	String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
    	PayInfoVO r = insuranceService.payInsurance(input);
        return Result.success(r);
    }
    
    /**
     * 通过订单ID去查询维保信息
     * @return
     */
    @GetMapping("info/payOrderId")
    public Result getInsurance(@NotNull(message = "订单ID不能为空")String orderId) {
    	ThirdApiResult apiResult = insuranceService.getInsuranceByPayOrderId(orderId);
    	return Result.success(apiResult);
    }
    
    /**
     * 通过结果记录获取维保信息
     * @param resultId
     * @return
     */
    @GetMapping("info")
    public Result getInsurance(@NotNull(message = "记录id不能为空")Integer resultId) {
    	return Result.success(insuranceService.getInsuranceById(resultId));
    }
    
    /**
     * 获取当前用户曾经查询过的有效的车架号维保信息
     * @param payOrderId
     * @return
     */
    @GetMapping("info/vin")
    public Result getInsuranceByVin(@NotNull(message = "车架号不能为空")String vin) {
    	ThirdApiResult apiResult = insuranceService.getInsuranceByVin(vin);
    	return Result.success(apiResult);
    }
    
    /**
     * 分页查询维保记录
     * @param query
     * @return
     */
    @PostMapping("list")
    public Result listSearchLog(@RequestBody ThirdQuery query) {
    	return Result.success(insuranceService.page(query));
    }
    
    

}
