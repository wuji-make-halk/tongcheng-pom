package com.micro.pmo.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.micro.pmo.moudle.customer.vo.LoginPhone;


public class PhoneUtils {

	/***
	 * 电话号码校验
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if(StringUtils.isBlank(phone)|| phone.length() != 11){
			return false;
		}
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    //正则校验
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(phone);
	    return m.matches();
	}
	
	/**
	 * 校验验证码
	 * @param cacheType
	 * @param cacheId
	 * @param loginPhone
	 * @return
	 */
	public static boolean checkValidcodeByPhone(String cacheType,String cacheId,LoginPhone loginPhone) {
		Validcode validcode = getValidcode(cacheType,cacheId,loginPhone.getPhone());
		if (validcode == null) return false;
		if (validcode.expiredTime < System.currentTimeMillis()) {
			CacheUtils.remove(cacheType, cacheId + loginPhone.getPhone());
			return false;
		}
		if(validcode.validcode.equals(loginPhone.getCode())){
			CacheUtils.remove(cacheType, cacheId + loginPhone.getPhone());
			return true;
		}
		return false;
	}
	/**
	 * 获取验证码
	 * @param cacheType
	 * @param cacheId
	 * @param phone
	 * @return
	 */
	public static Validcode getValidcode(String cacheType,String cacheId,String phone){
		if(StringUtils.isBlank(phone)){
			return null;
		}
		Validcode code = (Validcode) CacheUtils.get(cacheType, cacheId + phone);
		return code;
	}
	/**
	 * 保存验证码到缓存中
	 * @param cacheType
	 * @param cacheId
	 * @param validcode
	 */
	public static void putValidcode(String cacheType,String cacheId,Validcode validcode){
		CacheUtils.put(cacheType, cacheId + validcode.validPhone, validcode);
	}
}
