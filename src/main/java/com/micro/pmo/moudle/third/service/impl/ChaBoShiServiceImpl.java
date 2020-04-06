package com.micro.pmo.moudle.third.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.ChaBoShiUtils;
import com.micro.pmo.commons.utils.FastJSONUtils;
import com.micro.pmo.commons.utils.Kv;
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
import com.micro.pmo.moudle.third.service.ChaBoShiService;
import com.micro.pmo.moudle.third.service.ThirdApiResultService;
import com.micro.pmo.moudle.third.vo.ThirdBuyVO;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

/**
 * @Author WangQiLong
 * @Date 2019/7/22 下午3:58
 **/
@Service
public class ChaBoShiServiceImpl implements ChaBoShiService {
 

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
  
	@Transactional
	@Override
	public Kv<String, Object> chaboshiNotify(Kv<String, Object> data) {

		Kv<String, Object> kv = Kv.by("code", 0);
		kv.set("message", "success");
		
		Integer result = data.getInt("result");
		String orderId = data.getStr("orderid");

		Integer resultStatus = ThirdResEnum.SUCCESS.getStatus();

		ThirdApiResult apiResult = thirdApiResultMapper.getThirdApiResult(new ThirdApiResult().setOrderId(orderId));

		String reportUrl = null;

		if (apiResult == null) {
			return kv;
		}
		
		//获取
		OrderThird orderThird = orderThirdApiMapper.getOrderThirdApiById(apiResult.getOrderId());

		if (orderThird == null) {
			return kv;
		}

		ThirdApiResult newThirdApiResult = getReport(orderThird, orderId, apiResult.getBuyType());

		if (result != 1) {
			 // 查询失败
			resultStatus = ThirdResEnum.ERROR.getStatus();
			 
		} else {
			if (apiResult.getBuyType().equals(1)) {
				//如果是赠送次数则增加一个次数
				cusCounterService.addCounter(apiResult.getCusId(), "", CounterTypeEnum.maintenance, 1);
			}else {
				// 如果返回数据不为空则表示查询成功 否则为查询失败
				if (!StringUtils.isEmpty(newThirdApiResult.getResult())) {
					reportUrl = newThirdApiResult.getResult();
				} else {
					resultStatus = ThirdResEnum.ERROR.getStatus();
				}
			}
		}

		apiResult.setResStatus(resultStatus);
		apiResult.setResCode(result);
		apiResult.setResult(reportUrl);

		// 更新接口结果状态
		thirdApiResultMapper.updateThirdApiResultById(apiResult);

		// 如果为空则进行退款操作
		if (StringUtils.isEmpty(reportUrl)) {
			Order order = orderService.getOrderByOrderId(orderThird.getOrderThirdId());
			order.setProductName("4s维保");
			order.setPayType("第三方查询");
			order.setRefundAmount(order.getOrderMoney());
			orderService.refund(order); 
		}

		return kv.set("msg", "通知成功");
	}

	
	@Override
	@Transactional
	public PayInfoVO payChaboshi(ThirdBuyVO input) {
		CounterConfig config =counterConfigMapper.getBuyInfoByType(CounterTypeEnum.maintenance);
		
		Customer cus = CusUtils.getCustomer();
		
		BigDecimal orderMoney = config.getConfigPrice().multiply(new BigDecimal(input.getNum()));
		
		//如果是账号支付则订单状态为已支付，否则为待支付
		OrderStatusEnum status = input.isAccountPay()?OrderStatusEnum.SUCCESS: OrderStatusEnum.UNPAID;	
		Order order =  new Order(orderMoney, OrderPayTypeEnum.THIRD_REPAIRE.getType(), 
				cus.getCusId(), status.getStatus(),input.getIp(),
				input.getPayMode().getMode(),
				cus.getOpenId(),config.getConfigType().getConfigName(),"查询车辆保险");
		
		//创建订单
		PayInfoVO orderResult = orderService.createOrder(order);
		
		OrderThird orderThird = new OrderThird(order.getOrderId() , cus.getCusId(), config.getConfigPrice(), config.getConfigPrice(), input.getVin());
	
		
		orderThirdApiMapper.insertOrderThirdApi(orderThird);
	 
		//如果是账号支付则判定为成功
		if(order.isAccountPay()) {
			paySuccess(order.getOrderId());
		}
		
		orderResult.setOrderId(order.getOrderId());
		
		
		return orderResult;
	}
 
	
	@Transactional
	@Override
	public void paySuccess(String orderId) {
		
		OrderThird orderThird = orderThirdApiMapper.getOrderThirdApiById(orderId);

		//获取
		ThirdApiResult thirdApiResult =   buyData(orderThird , 0); 
		thirdApiResult.setPayOrderId(orderId);
		// 保存记录 以及 搜索记录
		thirdApiResultService.saveApiResultAndSearchRecord(thirdApiResult);	
		
		Integer apiResultStatus = thirdApiResult.getResStatus();
		
		//如果没有数据以及查询失败则进行退款
		if(ThirdResEnum.ERROR.getStatus().equals(apiResultStatus)||ThirdResEnum.NO_DATA.getStatus().equals(apiResultStatus)) {
			//退款
			Order order = orderMapper.getOrderById(orderId);
			order.setProductName("4s维保");
			order.setPayType("第三方查询");
			order.setRefundAmount(order.getOrderMoney());
			orderService.refund(order); 
		} 
	}
	
