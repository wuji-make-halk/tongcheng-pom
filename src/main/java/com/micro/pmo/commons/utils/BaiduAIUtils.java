package com.micro.pmo.commons.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 百度AI工具
 * @Author WangQiLong
 * @Date 2019/7/25 下午4:41
 **/
public class BaiduAIUtils {

    public static final String APP_ID = "16884583";
    public static final String API_KEY = "NC3DbkfTEALS7XNe3l06wOf5";
    public static final String SECRET_KEY = "h0oPENZdD3T81KRi2nqPXCfoCz1SPCrv";

    /**
     * 车辆识别工具
     * @param file 图像数据(二进制数组)，base64编码，要求base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式 。注意：图片需要base64编码、去掉编码头后再进行urlencode。
     * @param topNum 返回预测得分top结果数，如果为空或小于等于0默认为5；如果大于20默认20
     * @param baikeNum 返回百科信息的结果数，默认值为0，不返回；2为返回前2个结果的百科信息，以此类推。
     * @return
     */
    public static String getCarMsg(byte[] file,String topNum,String baikeNum){
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        HashMap<String,String> options = new HashMap<>();
        if(!StringUtils.isEmpty(topNum)){
            options.put("top_num", topNum);
        }
        if(!StringUtils.isEmpty(baikeNum)){
            options.put("baike_num", baikeNum);
        }
        JSONObject jsonObject = client.carDetect(file, options);
        return jsonObject.toString();
    }

}
