package com.micro.pmo.moudle.vip.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CusVipMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.VipMapper;
import com.micro.pmo.moudle.customer.entity.CusVip;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.vip.admin.service.AdminVipService;
import com.micro.pmo.moudle.vip.entity.Vip;

/**
 * CusVipDao
 * 
 * @author wenhaofan
 * @createtime
 */
@Service
public class AdminVipServiceImpl implements AdminVipService {

	@Autowired
	private VipMapper vipMapper;

	@Autowired
	private CusVipMapper cusVipMapper;
	
	@Autowired
	private CustomerMapper cusMapper;
	
	@Override
	public void updateVip(Vip Vip) {
		vipMapper.updateVipById(Vip);
	}

	@Override
	public void saveVip(Vip Vip) {
		vipMapper.insertVip(Vip);
	}

	@Override
	public List<Vip> vipList(BaseQuery baseQuery) {
		return vipMapper.vipList();
	}

	@Override
	public void giveVip(Integer vipId, Integer cusId) {
		Vip vip = vipMapper.getVipById(vipId);
		//获取用户的有效vip记录
		Integer month = vip.getVipDuration();
		
		//获取用户的vip记录
		CusVip beforeCusVip = cusVipMapper.getValidCusVip(cusId);
		
		//根据以往的Vip开通记录计算新的Vip到期时间
		Date endStart = getEndDate(month, beforeCusVip);
	 
		CusVip cusVip = new CusVip(endStart, "" , cusId,new Date(),0, cusId);
		
		// 如果有vip记录则更新剩余有效时间
		if (beforeCusVip != null) {
			beforeCusVip.setVipExpirationDate(endStart);
			cusVipMapper.updateVipExpirationDateById(beforeCusVip);
		}else {
			cusVipMapper.insertCusVip(cusVip);
		}
		
		//更新缓存
		Customer cusOld = cusMapper.getCusAllById(cusId);
		if(cusOld == null || StringUtils.isBlank(cusOld.getCusToken())){
			return ;
		}
		//删除缓存
		CusUtils.removeCusCache(cusOld.getCusToken());
		//更新缓存
		CusUtils.putCusCache(cusOld.getCusToken());
	}
	
	/**
	 * 获取新的vip结束时间
	 * 
	 * @param month
	 * @param beforeCusVip
	 * @return
	 */
	private Date getEndDate(Integer month, CusVip beforeCusVip) {
		Date newEndDate = null;
		
		//如果没有之前的VIP记录 则直接将下一个的时间作为VIP截止时间
		if(beforeCusVip==null) {
			return  DateUtil.datePlusMonth(new Date(),month);	
		}
  
		Date endDate = beforeCusVip.getVipExpirationDate();
		
		//如果vip未过期则在vip截止时间上加上新增的时间
		if (endDate.after(new Date())) {
			newEndDate = DateUtil.datePlusMonth(endDate,month);
			return newEndDate;
		} 
		 
		return newEndDate = DateUtil.datePlusMonth(new Date(), month);
	}

}