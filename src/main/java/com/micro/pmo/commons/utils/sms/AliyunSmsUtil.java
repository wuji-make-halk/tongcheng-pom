package com.micro.pmo.commons.utils.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;

@Component
public class AliyunSmsUtil extends BaseSms {

    @Value("${sms.aliyun.key}")
    private String key;


    @Value("${sms.aliyun.secret}")
    private String secret;


    @Value("${sms.aliyun.sign-name}")
    private String signName;

    

    @Override
    public boolean sendSms(String templateCode, String phone, String... params){

        DefaultProfile profile = DefaultProfile.getProfile("default", key, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "四川汇原鑫");
        request.putQueryParameter("TemplateCode", templateCode);
        JSONObject jsonObject = new JSONObject();
        if(params != null &&  params.length > 0){
        	for (int i = 1; i <= params.length; i++) {
				jsonObject.put("param" + i, params[i-1]);
			}
        }
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());

        CommonResponse response = null;
		try {
			response = client.getCommonResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PmoException(ResultState.SEND_MSG_ERROR);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PmoException(ResultState.SEND_MSG_ERROR);
		}
        JSONObject object = JSONObject.parseObject(response.getData());
        if (object.getString("Code").equals("OK")) {
            return true;
        }
        throw new PmoException(ResultState.SEND_MSG_ERROR);
        
    }

    @Override
    public boolean sendCaptcha() {
        return false;
    }
}
