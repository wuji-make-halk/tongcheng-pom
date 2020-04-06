package com.micro.pmo.commons.utils.sms;

/**
 * 短信发送接口
 * @author raoBo
 */
public interface SmsObject {
    /**
     * 发送短信
     * @return 发送是否成功
     */
    boolean sendSms(String templateCode, String phone, String... params);

    /**
     * 发送验证码短信
     * @return 发送是否成功
     */
    boolean sendCaptcha();
}
