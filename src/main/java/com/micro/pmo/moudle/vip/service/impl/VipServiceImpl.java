package com.micro.pmo.moudle.vip.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.mapper.CusVipMapper;
import com.micro.pmo.mapper.VipMapper;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.vip.entity.Vip;
import com.micro.pmo.moudle.vip.service.VipService;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月4日
 */
@Service
public class VipServiceImpl implements VipService {

	@Autowired
	private CusVipMapper cusVipMapper;

	@Autowired
	private VipMapper vipMapper;

	@Override
	public Map<String, Object> vipList() {
		
		Integer userId = CusUtils.getCustomer().getCusId();

		List<Vip> vipList = vipMapper.vipList();
		 
		Map<String, Object> result = Maps.newHashMap();
		result.put("vipList", vipList);
		result.put("surplusMonth", getSurplusMonth(userId));
		
		return result;
	}

	@Override
	public Vip getVipById(Integer id) {
		return vipMapper.getVipById(id);
	}

	@Override
	public BigDecimal getSurplusMonth(Integer userId) {
		return DateUtil.getSurplusMonth(cusVipMapper.getEndDate(userId));
	}

}
