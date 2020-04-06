package com.micro.pmo.moudle.vip.admin.conrtroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.vip.admin.service.AdminVipService;
import com.micro.pmo.moudle.vip.entity.Vip;

/**
 * SysVip
 * 
 */
@Validated
@RestController
@RequestMapping("api-pc/vip")
public class AdminVipApi {

	@Autowired
	AdminVipService vipService;

	/**
	 * 根据id修改
	 * 
	 * @param SysVip
	 * @return
	 */
	@PutMapping("update")
	public Result updateVip(@Valid @RequestBody Vip SysVip) {
		vipService.updateVip(SysVip);
		return Result.success();
	}

	/**
	 * 保存VIP配置
	 * 
	 * @param SysVip
	 * @return
	 */
	@PostMapping()
	public Result saveVip(@Valid @RequestBody Vip SysVip) {
		vipService.saveVip(SysVip);
		return Result.success();
	}

	/**
	 * 分页查询vip配置
	 * 
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	@GetMapping("list")
	public Result listSysVip(BaseQuery query) {
		return Result.success(vipService.vipList(query));
	}

	/**
	 * 赠送VIP
	 * 
	 * @param vipId
	 * @param cusId
	 * @return
	 */
	@PostMapping("give")
	public Result giveVip(@RequestBody Kv<String, Integer> data) {

		Integer vipId = data.getInt("vipId");
		if (vipId == null) {
			throw new PmoException(ResultState.PARAM_ERROR, "VIP配置ID不能为空");
		}

		Integer cusId = data.getInt("cusId");
		if (cusId == null) {
			throw new PmoException(ResultState.PARAM_ERROR, "用户id不能为空");
		}
		
		vipService.giveVip(vipId, cusId);
		return Result.success();

	}

}