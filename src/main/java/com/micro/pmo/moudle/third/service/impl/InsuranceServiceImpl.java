package com.micro.pmo.moudle.third.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.commons.utils.SignatureAlgorithm;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.mapper.OrderMapper;
import com.micro.pmo.mapper.OrderThirdApiMapper;
import com.micro.pmo.mapper.ThirdApiResultMapper;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.enu.OrderPayTypeEnum;
import com.micro.pmo.moudle.order.enu.OrderStatusEnum;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.order.vo.PayInfoVO;
import com.micro.pmo.moudle.third.entity.OrderThird;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.enu.ThirdResEnum;
import com.micro.pmo.moudle.third.enu.ThirdTypeEnum;
import com.micro.pmo.moudle.third.service.InsuranceService;
import com.micro.pmo.moudle.third.service.ThirdApiResultService;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

/**
 * @Author WangQiLong
 * @Date 2019/7/22 下午3:58
 **/
@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private OrderThirdApiMapper orderThirdApiMapper;

	@Autowired
	private ThirdApiResultService thirdApiResultService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ThirdApiResultMapper thirdApiResultMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CounterConfigMapper counterConfigMapper;

	@Autowired
	private CusCounterService cusCounterService;

	@Override
	public ThirdApiResult getInsuranceById(Integer id) {
		return thirdApiResultMapper.getThirdApiResultById(id);
	}

	@Override
	public Kv<String, Object> insuranceNotify(Kv<String, Object> data) {

		Kv<String, Object> kv = Kv.by("code", 2000);
		kv.set("msg", "通知成功").set("data", new ArrayList<String>());
		
 
		Integer retCode = data.getInt("ret_code");
		String orderId = data.getStr("order_no");

		Integer resultStatus = ThirdResEnum.SUCCESS.getStatus();

		ThirdApiResult apiResult = thirdApiResultMapper.getThirdApiResult(new ThirdApiResult().setOrderId(orderId));

		String reportUrl = null;

		if (apiResult == null) {
			return kv;
		}

		ThirdApiResult newThirdApiResult = getReportInfoByOrder(apiResult, apiResult.getBuyType());

		if (retCode != 2000) {
			if (retCode == 2001) {
				// 没有数据
				resultStatus = ThirdResEnum.NO_DATA.getStatus();
			} else {
				// 查询失败
				resultStatus = ThirdResEnum.ERROR.getStatus();
			}
		} else {

			// 如果返回数据不为空则表示查询成功 否则为查询失败
			if (!StringUtils.isEmpty(newThirdApiResult.getResult())) {
				reportUrl = newThirdApiResult.getResult();
			} else {
				resultStatus = ThirdResEnum.ERROR.getStatus();
			}

		}

		apiResult.setResStatus(resultStatus);
		apiResult.setResCode(retCode);
		apiResult.setResult(reportUrl);

		// 更新接口结果状态
		thirdApiResultMapper.updateThirdApiResultById(apiResult);

		// 如果为空则进行退款操作
		if (StringUtils.isEmpty(reportUrl)) {
			if (apiResult.getBuyType().equals(1)) {
				// 如果是赠送次数则增加一个次数
				cusCounterService.addCounter(apiResult.getCusId(), "", CounterTypeEnum.insurance, 1);
			} else {
				Order order = orderMapper.getOrderById(apiResult.getPayOrderId());
				order.setProductName(CounterTypeEnum.insurance.getConfigName());
				order.setPayType("第三方查询");
				order.setRefundAmount(order.getOrderMoney());
				orderService.refund(order);
			}
		}

		return kv;
	}

	@Transactional
	@Override
	public Kv<String, Object> useSend(String vin) {
		// 减少一次赠送次数
		cusCounterService.reduceSend(CounterTypeEnum.insurance, 1);

		// 创建一个没有订单的订单ID
		OrderThird orderThird = new OrderThird().setVin(vin).setOrderThirdId(UUIDUtils.createSerialNum())
				.setCusId(CusUtils.getCusId());
 
		//待删除，测试使用 TODO
		/*ThirdApiResult test = new ThirdApiResult(3, vin, "http://customer.ceshi.che300.com/ins-report?cid=493&order_id=12818&sn=574a001c159ac88bce5cdd77a75dd4cb", 
				new Date(),ThirdTypeEnum.CAR300_INSURANCE, "201908151444580001", "D815515053293872", 2000, 0, 2);
		return Kv.by("result", test);*/
 
		// 购买报告
		ThirdApiResult result = buyInsurance(orderThird, 1);

		// 保存记录 以及 搜索记录
		thirdApiResultService.saveApiResultAndSearchRecord(result);

		Integer apiResultStatus = result.getResStatus();

		// 如果没有数据以及查询失败则进行退款
		if (ThirdResEnum.ERROR.getStatus().equals(apiResultStatus)
				|| ThirdResEnum.NO_DATA.getStatus().equals(apiResultStatus)) {
			// TODO 查询退款
			cusCounterService.addCounter(result.getCusId(), "", CounterTypeEnum.insurance, 1);
		}

		return Kv.by("result", result);
	}

	@Override
	@Transactional
	public PayInfoVO payInsurance(ThirdBuyVO input) {
		CounterConfig config = counterConfigMapper.getBuyInfoByType(CounterTypeEnum.insurance);
		//TODO 测试使用，待删除
		/*PayInfoVO payInfoVO = new PayInfoVO(OrderPayModeEnum.WX_PAY_JSAPI,OrderPayTypeEnum.THIRD_INSURE);
		payInfoVO.setOrderId("20190814172437131");
		return payInfoVO;*/
		
		Customer cus = CusUtils.getCustomer();

		BigDecimal orderMoney = config.getConfigPrice().multiply(new BigDecimal(input.getNum()));

		// 如果是账号支付则订单状态为已支付，否则为待支付
		OrderStatusEnum status = input.isAccountPay() ? OrderStatusEnum.SUCCESS : OrderStatusEnum.UNPAID;
		Order order = new Order(orderMoney, OrderPayTypeEnum.THIRD_INSURE.getType(), cus.getCusId(), status.getStatus(),
				input.getIp(), input.getPayMode().getMode(), cus.getOpenId(), config.getConfigType().getConfigName(),
				"查询车辆保险");

		// 创建订单
		PayInfoVO orderResult = orderService.createOrder(order);

		OrderThird orderThird = new OrderThird(order.getOrderId(), cus.getCusId(), config.getConfigPrice(),
				config.getConfigPrice(), input.getVin());

		orderThirdApiMapper.insertOrderThirdApi(orderThird);

		// 如果是账号支付则判定为成功
		if (order.isAccountPay()) {
			paySuccess(order.getOrderId());
		}

		orderResult.setOrderId(order.getOrderId());

		return orderResult;
	}

	@Override
	public ThirdApiResult getInsuranceByVin(String vin) {
		Integer cusId = CusUtils.getCusId();
		return thirdApiResultService.getLastThirdApiResult(cusId, vin, ThirdTypeEnum.CAR300_INSURANCE);
	}

	@Transactional
	public void paySuccess(String orderId) {

		OrderThird orderThird = orderThirdApiMapper.getOrderThirdApiById(orderId);

		// 获取记录
		ThirdApiResult thirdApiResult = buyInsurance(orderThird, 0);
		thirdApiResult.setPayOrderId(orderId);
		// 保存记录 以及 搜索记录
		thirdApiResultService.saveApiResultAndSearchRecord(thirdApiResult);

		Integer apiResultStatus = thirdApiResult.getResStatus();

		// 如果没有数据以及查询失败则进行退款
		if (ThirdResEnum.ERROR.getStatus().equals(apiResultStatus)
				|| ThirdResEnum.NO_DATA.getStatus().equals(apiResultStatus)) {
			// TODO 查询退款

			if (thirdApiResult.getBuyType().equals(1)) {
				// 次数加1
				cusCounterService.addCounter(thirdApiResult.getCusId(), "", CounterTypeEnum.insurance, 1);
			} else {
				Order order = orderMapper.getOrderById(orderId);
				order.setProductName(CounterTypeEnum.insurance.getConfigName());
				order.setPayType("第三方查询");
				order.setRefundAmount(order.getOrderMoney());
				orderService.refund(order);
			}
		}
	}

	/**
	 * 
	 * 
	 * @param car
	 * @return
	 */
	public ThirdApiResult buyInsurance(OrderThird orderThird, Integer buyType) {

		String vin = orderThird.getVin();
		Integer cusId = orderThird.getCusId();

		ThirdApiResult thirdApiResult = thirdApiResultService.getLastThirdApiResult(vin,
				ThirdTypeEnum.CAR300_INSURANCE);

		// 如果已经有记录了则直接返回
		if (thirdApiResult != null && thirdApiResult.getResStatus().equals(ThirdResEnum.SUCCESS.getStatus())) {
			thirdApiResult.setCusId(cusId);
			thirdApiResult.setResultId(null);
			thirdApiResult.setGmtCreate(new Date());
			return thirdApiResult;
		}

		String payOrderId = orderThird.getOrderThirdId();

		// 获取购买报告订单(取出订单编号)
		Kv<String, String> kv = SignatureAlgorithm.buyCarMsg(orderThird.getVin(), null);

		Integer resCode = kv.getInt("code");

		// 如果报告订单获取失败则判定为查询失败
		if (resCode != 2000) {

			String orderNo = "";
			String result = "";
			
			thirdApiResult = new ThirdApiResult(cusId, vin, result, new Date(), ThirdTypeEnum.CAR300_INSURANCE, orderNo,
					payOrderId, resCode, ThirdResEnum.ERROR.getStatus() , buyType);

			return thirdApiResult;
		}

		JSONObject kvData = kv.getAs("data");

		//获取第三方请求订单ID
		String orderNo = kvData.getString("order_no");

		thirdApiResult = new ThirdApiResult()
				.setCarVin(vin)
				.setCusId(cusId)
				.setOrderId(orderNo)
				.setPayOrderId(payOrderId);
		return getReportInfoByOrder(thirdApiResult, buyType);
	}

	private ThirdApiResult getReportInfoByOrder(ThirdApiResult thirdApiResultData, Integer buyType) {
		//车架号
		String vin = thirdApiResultData.getCarVin();
		//用户ID
		Integer cusId = thirdApiResultData.getCusId();

		String orderNo = thirdApiResultData.getOrderId();
		
		String payOrderId = thirdApiResultData.getPayOrderId();

		// 获取报告详情
		Result reportInfo = SignatureAlgorithm.getReportInfo(orderNo, vin);

		// 报告详情
		Kv<String, Object> repostRes = JSONObject.parseObject(reportInfo.getData().toString(), Kv.class);

		Integer repostCode = repostRes.getInt("code");

		// 处理非正常返回的情况
		if (repostCode == null || !repostCode.equals(2000)) {
			// 0 为查询成功，1为查询失败，2为等待响应 ,3为没有数据
			Integer apiResultStatus = getApiResultStatus(repostCode);

			ThirdApiResult thirdApiResult = new ThirdApiResult(cusId, vin, null, new Date(),
					ThirdTypeEnum.CAR300_INSURANCE, orderNo, payOrderId, repostCode, apiResultStatus, buyType);

			return thirdApiResult;
		}

		JSONObject repostResDaat = repostRes.getAs("data");

		String reportUrl = repostResDaat.getString("report_url");
		// 创建成功的记录
		ThirdApiResult thirdApiResult = new ThirdApiResult(cusId, vin, reportUrl, new Date(),
				ThirdTypeEnum.CAR300_INSURANCE, orderNo, payOrderId, repostCode, ThirdResEnum.SUCCESS.getStatus(), buyType);

		return thirdApiResult;
	}

	private Integer getApiResultStatus(Integer repostCode) {
		Integer apiResultStatus;
		if (repostCode.equals(2002)) {
			// 没有数据
			apiResultStatus = ThirdResEnum.NO_DATA.getStatus();
		} else if (repostCode.equals(2001)) {
			// 报告查询中
			apiResultStatus = ThirdResEnum.IN_QUERY.getStatus();
		} else {
			// 查询失败
			apiResultStatus = ThirdResEnum.ERROR.getStatus();
		}
		return apiResultStatus;
	}

	@Override
	public ThirdApiResult getInsuranceByPayOrderId(String payOrderId) {
		//TODO 测试使用，待删除
		/*ThirdApiResult test = new ThirdApiResult(3, "LGBN22E28AY001155", "http://customer.ceshi.che300.com/ins-report?cid=493&order_id=12818&sn=574a001c159ac88bce5cdd77a75dd4cb", 
				new Date(),ThirdTypeEnum.CAR300_INSURANCE, "201908151444580001", "D815515053293872", 2000, 0, 2);
		return test;*/
		ThirdApiResult query = new ThirdApiResult().setPayOrderId(payOrderId);

		return thirdApiResultMapper.getThirdApiResult(query);
	}

	@Override
	public PageInfo<ThirdApiResult> page(ThirdQuery query) {

		query.setCusId(CusUtils.getCusId());

		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());

		query.setResultType(ThirdTypeEnum.CAR300_INSURANCE.getCode());

		List<ThirdApiResult> list = thirdApiResultMapper.thirdApiResultList(query);

		return new PageInfo<ThirdApiResult>(list);
	}

}
