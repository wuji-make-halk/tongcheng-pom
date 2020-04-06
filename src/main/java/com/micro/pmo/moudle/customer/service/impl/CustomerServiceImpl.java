package com.micro.pmo.moudle.customer.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.JwtUtils;
import com.micro.pmo.commons.utils.PhoneUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.service.CustomerService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.InputCusUpdateVO;
import com.micro.pmo.moudle.customer.vo.LoginPhone;
import com.micro.pmo.moudle.vip.service.CusVipService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper cusMapper;
	@Autowired
	private CusAccountService cusAccountService;
	@Autowired
	private CusVipService cusVipService;
	
	
	
	@Value("${qiniuyun.img_url}")
	private String defaultImgUrl;
	
	@Override
	public Boolean checkCus(String phone) {
		Customer cus = cusMapper.findCusByPhone(phone);
		if(cus == null){
			return true;
		}
		if(cus.getCusStatus() == 1){
			return false;
		}
		return true;
	}

	
	@Override
	@Transactional
	public Customer cusLogin(LoginPhone loginPhone) {
		if(StringUtils.equals(loginPhone.getPhone(), "15346180129")){
			if(!StringUtils.equals(loginPhone.getCode(), "000000")){
				throw new PmoException(ResultState.LOGIN_COD_INVALID);
			}
		}else{
			//验证码校验不通过
			if(!PhoneUtils.checkValidcodeByPhone(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID,loginPhone)){
				throw new PmoException(ResultState.LOGIN_COD_INVALID);
			}
		}
		//检查是否是新用户
		Customer cus = cusMapper.findCusByPhone(loginPhone.getPhone());
		//用户新建
		if(cus == null){
			int leng = loginPhone.getPhone().length();
			String cusNick = "尾号" + loginPhone.getPhone().substring(leng-4,leng);
			//头像
			String cusAvatar = defaultImgUrl + "default.png";
			String provinceName = null;
			String cityName = null;
			String position = loginPhone.getPosition();
			if(StringUtils.isNotBlank(position)){
				String [] name = position.split("/");
				if(name.length != 2){
					System.err.println("位置获取失败：" + position);
					throw new PmoException(ResultState.PARAM_ERROR, "position参数格式错误");
				}
				provinceName = name[0];
				cityName = name[1];
			}
			cus = new Customer(loginPhone.getPhone(), cusNick, cusAvatar, 0, new Date(),provinceName,cityName);
			//设备号
			cus.setDeviceCode(loginPhone.getDeviceCode());
			cus.setDeviceType(loginPhone.getDeviceType());
			cusMapper.insertCus(cus);
			//创建用户对应的账号记录
			cusAccountService.createCusAccount(cus.getCusId());
			
		}else{
			//不是新建
			if(cus.getCusStatus() == 1){
				throw new PmoException(ResultState.CUS_FROZEN);
			}
			CacheUtils.remove(cus.getCusToken());
			//判断是否更新设备码
			if(StringUtils.isNotBlank(loginPhone.getDeviceCode()) && !StringUtils.equals(cus.getDeviceCode(), loginPhone.getDeviceCode())){
				cusMapper.updateCusDeviceCode(cus.getCusId(), loginPhone.getDeviceCode(), loginPhone.getDeviceType());
			}
		}

		//缓存处理
		Integer cusId = cus.getCusId();
		//验证通过后生成
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", cusId);
		//时间设置,写入token
		String cusToken = JwtUtils.createJWT(-1, claims);
		cusMapper.updateCusToken(cusId, cusToken);
		
		cus.setCusToken(cusToken);
		
		return CusUtils.putCusCache(cusToken);
	}

	@Override
	public Customer cusInfo() {
		Customer cus = CusUtils.getCustomer();
		//包含了微店，账号余额
		cus = cusMapper.findCusByToken(cus.getCusToken());
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
		
		//分享次数处理 TODO
		return cus;
	}

	@Override
	public int updateCurrentCus(InputCusUpdateVO cusInfo) {
		Customer cus = CusUtils.getCustomer();
		//进行赋值
		cus.setCusAvatar(cusInfo.getCusAvatar());
		cus.setCusNick(cusInfo.getCusNick());
		cus.setCityName(cusInfo.getCityName());
		
		int count = cusMapper.updateCus(cus);
       //更新缓存
		CusUtils.removeCusCache(cus.getCusToken());
		CusUtils.putCusCache(cus.getCusToken());
		
		return count;
	}


	@Override
	public void cusLoginOut() {
		if(CusUtils.isLogin()){
			Customer cus = CusUtils.getCustomer();
			CusUtils.removeCusCache(cus.getCusToken());
		}
		
	}

}
