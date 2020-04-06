package com.micro.pmo.moudle.car.dealController;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.car.service.CarDealService;
import com.micro.pmo.moudle.car.vo.DealInfoVo;
import com.micro.pmo.moudle.car.vo.DealVo;
import com.micro.pmo.moudle.car.vo.InputCarDealVO;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;

@Validated
@RestController
@RequestMapping("api-app/car/deal")
public class DealController {


    @Autowired
    private CarDealService carDealService;

    @PostMapping()
    public Result save(@RequestBody InputCarDealVO carDeal) {
    	carDealService.saveCarDeal(carDeal);
    	return Result.success();
    }
    
    /***
     * 成交价条数
     * @param dealVo
     * @return
     */
    @PostMapping("deal-size")
    public Result dealSize(@RequestBody DealVo dealVo){
    	CounterInfoVO vo = carDealService.findByIdAndCarTime(dealVo);
        return Result.success(vo);
    }

    /***
     * 成交价列表
     * @param dealVo
     * @return
     */
    @PostMapping("list")
    public Result dealList(@RequestBody DealVo dealVo) {
    	List<DealInfoVo> vo = carDealService.findDealInfoList(dealVo);
        return Result.success(vo);
    }


    /**
     * 成交详情
     * @param dealId
     * @return
     */
    @GetMapping("info")
    public Result dealDetails(@NotNull(message = "成交Id不能为空") Integer dealId){
        DealInfoVo dealInfoVo = carDealService.findDealDetails(dealId);
        return Result.success(dealInfoVo);
    }
 
    /**
        * 购买成交价次数
     * @param input
     * @return
     */
    @PostMapping("buy")
    public Result buy(HttpServletRequest request,@Valid @RequestBody InputCusCounterBuyVO input) {
    	String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
    	return Result.success(carDealService.buyQueryDeal(input));
    }
    
}
