package com.micro.pmo.moudle.weixin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.JwtUtils;
import com.micro.pmo.commons.utils.PhoneUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.LoginPhone;
import com.micro.pmo.moudle.weixin.utils.WXLoginUtil;
import com.micro.pmo.moudle.weixin.utils.WXUtil;
import com.micro.pmo.moudle.weixin.vo.ParameterModel;
import com.micro.pmo.moudle.weixin.vo.ResultModel;
import com.micro.pmo.moudle.weixin.vo.WeixinAuthorization;
import com.micro.pmo.moudle.weixin.vo.WexinVo;
import com.micro.pmo.moudle.weixin.vo.WxResultModel;

/***
 * 微信
 * @author raoBo
 *
 */
@Service
public class WeixinService {

	
	@Value("${wx.miniapp.appId}")
	private String appId;
	
	@Value("${wx.miniapp.appSecret}")
	private String appSecret;
	
	@Autowired
	private CustomerMapper cusMapper;
	
	
	@Autowired
	private CusAccountService cusAccountService;
	
	/***存入缓存**/
	private static final String authorizationCode = "authorization_";
	
	/***
	 * 授权信息
	 * @return
	 */
	public Result authorizationService(WeixinAuthorization weixinAuthorization){
		WxResultModel model = WXLoginUtil.getWxResultModelByCode(appId,appSecret,weixinAuthorization.getCode());
		if(model == null){
			return Result.failure(ResultState.LOGIN_COD_INVALID,"授权未获取到信息");
		}
		WexinVo wexinVo  = WXUtil.decrypt(weixinAuthorization.getEncryptedData(), model.getSession_key(), weixinAuthorization.getIv(), "UTF-8");
		if(wexinVo == null){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR,"获取微信用户信息失败");
		}
		/*WxResultModel model = new WxResultModel();
		model.setOpenid("2558855511");
		model.setUnionid("ot28N6Lguie-dS-4UF50SHN641kY23");*/
		//获取unionId 进行判断处理 切换账号时不同
		Customer loginCus = null;
		if(CusUtils.isLogin()){
			loginCus =  CusUtils.getCustomer();
		}
		
		Customer oldCusA = isLoginCus(wexinVo.getOpenId(), wexinVo.getUnionId(),loginCus);
		//有包含当前登录用户
		if(oldCusA != null){
			 ResultModel resultModel = new ResultModel(true, oldCusA.getCusToken(), oldCusA,model.getOpenid());
			 return Result.success(resultModel);
		}
		//没有匹配出来,又是登录状态
		if(loginCus != null){
			cusMapper.updateCusOpenId(loginCus.getCusId(), model.getOpenid(), wexinVo.getUnionId());
			 ResultModel resultModel = new ResultModel(true, loginCus.getCusToken(), loginCus,model.getOpenid());
			return Result.success(resultModel);
		}
		
