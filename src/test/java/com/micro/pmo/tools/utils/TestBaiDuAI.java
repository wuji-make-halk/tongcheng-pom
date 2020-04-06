package com.micro.pmo.tools.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imagesearch.AipImageSearch;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author WangQiLong
 * @Date 2019/7/25 下午4:28
 **/
public class TestBaiDuAI {
    public static final String APP_ID = "16884583";
    public static final String API_KEY = "NC3DbkfTEALS7XNe3l06wOf5";
    public static final String SECRET_KEY = "h0oPENZdD3T81KRi2nqPXCfoCz1SPCrv";
    @Test
    public void testScanCar(){
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        String pic = "/Users/milkyway/Downloads/carcar.jpeg";
        HashMap<String,String> options = new HashMap<>();
        options.put("top_num", null);
        options.put("baike_num", null);
        JSONObject jsonObject = client.carDetect(pic, options);
        jsonObject.toString();
        System.out.println(jsonObject);
    }

}
