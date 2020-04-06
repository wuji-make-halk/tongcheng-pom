package com.micro.pmo.commons.utils.sms;

/**
 * 验证码常量
 */
public class SmsConstant {
    /**
     * 短信验证码前缀
     */
    final static String SMS_CACHE_PREFIX = "captcha:sms:";

    /* 标识的错误次数 */
    final static Integer SMS_ID_ERROR_NUMBER = 4;

    /* IP地址的错误次数 */
    final static Integer SMS_IP_ADDRESS_ERROR_NUMBER = 11;

    /* 缓存校验的时间 */
    final static Integer SMS_ID_CACHE_EXPIRE = 86400;
    /**
     * 短信验证码生成出来保存的时间
     */
    final static Integer SMS_CACHE_EXPIRE = 900;

}
