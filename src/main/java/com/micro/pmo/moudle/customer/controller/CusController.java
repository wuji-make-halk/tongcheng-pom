package com.micro.pmo.moudle.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.contants.SysContants;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.PhoneUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.Validcode;
import com.micro.pmo.commons.utils.sms.SmsObject;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.service.CustomerService;
import com.micro.pmo.moudle.customer.vo.InputCusUpdateVO;
import com.micro.pmo.moudle.customer.vo.LoginPhone;

@Validated
@RestController
@RequestMapping("api-app/cus")
public class CusController {

	@Autowired
	private SmsObject smsObject;
	
	@Value("${sms.aliyun.loginTemplate}")
	private String loginTemplate;
	
	@Value("${sys.environment}")
	private String environment;
	
	@Autowired
	private CustomerService customerService;
	
	/***
	 * 发送验证码
	 * @param cusPhone
	 * @return
	 */
	 @GetMapping("sendMsg")
	 public Result sendMsg(@NotNull(message = "电话号码不能为空") 
	 				@Length(min = 11,message = "电话号码位数错误") String phone) {
		 //手机格式的校验
		 if(!PhoneUtils.isPhone(phone)){
			 return Result.failure(ResultState.PHONE_ERROR);
		 }
		 //检查当前电话号码情况
		 if(!customerService.checkCus(phone)){
			 return Result.failure(ResultState.CUS_FROZEN);
		 }
		 //验证码生成
		 Validcode validcode = Validcode.generate(6, 5 * 60 * 1000,phone);
		//保存到缓存中
		 PhoneUtils.putValidcode(CacheUtils.CUS_CACHE,CacheUtils.CUS_CACHE_ID, validcode);
		 System.out.println("验证码："+validcode.validcode);
		 if(SysContants.dev.equals(environment)){
			 //发送短信
			 smsObject.sendSms(loginTemplate, phone,validcode.validcode);
			 return Result.success();
		 }else{
			 return Result.success(validcode.validcode);
		 }
	 }
	 /**
	  * 用户登录
	  * @param loginPhone
	  * @return
	  */
	 @PostMapping("login")
	 public Result cusLogin(@Valid @RequestBody LoginPhone loginPhone){
		 Customer cus  = customerService.cusLogin(loginPhone);
		 return Result.success(cus);
	 }
	 /***
	  * 获取用户基本信息
	  * @return
	  */
	 @GetMapping("info")
	 public Result cusInfo(){
		 Customer cus = customerService.cusInfo();
		 return Result.success(cus);
	 }
	 
	 /**
	  * 更新当前登录用户昵称 和头像
	  * @param cusInfo
	  * @return
	  */
	 @PutMapping("update")
	 public Result cusUpdate(@Valid @RequestBody InputCusUpdateVO cusInfo) {
		 customerService.updateCurrentCus(cusInfo);
		 return Result.success();
	 }
	 
	 /***
	  * 退出登录
	  * @return
	  */
	 @PostMapping("login-out")
	 public Result cusLoginOut(){
		 customerService.cusLoginOut();
		 return Result.success();
	 }
	 
}
