package com.micro.pmo.moudle.extension.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.extension.entity.Extension;
import com.micro.pmo.moudle.extension.service.PromotionService;
import com.micro.pmo.moudle.extension.vo.ExtensionCarVO;
import com.micro.pmo.moudle.extension.vo.InputExtensionBuyVO;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月5日
 */
@Validated
@RestController
@RequestMapping("api-app/promotion")
public class PromotionApi {

	@Autowired
	private PromotionService promotionService;


	/**
	 * 获取待推广的汽车列表
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("car-list")
	public Result promotionCarList(Integer pageNumKey, Integer pageSizeKey) {
		PageInfo<ExtensionCarVO> result = promotionService.extensionCarList(pageNumKey, pageSizeKey);
		return Result.success(result);
	}

	/**
	 * 推广配置列表
	 * @return
	 */
	@GetMapping("extension-list")
	public Result extensionList() {
		List<Extension> extensionList = promotionService.extensionList();
		return Result.success(extensionList);
	}

	/**
	 * 使用推广赠送权限
	 * @return
	 */
	@PutMapping("use/send")
	public Result usePromotion(@RequestBody Map<String,Integer> map ) {
		Integer carId = map.get("carId");
		if(carId == null){
			return Result.failure(ResultState.PARAM_ERROR, "参数错误：[carId]车辆id不能为空");
		}
		promotionService.usePromotion(carId);
		return Result.success();
	}

	/**
	 * 获取推广和刷新的赠送信息
	 * @return
	 */
	@GetMapping("info/send")
	public Result promotionInfo() {
		return Result.success(promotionService.getPromotionAndFlushSendInfo());
	}

	/**
	 * 购买推广
	 * @param request
	 * @param data
	 * @return
	 */
	@PostMapping("buy")
	public Result buy(HttpServletRequest request, @RequestBody @Valid  InputExtensionBuyVO input ) {
		String ip = CommonUtils.getIpAddr(request);
		input.setIp(ip);
		return Result.success(promotionService.buyPromotion(input));
	}

}