	/**
	 * 使用赠送
	 * @return
	 */
	@Override
	@Transactional
	public ThirdApiResult useSend(String vin) {
		
		CounterConfig config = cusCounterService.reduceSend(CounterTypeEnum.maintenance, 1);
		
		Integer cusId = CusUtils.getCusId();
		
		
		OrderThird orderThird = new OrderThird(null, cusId, config.getConfigPrice(), config.getConfigPrice(), vin);
	 
		ThirdApiResult	thirdApiResult = buyData(orderThird , 1);
		 
	 
		// 保存记录 以及 搜索记录
		thirdApiResultService.saveApiResultAndSearchRecord(thirdApiResult);	
		
		Integer apiResultStatus = thirdApiResult.getResStatus();
 
		if(ThirdResEnum.ERROR.getStatus().equals(apiResultStatus)||ThirdResEnum.NO_DATA.getStatus().equals(apiResultStatus)) {
			//如果没有数据以及查询失败则增加赠送次数
			cusCounterService.addCounter(cusId, "", CounterTypeEnum.maintenance, 1);
		} 
		
		return thirdApiResult;
	}
 
	/**
	 * 
	 * 
	 * @param car
	 * @return
	 */
	public ThirdApiResult buyData(OrderThird orderThird,Integer buyType) {
 
		String vin = orderThird.getVin();
		Integer cusId = orderThird.getCusId();
	 
		String payOrderId = orderThird.getOrderThirdId();
	
		ThirdApiResult thirdApiResult = thirdApiResultService.getLastThirdApiResult(vin,ThirdTypeEnum.CHA_BO_SHI);
		
		if(thirdApiResult != null && thirdApiResult.getResStatus().equals(ThirdResEnum.SUCCESS.getStatus())) {
			thirdApiResult.setCusId(orderThird.getCusId());
			thirdApiResult.setPayOrderId(payOrderId);
			thirdApiResult.setResultId(null);
			thirdApiResult.setGmtCreate(new Date());
			return thirdApiResult;
		}
		
		String buyInfo = ChaBoShiUtils.buyCarReport(orderThird.getVin());
        JSONObject buyInfoJson = JSONObject.parseObject(buyInfo);
        Map<String, String> buyMap = FastJSONUtils.toMap(buyInfoJson);
        Kv<String,String> kv =  new Kv<String, String>().set(buyMap);
        Integer resCode =  kv.getInt("Code");
        
        //如果响应码不正确则直接返回错误
        if(resCode == null ||!resCode.equals(0)){
        	 thirdApiResult = new ThirdApiResult(cusId, vin, null, new Date(), ThirdTypeEnum.CHA_BO_SHI,
					null, payOrderId, resCode, ThirdResEnum.ERROR.getStatus(),buyType);
            return thirdApiResult;
        } 
        String orderId = buyMap.get("orderId");
        
        
        return getReport( orderThird , orderId , buyType);
	}


	/**
	 * 获取报告信息
	 * @param orderThird
	 * @param orderId
	 * @param buyType
	 * @return
	 */
	private ThirdApiResult getReport(OrderThird orderThird,String orderId ,Integer buyType) {
		
		String vin = orderThird.getVin();
		Integer cusId = orderThird.getCusId();
	 
		String payOrderId = orderThird.getOrderThirdId();
		
		ThirdApiResult thirdApiResult;
		//获取报告详情
        String reportUrl = ChaBoShiUtils.getReportInfo(orderId);
        
    
        if(StringUtils.isEmpty(reportUrl) || !reportUrl.startsWith("http")) {
        	
        	ThirdResEnum thirdResEnum = ThirdResEnum.ERROR;
    		
        	//设置为查询中
    		if(StringUtils.equals(reportUrl, "1102")) {
    			thirdResEnum = ThirdResEnum.IN_QUERY;
    		}
        	
        	Integer resCode = null;
    		try {
    			Integer.parseInt(reportUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
        	thirdApiResult = new ThirdApiResult(cusId, vin, null, new Date(), ThirdTypeEnum.CHA_BO_SHI,
					null, payOrderId,resCode , thirdResEnum.getStatus() ,buyType);
        	return thirdApiResult;
        }
 
        thirdApiResult = new ThirdApiResult(cusId, vin, reportUrl, new Date(), ThirdTypeEnum.CHA_BO_SHI,
				null, payOrderId, null, ThirdResEnum.SUCCESS.getStatus(),buyType);
        
        return thirdApiResult;
	}
 
	@Override
	public ThirdApiResult getByPayOrderId(String payOrderId) {
		
		ThirdApiResult query = new ThirdApiResult().setPayOrderId(payOrderId);
 
		return thirdApiResultMapper.getThirdApiResult(query);
	}

	@Override
	public PageInfo<ThirdApiResult> page(ThirdQuery query) {
		
		query.setCusId(CusUtils.getCusId());
		
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		
		query.setResultType(ThirdTypeEnum.CHA_BO_SHI.getCode());
		
		List<ThirdApiResult>  list = thirdApiResultMapper.thirdApiResultList(query);
 
		return new PageInfo<ThirdApiResult>(list);
	}

}
