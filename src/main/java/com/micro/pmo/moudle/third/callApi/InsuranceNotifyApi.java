package com.micro.pmo.moudle.third.callApi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.third.service.InsuranceService;

/**
 * @Author WangQiLong
 * @Date 2019/7/22 下午3:51
 * che300回调接口
 **/
@Validated
@RestController
@RequestMapping("api-pc/car300")
public class InsuranceNotifyApi {
    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("insurance-notify")
    public Kv<String,Object> buyReportBack( @RequestParam Map<String,Object> data){
        return insuranceService.insuranceNotify(new Kv<String, Object>().set(data));
    }

}
