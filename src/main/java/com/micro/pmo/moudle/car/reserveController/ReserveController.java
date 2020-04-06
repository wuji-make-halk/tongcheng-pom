package com.micro.pmo.moudle.car.reserveController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.jpush.PushUtils;
import com.micro.pmo.moudle.car.entity.ReserveCar;
import com.micro.pmo.moudle.car.service.CollectCarService;
import com.micro.pmo.moudle.car.service.ReserveCarService;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;

@Validated
@RestController
@RequestMapping("api-app/car/reserve")
public class ReserveController {

    @Autowired
    private ReserveCarService reserveCarService;

    @Autowired
    private CollectCarService collectCarService;
    
    /***
     * 预订收车是否可在添加
     * @return
     */
    @GetMapping("use-time")
    public Result reserveUseTime(){
    	Map<String,Object> map = reserveCarService.reserveUseTime();
    	 return Result.success(map);
    }
    /**
     * 添加预定收车
     * @param reserveCar
     * @return
     */
    @PostMapping()
    public Result addReserve(@Valid @RequestBody ReserveCar reserveCar) {
        reserveCarService.insertReserveCar(reserveCar);
        return Result.success();
    }
    /***
     * 修改预定收车
     * @param reserveCar
     * @return
     */
    @PutMapping("update")
    public Result updateReserve(@Valid @RequestBody ReserveCar reserveCar){
    	reserveCarService.updateReserveCar(reserveCar);
    	 return Result.success();
    }
    /**
     * 用户心愿列表
     * @param reserveStatus
     * @return
     */
    @GetMapping("list")
    public Result reserveList(){
        List<ReserveCar> list = reserveCarService.findReserveByIdList();
        return Result.success(list);
    }
    /***
     * 用于修改
     * @param reserveId
     * @return
     */
    @GetMapping("info")
    public Result reserveInfo(@NotNull(message = "心愿Id不能为空") Integer reserveId){
    	ReserveCar reserveCar = reserveCarService.reserveCarInfo(reserveId);
    	return Result.success(reserveCar);
    }
    /***
     * 用于展示
     * @param reserveId
     * @return
     */
    @GetMapping("matched-info")
    public Result reserveMatchedInfo(@NotNull(message = "心愿Id不能为空") Integer reserveId){
    	Map<String,Object> map = reserveCarService.reserveMatchedInfo(reserveId);
    	return Result.success(map);
    }
    
    /**
     * 删除心愿点
     * @param reserveId
     * @return
     */
    @DeleteMapping("del")
    public Result deleteReserve(@RequestBody @NotNull(message = "心愿Id不能为空") Integer reserveId){
        reserveCarService.updateReserveStatus(reserveId);
        return Result.success();
    }
	
    
    
	/**
	 * 购买预定收车
	 * @param request
	 * @param data
	 * @return
	 */
	@PostMapping("buy")
	public Result buy(HttpServletRequest request, @RequestBody @Valid  InputCusCounterBuyVO input) {
		String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
		return Result.success(collectCarService.buyCollectCar(input));
	}
	
	/***
	 * TODO 待删除
	 */
	@GetMapping("jpush-test")
	public Result jpushTest(){
		//开始添加
		List<String> aliasList = Lists.newArrayList();
		aliasList.add("35382308327110");
		Map<String,String> extrasparam = Maps.newHashMap();
		extrasparam.put("reserveId", "1");
		PushUtils.jPushCusToIos(aliasList, "预订收车", "预订收车", "我的预订列表进入测试", extrasparam);
		return Result.success();
	}
}
