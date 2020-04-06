package com.micro.pmo.moudle.user.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.user.entity.User;

public class UserUtils {

	
	public static final String userToken = "userToken";
	
	/***
	 * 获取user
	 * @param userToken
	 * @return
	 */
	public static User getUser(){
		//获取请求头
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		//找出token
		String token = request.getHeader(userToken);
		if(StringUtils.isBlank(token)){
			throw new PmoException(ResultState.TOKEN_ERROR);
		}
		//获取后台用户，如没有缓存查询数据库
		User user = (User) CacheUtils.get(CacheUtils.USER_CACHE, CacheUtils.USER_CACHE_ID + token);
		if(user == null){
			throw new PmoException(ResultState.LOGIN_INVALID);
		}
		return user;
	}
	
	public static boolean isLogin() {
		//获取请求头
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		//找出token
		String token = request.getHeader(userToken);
		if(StringUtils.isBlank(token)){
			return false;
		}
		//获取后台用户，如没有缓存查询数据库
		User user = (User) CacheUtils.get(CacheUtils.USER_CACHE, CacheUtils.USER_CACHE_ID + token);
		if(user == null){
			return false;
		}
		return true;
	}
	
	/***
	 * 区域经理地址
	 * @return
	 */
	public static String getUserCity(){
		User user = getUser();
		if(user.getUserType() != 0){
			return  user.getCity();
		}
		return null;
	}
}
