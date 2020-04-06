package com.micro.pmo.moudle.third.callApi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.third.service.ChaBoShiService;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月15日 
*/
@RestController
@RequestMapping("api-pc/chaboshi")
public class ChaboshiNotifyApi {

	@Autowired
	private ChaBoShiService chaboshiService;
	
	/**
	 * 查博士回调
	 * @param reqData
	 * @return
	 */
	@PostMapping("notify")
	public Kv<String,String> notify(@RequestParam Map<String,String> reqData){
		return chaboshiService.chaboshiNotify(new Kv().set(reqData));
		
	}
	
}
