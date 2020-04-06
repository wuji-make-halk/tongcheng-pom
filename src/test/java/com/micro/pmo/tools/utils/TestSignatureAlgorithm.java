//package com.micro.pmo.tools.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.micro.pmo.AppPmo;
//import com.micro.pmo.commons.utils.FastJSONUtils;
//import com.micro.pmo.commons.utils.Result;
//import com.micro.pmo.commons.utils.SignatureAlgorithm;
//import com.micro.pmo.moudle.car.entity.Car;
//import com.micro.pmo.moudle.open.service.CarThreeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author WangQiLong
// * @Date 2019/7/19 下午7:00
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppPmo.class)
//public class TestSignatureAlgorithm {
//    //购买报告
//    @Autowired
//    private CarThreeService carThreeService;
//    
//    /* @Test
//    public void testBuyCarMsg(){
//        HashMap<String,String> test = new HashMap<>();
//        Car car = new Car();
//        car.setCarId(9527);
//        car.setEngineNumber("4377E279");
////        car.setCarVin("LS5A3DBE8FA117532");
//        car.setCarVin("LGBN22E28AY001155");
//        Result result = SignatureAlgorithm.buyCarMsg(car);
//        String data = (String)result.getData();
//        Result result1 = JSONObject.parseObject(data, Result.class);
//        JSONObject jsonObject = (JSONObject) result1.getData();
//        Map<String, String> strMap = FastJSONUtils.toMap(jsonObject);
//        String order_no = strMap.get("order_no");
//        System.out.println(result);
//
//    }*/
//    //获取报告详情
//    @Test
//    public void testGetReport(){
//        String orderNo = "D725217515843193";
////        String orderNo = "D724351988704623";
//        Result reportInfo = SignatureAlgorithm.getReportInfo(orderNo,"LGBN22E28AY001155");
//        System.out.println(reportInfo);
//    }
//
//}
