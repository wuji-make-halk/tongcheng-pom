package com.micro.pmo.moudle.car.admin.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.DepositMapper;
import com.micro.pmo.mapper.OrderDepositMapper;
import com.micro.pmo.moudle.car.admin.service.AdminDepositService;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderQuery;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositOrderVO;
import com.micro.pmo.moudle.car.admin.vo.AdminDepositRefundVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.OrderDeposit;
import com.micro.pmo.moudle.car.service.DepositService;
import com.micro.pmo.moudle.config.admin.entity.Deposit;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.order.entity.Order;
import com.micro.pmo.moudle.order.service.OrderService;
import com.micro.pmo.moudle.user.utils.UserUtils;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月1日 
*/
@Service
public class AdminDepositServiceImpl implements AdminDepositService{
	
	@Autowired
	private OrderDepositMapper orderDepositMapper;
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private OrderService orderService;
 
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private DepositMapper depositMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public PageInfo<AdminDepositOrderVO> pageDepositOrder(AdminDepositOrderQuery query) {
		if(StringUtils.isBlank(query.getCityName())){
			query.setCityName(UserUtils.getUserCity());
		}
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		
		List<AdminDepositOrderVO> list = orderDepositMapper.listBuyerDepositOrder(query);
		for (AdminDepositOrderVO adminDepositOrderVO : list) {
			if(adminDepositOrderVO.getDealStatus() == 1 || adminDepositOrderVO.getDealStatus() == 2){
				OrderDeposit orderDeposit = orderDepositMapper.getOrderDepositByOrderId(adminDepositOrderVO.getBuyerOrderId());
				if(orderDeposit == null){
					continue;
				}
				//卖方没有确认都是正在交易
				if(orderDeposit.getStatus() == 0){
					adminDepositOrderVO.setDealStatus(0);
					continue;
				}
				//卖方交易失败，买方成功
				if(orderDeposit.getStatus() == 2 && adminDepositOrderVO.getDealStatus() == 1){
					adminDepositOrderVO.setDealStatus(2);
					continue;
				}
			}
			
			
			
			
		}
		
		PageInfo<AdminDepositOrderVO> infoPage = new PageInfo<AdminDepositOrderVO>(list);
 
		return infoPage;
	}
	
	public Kv<String,Object> getOrderDepositInfo(String orderId) {
		//获取买家定金记录
		AdminDepositOrderVO buyerOrderDeposit = orderDepositMapper.getOrderDepositInfoByOrderId(orderId);
		
		OrderDeposit buyerDepositOrder = orderDepositMapper.getOrderDepositOtherByOrderId(orderId, 1);
		//获取定金的车辆详情
		Car car = carMapper.carInfoById(buyerOrderDeposit.getCarId());
		AdminDepositOrderVO sellerOrderDeposit = null;
		if(buyerDepositOrder == null){
			Customer cus = customerMapper.getCusAllById(car.getCreator());
			sellerOrderDeposit = new AdminDepositOrderVO();
			sellerOrderDeposit.setCusId(car.getCreator());
			sellerOrderDeposit.setCusNick(cus.getCusNick());
			sellerOrderDeposit.setCarId(car.getCarId());
			sellerOrderDeposit.setType(1);
			sellerOrderDeposit.setDealStatus(3);
		}else{
			//获取卖家定金记录
			sellerOrderDeposit = orderDepositMapper.getSellerDepositByBuyerOrderId(buyerDepositOrder.getOrderDepositId());
		}
		
		Kv<String,Object> data  = Kv.by("buyerDeposit", buyerOrderDeposit);

		boolean isRefund = false;
		
		if(sellerOrderDeposit != null && buyerOrderDeposit!=null) {
			Integer buyerDealStatus = buyerOrderDeposit.getDealStatus();
			Integer sellerDealStatus = sellerOrderDeposit.getDealStatus();
			
			if(buyerDealStatus.equals(1)&&sellerDealStatus.equals(2)) {
				isRefund = true;
			}else if(buyerDealStatus.equals(2)&&sellerDealStatus.equals(1)){
				isRefund = true;
			}
		}

		return data.set("sellerDeposit", sellerOrderDeposit)
				.set("car",car)
				.set("isRefund", isRefund);
	}

	@Override
	public void depositRefund(AdminDepositRefundVO depositRefund) {

		if(UserUtils.getUser().getUserType() == 1) {
			throw new PmoException(ResultState.TOKEN_ERROR,"您没有权限");
		}
		
		OrderDeposit buyerOrderDeposit = orderDepositMapper.getOrderDepositByOrderId(depositRefund.getBuyerOrderId());

		if(buyerOrderDeposit == null ) {
			throw new PmoException(ResultState.PARAM_ERROR,"订单ID错误");
		}

		OrderDeposit sellerOrderDeposit = orderDepositMapper.getOrderDepositOtherByOrderId(depositRefund.getBuyerOrderId(),1);
 
		Integer sellerDepositStatus = sellerOrderDeposit.getStatus();
		Integer buyerDepositStatus = buyerOrderDeposit.getStatus();
 
		if(buyerDepositStatus.equals(sellerDepositStatus)) {
			throw new PmoException(ResultState.PARAM_ERROR,"订单状态异常");
		}
		
		
		//计算服务费
		BigDecimal buyerServiceCharge = buyerOrderDeposit.getMoney().subtract(depositRefund.getBuyerPrice());
		BigDecimal sellerServiceCharge = sellerOrderDeposit.getMoney().subtract(depositRefund.getSellerPrice());
		
		//避免退款金额大于支付金额
		if(buyerServiceCharge.compareTo(BigDecimal.ZERO) == -1) {
			throw new PmoException(ResultState.DEPOSIT_REFUND_MONEY_OVERFLOW);
		}
		
		if(sellerServiceCharge.compareTo(BigDecimal.ZERO) == -1) {
			throw new PmoException(ResultState.DEPOSIT_REFUND_MONEY_OVERFLOW);
		}
		
		//设置服务费
		Order buyerOrder = orderService.getOrderByOrderId(buyerOrderDeposit.getOrderDepositId());
		Order sellerOrder = orderService.getOrderByOrderId(sellerOrderDeposit.getOrderDepositId());
		
		buyerOrder.setServiceCharge(buyerServiceCharge);
		sellerOrder.setServiceCharge(sellerServiceCharge);
		
		//设置退款金额
		buyerOrder.setRefundAmount(depositRefund.getBuyerPrice());
		sellerOrder.setRefundAmount(depositRefund.getSellerPrice());
		
		
		//买家定金退款
		depositService.refundDeposit(buyerOrder,buyerOrderDeposit);
		
		//卖家定金退款
		depositService.refundDeposit(sellerOrder,sellerOrderDeposit);	
	}

	@Override
	public void updateDepositConfig(Deposit deposit) {
		depositMapper.updateDeposit(deposit);
	}

	@Override
	public Deposit getDepositConfig() {
		return depositMapper.getDeposit();
	}	
	
	
	
}
