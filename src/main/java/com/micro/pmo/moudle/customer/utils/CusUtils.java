package com.micro.pmo.moudle.customer.utils;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.SpringUtils;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.vip.service.CusVipService;

public class CusUtils {

	private static CustomerMapper cusMapper = SpringUtils.getBean(CustomerMapper.class);

	private static CusVipService cusVipService = SpringUtils.getBean(CusVipService.class);
		

	public static final String cusToken = "cusToken";

	/***
	 * 通过token获取
	 * @param token
	 * @return
	 */
	public static Customer findCustomer(String token){
		// 获取用户，如没有缓存查询数据库
		Customer cus = (Customer) CacheUtils.get(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + token);
		if (cus != null) {
			return cus;
		}
		// 缓存用户
		putCusCache(token);
		//再次获取
		cus = (Customer) CacheUtils.get(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + token);
		
		return cus;
	}
	
	/***
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static Customer getCustomer() {
		// 获取请求头Token
		String token = getCusToken();
		if (StringUtils.isBlank(token)) {
			throw new PmoException(ResultState.TOKEN_ERROR);
		}
		return findCustomer(token);
	}

	/***
	 * 删除用户缓存
	 * @param token
	 */
	public static void removeCusCache(String token){
		if(StringUtils.isBlank(token)){
			return ;
		}
		CacheUtils.remove(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + token);
	}
	
	/**
	 * 缓存登录用户
	 * 
	 * @param token
	 * @param cus
	 */
	public static Customer putCusCache(String token) {
		if(StringUtils.isBlank(token)){
			return null;
		}
		//包含了微店，账号余额
		Customer cus = cusMapper.findCusByToken(token);
		if (cus == null) {
			throw new PmoException(ResultState.LOGIN_INVALID);
		}
		//默认为2是平台人员
		if(cus.getCusType() == null){
			cus.setCusType(2);
		}
		if(cus.getSurplusMoney() == null){
			cus.setSurplusMoney(BigDecimal.ZERO);
		}else{
			cus.setSurplusMoney(cus.getSurplusMoney().stripTrailingZeros());
		}
		//是否是vip
		Boolean isVip = cusVipService.cusVipJudge(cus.getCusId());
		cus.setIsVip(isVip);
		CacheUtils.put(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + token,cus);
		return cus;
	}

	/**
	 * 获取当前登录用户id
	 * 
	 * @return
	 */
	public static Integer getCusId() {
		return getCustomer().getCusId();
	}

	/**
	 * 判断当前用户是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		// 获取请求头Token
		String token = getCusToken();
		if (StringUtils.isBlank(token)) {
			return false;
		}
		// 获取用户，如没有缓存查询数据库
		Customer cus = (Customer) CacheUtils.get(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + token);
		if (cus != null) {
			return true;
		}
		cus = cusMapper.findCusByToken(token);
		if (cus == null) {
			return false;
		}
		// 缓存用户
		putCusCache(token);
		return true;
	}

	/**
	 * 获取当前登录用户的token
	 * 
	 * @return
	 */
	private static String getCusToken() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		// 找出token
		String token = request.getHeader(CusUtils.cusToken);
		return token;
	}
}
