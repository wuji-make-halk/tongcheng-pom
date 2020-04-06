package com.micro.pmo.moudle.car.controller;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月29日 
*/

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.car.enu.OrderDepositStatusEnum;
import com.micro.pmo.moudle.car.service.DepositService;
import com.micro.pmo.moudle.car.vo.InputDepositBuyVO;
import com.micro.pmo.moudle.car.vo.InputDepositPayVO;

@Validated
@RestController
@RequestMapping("api-app/car/deposit")
public class DepositApi {

	@Autowired
	private DepositService depositService;

	/**
	 * 买家下单并付定金
	 * 
	 * @return
	 */
	@PostMapping("buyer")
	public Result buyerDeposit(HttpServletRequest request, @Valid @RequestBody InputDepositBuyVO input) {
		input.setIp(CommonUtils.getIpAddr(request));
		return Result.success(depositService.payDeposit(input));
	}

	/**
	 * 继续付定金
	 * 
	 * @return
	 */
	@PostMapping("stick-pay")
	public Result buyerPay(HttpServletRequest request, @Valid @RequestBody InputDepositPayVO input) {
		input.setIp(CommonUtils.getIpAddr(request));
		return Result.success(depositService.stickPayDepositOrder(input));
	}

	
	/**
	 * 申请定金退款
	 * 
	 * @param data
	 * @return
	 */
	@PutMapping("apply-refund")
	public Result buyerRefund(@RequestBody Kv<String, Object> data) {
		String orderId = data.getStr("orderId");
		Integer type = data.getInt("type");
		if (StringUtils.isEmpty(orderId)) {
			throw new PmoException(ResultState.PARAM_ERROR, "订单ID不能为空");
		}

		depositService.applyRefund(orderId,type);

		return Result.success();
	}



	/**
	 * 买家和卖家定金订单确认
	 * 
	 * @return
	 */
	@PutMapping("confirm")
	public Result depositConfirm(@RequestBody Kv<String, Object> data) {

		String orderId = data.getStr("orderId");
		if (StringUtils.isEmpty(orderId)) {
			throw new PmoException(ResultState.PARAM_ERROR, "订单ID不能为空");
		}

		Integer status = data.getInt("status");
		if (status == null) {
			throw new PmoException(ResultState.PARAM_ERROR, "确认状态不能为空");
		}

		if (!status .equals(OrderDepositStatusEnum.ERROR.getValue())  && !status .equals(OrderDepositStatusEnum.AFFIRM.getValue())) {
			throw new PmoException(ResultState.PARAM_ERROR, "状态参数错误");
		}

		depositService.confirmDepositOrder(orderId, status);
		return Result.success();
	}

	/**
	 * 订金列表
	 * 
	 * @return
	 */
	@GetMapping("list")
	public Result depositList(BaseQuery query, @NotNull(message = "订金类型不能为空") Integer type) {
		return Result.success(depositService.listUserOrderDeposit(query, type));
	}
	
	/**
	 * 定金详情
	 * @return
	 */
	@GetMapping("info")
	public Result depositInfo(@NotNull(message = "订单ID不能为空")String orderId,@NotNull( message = "订单类型不能为空")Integer type) {
		return Result.success(depositService.depositInfo(orderId, type));
	}
}
