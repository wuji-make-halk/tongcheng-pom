package com.micro.pmo.moudle.third.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.AESUtil;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.customer.utils.CusUtils;

@RestController
@RequestMapping("api-app/open/easy")
public class EasyDriveApi {

	@GetMapping("driver-url")
	public Result easyDriveUrl(){
		Integer userId = CusUtils.getCusId();
		String content = userId + AESUtil.user_from;
		String token = AESUtil.encrypt(content, AESUtil.user_password);
		
		//https://finegw.cx580.com/merauthorization?user_id={user_id}&user_from={user_from}&token={token}
		String url = "https://finegw.cx580.com/merauthorization?user_id="+userId+"&user_from="+ AESUtil.user_from+"&token="+token;
		return Result.success(url);
	}
}
