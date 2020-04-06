package com.micro.pmo.moudle.weixin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.weixin.service.WeixinService;
import com.micro.pmo.moudle.weixin.vo.ParameterModel;
import com.micro.pmo.moudle.weixin.vo.WeixinAuthorization;
/***
 * 微信登录
 * @author raoBo
 *
 */
@Validated
@RestController
@RequestMapping("api-app/weixin")
public class WeixinApi {

	@Autowired
	private WeixinService weixinService;
	
	/***
	 * 获取授权信息
	 * @param parameterModel
	 * @return
	 */
	 @PostMapping("authorization")
	 public Result authorization(@Valid @RequestBody WeixinAuthorization weixinAuthorization) {
		 return weixinService.authorizationService(weixinAuthorization);
	 }
	 
	 /***
	  * 登录
	  * @param parameterModel
	  * @return
	  */
	 @PostMapping("login")
	 public Result weixinLogin(@Valid @RequestBody ParameterModel parameterModel){
		 return weixinService.weixinLoginService(parameterModel);
	 }
	 
	 /***
	  * 获取授权基本头像信息
	  * @param map
	  * @return
	  */
	 @GetMapping("share-authorize")
	 public Result shareAuthorize(@Valid WeixinAuthorization weixinAuthorization){
		 return weixinService.shareAuthorize(weixinAuthorization.getCode(),weixinAuthorization.getEncryptedData(),weixinAuthorization.getIv());
	 }
	 
}
