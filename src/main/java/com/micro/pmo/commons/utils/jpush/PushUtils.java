package com.micro.pmo.commons.utils.jpush;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/***
 * 极光推送
 * @author 82423
 *
 */
public class PushUtils {

    private static final Logger logger = LoggerFactory.getLogger(PushUtils.class);
    
    //TODO 上app应用进行更换
    private final static String appKey = "af5985b336ac8a62e2451d7f";
 
    private final static String masterSecret = "c89a8ba6a9662c13f711d0e2";
 
    private final static Boolean ApnsProduction = false;//上线之后要改为true
 
    private static JPushClient jPushClient = new JPushClient(masterSecret,appKey);
    
    
    /***
     * 推送ios和安卓
     * @param entitys
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return
     */
    public static Boolean jpushToCus(List<JPushEntity> entitys,String notification_title, String msg_title, String msg_content, Map<String,String> extrasparam){
    	List<String> ios = Lists.newArrayList();
    	List<String> android = Lists.newArrayList();
    	//设备类型 1 IOS设备  2 Android设备
    	for (JPushEntity jPushEntity : entitys) {
			if(jPushEntity.getDeviceType() == 1){
				ios.add(jPushEntity.getDeviceCode());
				continue;
			}
			android.add(jPushEntity.getDeviceCode());
		}
    	Boolean result = false;
    	try {
    		//ios推送
        	if(CollectionUtils.isNotEmpty(ios)){
        		 PushPayload pushPayloadios = jpushAliasToIos(ios, notification_title, msg_title, msg_content, extrasparam);
        		 PushResult pushResultIos = jPushClient.sendPush(pushPayloadios);
        		 if(pushResultIos.getResponseCode() == 200){
        			 result = true;
        		 }
        	}	 
        	//android 推送
        	if(CollectionUtils.isNotEmpty(android)){
        		 PushPayload pushPayloadAndroid = pushAliasToAndroid(android, notification_title, msg_title, msg_content, extrasparam);
        		 PushResult pushResultAndroid = jPushClient.sendPush(pushPayloadAndroid);
        		 if(pushResultAndroid.getResponseCode() == 200){
        			 result = true;
        		 }
        	}
        	logger.info("预订收车极光推送成功");
		} catch (APIConnectionException e) {
			e.printStackTrace();
			logger.error("推送失败:"+ e.getMessage());
		} catch (APIRequestException e) {
			e.printStackTrace();
			logger.error("推送连接失败:"+ e.getMessage());
		}
    	return result;
    }
    /***
     * 极光推送到ios
     * @param aliasList
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return
     */
    public static Boolean jPushCusToIos(List<String> aliasList,String notification_title, String msg_title, String msg_content, Map<String,String> extrasparam){
		try {
			PushPayload pushPayloadios = jpushAliasToIos(aliasList, notification_title, msg_title, msg_content, extrasparam);
			PushResult pushResultIos = jPushClient.sendPush(pushPayloadios);
			if(pushResultIos.getResponseCode() == 200){
				return true;
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
			logger.error("ios极光推送失败:"+ e.getMessage());
		} catch (APIRequestException e) {
			e.printStackTrace();
			logger.error("ios推送失败:"+ e.getErrorMessage());
		}
		 return false;
    }
    /**
     * 极光推送到android
     * @param aliasList
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return
     */
    public static Boolean jPushCusToAndroid(List<String> aliasList,String notification_title, String msg_title, String msg_content, Map<String,String> extrasparam){
		 try {
			 PushPayload pushPayloadAndroid = pushAliasToAndroid(aliasList, notification_title, msg_title, msg_content, extrasparam);
			PushResult pushResultAndroid = jPushClient.sendPush(pushPayloadAndroid);
			if(pushResultAndroid.getResponseCode() == 200){
				return true;
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
			logger.error("android极光推送失败:"+ e.getMessage());
		} catch (APIRequestException e) {
			e.printStackTrace();
			logger.error("android极光推送失败:"+ e.getErrorMessage());
		}
		 
		 return false;
    }
    
    
    /**
     * 推送给设备标识参数的用户
     * @param aliasList 别名或别名组
     * @param notification_title 通知内容标题
     * @param msg_title 消息内容标题
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    private static PushPayload jpushAliasToIos(List<String> aliasList,String notification_title, String msg_title, String msg_content, Map<String,String> extrasparam){
    	
    	
        logger.info("----------向ios平台指定用户推送消息中.......");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.ios())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.tag(aliasList))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_title)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("default")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
 
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtras(extrasparam)
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(ApnsProduction)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    
    }
    /**
     * 推送到安卓处理
     * @param aliasList
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return
     */
    private static PushPayload pushAliasToAndroid(List<String> aliasList,String notification_title, String msg_title, String msg_content, Map<String,String> extrasparam){

        logger.info("----------向android平台指定用户推送消息中......");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.tag(aliasList))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extrasparam)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtras(extrasparam)
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(ApnsProduction)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    
    }
}
