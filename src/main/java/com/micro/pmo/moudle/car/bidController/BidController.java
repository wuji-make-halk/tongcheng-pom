package com.micro.pmo.moudle.car.bidController;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.car.service.CarBidService;
import com.micro.pmo.moudle.car.vo.BidVo;

@Validated
@RestController
@RequestMapping("api-app/car/bid")
public class BidController {

    @Autowired
    private CarBidService carBidService;

    /**
     * 添加出价信息
     * @param binPrice
     * @param carId
     * @return
     */
    @PostMapping()
    public Result insertBid(@RequestBody Kv<String,Object> data ){
    	BigDecimal binPrice = data.getBigDecimal("binPrice");
    	if(binPrice == null) {
    		return Result.failure(ResultState.PARAM_ERROR,"出价价格不能为空");
    	}
    	Integer carId = data.getInt("carId");
    	if(carId == null) {
    		return Result.failure(ResultState.PARAM_ERROR,"车辆Id不能为空");
    	}
    	carBidService.insertBid(binPrice,carId);
        return Result.success();
    }


    /**
     * 出价列表
     * @return
     */
    @GetMapping("list")
    public Result bidList(){
        List<BidVo> bidVoList = carBidService.findList();
        return Result.success(bidVoList);
    }

    /**
     * 查看出价详情
     * @param carId
     * @return
     */
    @GetMapping("info")
    public Result bidDetails(@NotNull(message = "车辆ID不能为空") Integer carId){
        Map<String,Object> map = carBidService.findBigDetails(carId);
        return Result.success(map);
    }
}
