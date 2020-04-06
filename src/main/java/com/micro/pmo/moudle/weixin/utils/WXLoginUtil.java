package com.micro.pmo.moudle.weixin.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.micro.pmo.moudle.weixin.vo.WxResultModel;

public class WXLoginUtil {
	
	public static void main(String[] args) {
		WxResultModel wrm = getWxResultModelByCode("wxfc34d6ecada37a50", "b5cdc1e0f982c26e5023fc416c0b2cb6", "081Lj5Yo1gYshk04zGYo1EDnYo1Lj5Y1");
		System.out.println(wrm.getOpenid());
	}
	
	/***
	 * 获取微信授权信息
	 * 
	 * @param appId
	 * @param secret
	 * @param code
	 * @return
	 */
	public static WxResultModel getWxResultModelByCode(String appId, String secret, String code) {
		if (StringUtils.isBlank(appId) || StringUtils.isBlank(secret) || StringUtils.isBlank(code)) {
			return null;
		}
		// 微信授权链接
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code="
				+ code + "&grant_type=authorization_code";
		String weixinRes = getWeixinAuthorization(url);
		if (StringUtils.isBlank(weixinRes)) {
			return null;
		}
		// 返回错误
		if (weixinRes.contains("errcode")) {
			return null;
		}
		return JSONObject.parseObject(weixinRes, WxResultModel.class);

	}

	/**
	 * 发起get请求的方法
	 * 
	 * @param url
	 * @return
	 */
	public static String getWeixinAuthorization(String url) {
		String result = "";
		BufferedReader in = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			// Map<String, List<String>> map = conn.getHeaderFields();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			return result;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (Exception e2) {
				return result;
			}
		}
		return result;
	}

}