		//数据开始处理，保存缓存 对ResultModel处理 TODO
		ResultModel resultModel = new ResultModel(false, model.getOpenid());
		//加入缓存数据
		CacheUtils.put(authorizationCode + weixinAuthorization.getCode(), model);
		return Result.success(resultModel);
	}
	
	/***
	 * 判断当前登录用户是否绑定
	 * @param openId
	 * @param unionId
	 * @return
	 */
	private Customer isLoginCus(String openId,String unionId,Customer loginCus){
		//用户未登录不用匹配
		if(loginCus == null){
			return null;
		}
		List<Customer> cusList = cusMapper.getCusByOpenIdAndUnionId(openId,unionId);
		if(CollectionUtils.isEmpty(cusList)){
			return null;
		}
		//登录用户存在
		for (Customer customer : cusList) {
			//有当前登录用户
			if(loginCus.getCusId() == customer.getCusId()){
				return loginCus;
			}
		}
		return null;
		
	}
	/**
	 * 更新openId
	 * @param cus
	 * @param openId
	 * @param unionId
	 * @return
	 */
	@Transactional
	private ResultModel bindWeinxin(Customer cus,String openId,String unionId){
		cusMapper.updateCusOpenId(cus.getCusId(), openId, unionId);
		if(StringUtils.isBlank(cus.getCusToken())){
			//生成新的token
			//验证通过后生成
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("id", cus.getCusId());
			//时间设置
			String cusToken = JwtUtils.createJWT(-1, claims);
			cusMapper.updateCusToken(cus.getCusId(), cusToken);
			cus.setCusToken(cusToken);
			//写入token
			cusMapper.updateCusToken(cus.getCusId(), cusToken);
			//新的token保存
			 CusUtils.putCusCache(cusToken);
		}
		cus = CusUtils.findCustomer(cus.getCusToken());
		return new ResultModel(true, cus.getCusToken(), cus,openId);
	}
	/***
	 * 写入用户信息
	 * @param wexinVo
	 * @param parameterModel
	 * @return
	 */
	@Transactional
	private Customer insertCustomer(WexinVo wexinVo,ParameterModel parameterModel){
		//用户信息
		Customer cus = new Customer(parameterModel.getCusPhone(), wexinVo.getNickName(), wexinVo.getAvatarUrl(), 0, new Date(),null,null);
		cus.setOpenId(wexinVo.getOpenId());
		cus.setUnionId(wexinVo.getUnionId());
		cusMapper.insertCus(cus);
		//缓存更新
		Integer cusId = cus.getCusId();
		//验证通过后生成
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", cusId);
		//时间设置
		String cusToken = JwtUtils.createJWT(-1, claims);
		cusMapper.updateCusToken(cusId, cusToken);
		//创建用户对应的账号记录
		cusAccountService.createCusAccount(cusId);
		//缓存写入
		return CusUtils.putCusCache(cusToken);
	}
	
	/***
	 * 微信绑定登录
	 * @return
	 */
	public Result weixinLoginService(ParameterModel parameterModel){
		Object obj = CacheUtils.get(authorizationCode + parameterModel.getCode());
		if(obj == null){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR,"请先授权");
		}
		//验证码校验不通过
		LoginPhone login = new LoginPhone(parameterModel.getCusPhone(), parameterModel.getPhoneCode());
		//验证码不通过
		if(!PhoneUtils.checkValidcodeByPhone(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID,login)){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR);
		}
		WxResultModel model = (WxResultModel)obj;
		WexinVo wexinVo  = WXUtil.decrypt(parameterModel.getEncryptedData(), model.getSession_key(), parameterModel.getIv(), "UTF-8");
		if(wexinVo == null){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR,"获取微信用户信息失败");
		}
		//通过电话号码找用户
		//检查是否是新用户
		Customer cus = cusMapper.findCusByPhone(login.getPhone());
		//删除缓存
		CacheUtils.remove(authorizationCode + parameterModel.getCode());
		//新建用户
		if(cus == null){
			cus = insertCustomer(wexinVo, parameterModel);
			ResultModel resultModel = new ResultModel(true, cus.getCusToken(), cus, cus.getOpenId());
			return Result.success(resultModel);
		}
		//更新数据
		return Result.success(bindWeinxin(cus, wexinVo.getOpenId(), wexinVo.getUnionId()));
	}
	/***
	 * 
	 * @param code
	 * @return
	 */
	public Result shareAuthorize(String code,String encryptedData,String iv){
		WxResultModel model = WXLoginUtil.getWxResultModelByCode(appId,appSecret,code);
		if(model == null){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR,"授权未获取到信息");
		}
		WexinVo wexinVo  = WXUtil.decrypt(encryptedData, model.getSession_key(), iv, "UTF-8");
		if(wexinVo == null){
			return Result.failure(ResultState.WEIXIN_AUTHORIZE_ERROR,"获取微信用户信息失败");
		}
		Map<String,String> map = Maps.newHashMap();
		map.put("nickName", wexinVo.getNickName());
		map.put("avatarUrl", wexinVo.getAvatarUrl());
		return Result.success(map);
	}
}
