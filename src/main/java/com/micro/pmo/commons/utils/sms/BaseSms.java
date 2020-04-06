package com.micro.pmo.commons.utils.sms;

public abstract class BaseSms implements SmsObject {

    @Override
    public abstract boolean sendSms(String templateCode, String phone, String... params);

    /**
     * 异步发送
     * @param templateCode
     * @param phone
     * @param params
     */
    public void asyncSendSms(String templateCode, String phone, String... params) {
    	new Thread(()-> {
    		sendSms(templateCode, phone, params);
    	}).start();;
    }
    
    @Override
    public boolean sendCaptcha() {
        return false;
    }
}
