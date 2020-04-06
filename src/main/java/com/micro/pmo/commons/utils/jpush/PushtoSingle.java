package com.micro.pmo.commons.utils.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.notify.Notify;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.APNPayload.DictionaryAlertMsg;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.base.payload.MultiMedia.MediaType;
import com.gexin.rp.sdk.dto.GtReq;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.google.gson.JsonObject;
import com.micro.pmo.commons.utils.UUIDUtils;

public class PushtoSingle {

	 private static final Logger logger = LoggerFactory.getLogger(PushtoSingle.class); 
	 // 详见【概述】-【服务端接入步骤】-【STEP1】说明，获得的应用配置
    private static String appId = "ducS765oEb5DBsk2DDdcZ9";
    private static String appKey = "7gsLu5tJHR657hr0ftBMJ4";
    private static String masterSecret = "vCMNeU7q7K9wlwkGerWRg3";


    //static String CID = "e0c9f1836fdb60b945663e809fac5f8f";
    // 别名推送方式
    // static String Alias = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    
    private static  IGtPush push  = new IGtPush(host, appKey, masterSecret);;
    

    /***
     * 测试推送
     * @param args
     */
    public static void main(String[] args){
    	String title = "预订收车";
    	String text = "网标价：23.5 万元！成都发布本田雅阁混合动力2.0L旗舰版。请尽快查看>>";
    	
    	JsonObject josn = new JsonObject();
    	josn.addProperty("type", "reserve");
    	josn.addProperty("id", 52);
    	
    	//赵
    	String an = "9e7108a13914f0fc348345ade4c46972";
    	//缪
    	String ios = "631d8729faf5356774adc8e99844b7c9";
    	//刑
    	String an1 = "965b2dc927da5d77112a03fbe37add50";
    	
    	//ios
    	//uniPushOffLine(ios, title, text, josn);
    	//安卓
    	uniPushOffLine(an, title, text, josn);
    	
    	//uniPushOnline(an, title, text, josn.toString());
    	
    	//uniPushOnline(an, title, text, josn.toString());
    }
    /***
     * 离线模板处理，厂商通道
     * @param title
     * @param text
     * @param paramJosn
     * @return
     */
    public static TransmissionTemplate offLinetransmissionTemplate(String title,String text,JsonObject paramJosn){
    	 TransmissionTemplate template = new TransmissionTemplate();
    	 template.setAppId(appId);
    	 template.setAppkey(appKey);
  
         
    	 Notify notify = new Notify();
    	 notify.setTitle(title);
    	 notify.setContent(text);
    	 String intent = "intent:#Intent;action=android.intent.action.oppopush;launchFlags=0x14000000;component=io.dcloud.UNI59F5329/io.dcloud.PandoraEntry;S.UP-OL-SU=true;S.title=收车;S.content=收车;"
    	 		+ "S.payload="+ paramJosn.toString() +";end";
    	 System.out.println(intent);
    	 notify.setIntent(intent);
    	 notify.setType(GtReq.NotifyInfo.Type._intent);
    	 template.set3rdNotifyInfo(notify);//设置第三方通知
    	 
    	   //ios处理
        APNPayload payload = new APNPayload();
        payload.setAutoBadge("+1");
         payload.setContentAvailable(0);
         payload.setSound("default");
         
       	for (String key : paramJosn.keySet()) {
  			//System.out.println(key +":" + paramJosn.get(key).getAsString());
  			payload.addCustomMsg(key, paramJosn.get(key).getAsString());
  		}
         
         DictionaryAlertMsg alertMsg = new DictionaryAlertMsg();
         alertMsg.setTitle(title);
         alertMsg.setBody(text);
         payload.setAlertMsg(alertMsg);
         
         //ios
         template.setAPNInfo(payload);
         
     	template.setTransmissionContent(paramJosn.toString());
     	template.setTransmissionType(1);
     	
    	 return template;
    }
    /***
     * 离线通道推送
     * @param cid
     * @param title
     * @param text
     * @param paramJosn
     */
    public static void uniPushOffLine(String cid,String title,String text,JsonObject paramJosn){
    	TransmissionTemplate template = offLinetransmissionTemplate(title, text, paramJosn);
    	 SingleMessage message = new SingleMessage();
         message.setOffline(true);
         // 离线有效时间，单位为毫秒
         message.setOfflineExpireTime(24 * 3600 * 1000);
         message.setData(template);
         // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
         message.setPushNetWorkType(0);
         Target target = new Target();
         target.setAppId(appId);
         target.setClientId(cid);
         //target.setAlias(Alias);
         IPushResult ret = null;
         try {
             ret = push.pushMessageToSingle(message, target);
         } catch (RequestException e) {
             e.printStackTrace();
             ret = push.pushMessageToSingle(message, target, e.getRequestId());
         }
         if (ret != null) {
             logger.info("推送成功，第三方返回：" + ret.getResponse().toString());
         } else {
       	  logger.error("推送时第三方服务器响应异常");
         }
    }
    
    /***
     * unipush 在线推送
     * @param cid
     * @param title
     * @param text
     * @param penetrateParam
     */
    public static void uniPushOnline(String cid,String title,String text,String penetrateParam){
    	NotificationTemplate template =  onlineNotificationTemplate(title,text,penetrateParam);;
    	   
          SingleMessage message = new SingleMessage();
          message.setOffline(true);
          // 离线有效时间，单位为毫秒
          message.setOfflineExpireTime(24 * 3600 * 1000);
          message.setData(template);
          // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
          message.setPushNetWorkType(0);
          Target target = new Target();
          target.setAppId(appId);
          target.setClientId(cid);
          //target.setAlias(Alias);
          IPushResult ret = null;
          try {
              ret = push.pushMessageToSingle(message, target);
          } catch (RequestException e) {
              e.printStackTrace();
              ret = push.pushMessageToSingle(message, target, e.getRequestId());
          }
          if (ret != null) {
              logger.info("推送成功，第三方返回：" + ret.getResponse().toString());
          } else {
        	  logger.error("推送时第三方服务器响应异常");
          }
    	
    }
    
    
    /***
     * 安卓和ios在线个推
     * @param title
     * @param text
     * @param penetrateParam
     * @return
     */
    public static NotificationTemplate onlineNotificationTemplate(String title,String text,String penetrateParam) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(text);
        // 配置通知栏图标
       // style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("http://img.schyxgl.com/201908201619esc.png");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        //style.setChannel("通知渠道id");
       // style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        //安卓
        template.setStyle(style);
        
 	   
 	   
       //ios处理
       APNPayload payload = new APNPayload();
       payload.setAutoBadge("+1");
       payload.setContentAvailable(1);
       payload.setSound("default");
       DictionaryAlertMsg alertMsg = new DictionaryAlertMsg();
       alertMsg.setTitle(title);
       alertMsg.setBody(text);
       payload.setAlertMsg(alertMsg);
       
       MultiMedia multiMedia = new MultiMedia();
       multiMedia.setResUrl("http://img.schyxgl.com/201908201619esc.png");
       multiMedia.setResType(MediaType.pic);
       multiMedia.setOnlyWifi(false);
       multiMedia.setResId(UUIDUtils.getUUID());
       payload.addMultiMedia(multiMedia);
       //ios
       template.setAPNInfo(payload);
        
      template.setTransmissionType(1);  // 透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
       //template.setTransmissionContent("请输入您要透传的内容");
      template.setTransmissionContent(penetrateParam);
        
        return template;
    }
 
}
