package com.micro.pmo.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/**
 * @Author WangQiLong
 * @Date 2019/7/19 下午6:09
 **/
public class SignatureAlgorithm {
    //车300秘钥
    public static final String CHE300KEY = "cK0L15q62D6crxj6";
    //车300秘钥(测试环境)
   // public static final String CHE300KEY = "1U5O86Uw893nN2D3";
    //客户id
    public static final Integer CUSTOMER_ID = 441;
    //客户Id(测试环境)
    //public static final Integer CUSTOMER_ID = 493;
    //购买报告接口）：
    public static final String BUYCARMSG_URL = "http://open.che300.com/api/insurance";
    //获取报告接口
    public static final String GETREPORT_URL = "http://open.che300.com/api/insurance/get_report";
    //回调接口(测试)che 
    public static final String MYURL = "http://app.schyxgl.com/api-pc/car300/insurance-notify";
    //回调接口
//    public static final String MYURL = "";

    //状态码
    //2000 操作成功 ,2001 报告查询中,2002 操作成功无数据,
    //4000 回调地址错误,4001 签名错误,4002 报告生成失败,4003 用户未授权,4004 暂不支持这个品牌,4005 无效订单号,4006 无效 VIN 码,4008 无效发动机号,4009 客户未订购套餐或已失效,4010 客户套餐未生效,4011 客户套餐已过期,4012 客户套餐已用完
    //5000 服务异常,5001 数据维护中,5002 IP 尚未添加白名单,5003 参数传递错误,
    //9000 操作失败(失败描述详见 msg 字段)

    //获取car签名流程
    //    为了确保用户发送的请求不被更改，我们设计了签名算法。该算法基本可以保证 请求是合法者发送且参数没有被修改，但无法保证不被偷窥。该签名方法由参数 名称、密钥、当前日期(年月日)，三部分组成。签名生成规则如下:
    //    A) 参数名称 key 按照 ascii 码从小到大排序，以 key=>value 的形式序列化。
    //    B) 密钥
    //    C) 当前日期(注:月日前有前导 0， 如:2017-02-10)
    //    将以上 ABC 三部分的字符串计算 MD5 值，形成一个 32 位的十六进制(字母小写) 字符串，即为本次请求 sn(签名)的值。
    //    本例 sn(签名)生成 Demo 参考如下: sn=md5(customer_id=00000&engine_no=JS3233&id_no=110110190011002010&vi n=LZWACAGA8BA014187GWWmuOHS9ZzxAUjM2017-02-10) sn:785d5da60c742a3677dfe2bf0021d119

    /**
     * 购买报告
     * @param car
     * @return
     */
    public static Kv<String,String> buyCarMsg(String vin , String engineNumber){
        //构造参数
        HashMap<String,String> params = new HashMap<>();
        params.put("customer_id",CUSTOMER_ID+"");
        params.put("callback_url",MYURL);
        params.put("vin",vin);
        //测试环境下设置车辆引擎号
         params.put("engine_no","9527");
      
        //构造签名
        String signatureAlgorithm = getSN(params);
        //构造请求
        String contentType = "application/x-www-form-urlencoded;";
        HashMap<String,String> headers = Maps.newHashMap();
        HashMap<String,String> body = Maps.newHashMap();
        body.putAll(params);
        body.put("sn",signatureAlgorithm);
       
        try {
        	Result result = HttpClientUtils.postFormData(BUYCARMSG_URL, contentType, headers, body); 
            
            if(result.getCode() == 200) {
            	return 	JSONObject.parseObject(result.getData().toString(), Kv.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Kv.create();
            
        }
        return Kv.create();
    }



    /**
     * 传入参数key-value构造签名
     * @param params
     * @return
     */
    public static String getSN(HashMap<String, String> params) {
        int keysLen = params.size();
        String keysSort = "";
        String[] keys = params.keySet().toArray(new String[params.keySet().size()]);
        //参数根据key排序
        Arrays.sort(keys);
        //组装参数
        int flag = 0 ;
        for(String key:keys){
            flag++;
            if(keysLen==flag){
                keysSort = keysSort+key+"="+params.get(key);
            }else {
                keysSort = keysSort+key+"="+params.get(key)+"&";
            }
        }
        //获取日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        //组装参数+秘钥+日期
        String last = keysSort+CHE300KEY+date;
        return HashKit.md5(last);
    }

    /**
     * 获取报告详情
     * @param orderNO
     * @param vin
     * @return
     */
    public static Result getReportInfo(String orderNO,String vin){
        //请求参数
        HashMap<String,String> params = Maps.newHashMap();
        params.put("customer_id",CUSTOMER_ID+"");
        params.put("order_no",orderNO);
        params.put("vin",vin);
        String sn = getSN(params);
        //请求类型
        String contentType = "application/x-www-form-urlencoded;";
        //请求头
        HashMap<String,String> headers = Maps.newHashMap();
        HashMap<String,String> body = Maps.newHashMap();
        body.put("customer_id",CUSTOMER_ID+"");
        body.put("sn",sn);
        body.put("order_no",orderNO);
        body.put("vin",vin);
        Result result = null;
        try {
            result = HttpClientUtils.postFormData(GETREPORT_URL, contentType, headers, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
