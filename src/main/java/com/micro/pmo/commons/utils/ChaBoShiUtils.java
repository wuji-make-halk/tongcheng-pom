package com.micro.pmo.commons.utils;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.chaboshi.builder.CBSBuilder;

/**
 * 查博士第三方工具类
 * @Author WangQiLong
 * @Date 2019/7/23 下午5:30
 **/
public class ChaBoShiUtils {
    public static final String USER_ID = "110444";
    private static final String SECRET_KEY = "98ea0a036c788efa16def24d8c47d59d";
    private static final String CALL_BACK_URL="http://app.schyxgl.com/api-pc/chaboshi/notify";

    /**
     * 获取购买报告订单号
     * @param car
     * @return
     */
    public static String buyCarReport(String vin){
        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(USER_ID, SECRET_KEY, true);
        HashMap<String, Object> param = new HashMap<String, Object>();
        
        if(StringUtils.isEmpty(vin)) {
        	return null;
        }
        
        param.put("vin",vin);
        param.put("callbackurl",CALL_BACK_URL);
        String msg = cbsBuilder.sendPost("/report/buy_report ", param);
        return  msg    ;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getReportInfo(String orderId){
        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(USER_ID, SECRET_KEY, true);
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("orderid", orderId);
        
        String string = cbsBuilder.sendGet("/report/get_report_status", params);
        
        Kv result = JSONObject.parseObject(string, Kv.class);
        
        String code = result.getStr("Code");
        
        
        if(code == null ) {
        	return code;
        }
        
        if(!code.equals("1104") && !code.equals("1102")) {
        	return code;
        }
  
        String url= cbsBuilder. getReportUrl ("/new_report/show_reportMobile", params);
        return url;
    }

}
