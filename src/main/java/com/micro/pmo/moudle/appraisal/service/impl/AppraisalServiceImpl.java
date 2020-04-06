package com.micro.pmo.moudle.appraisal.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.AppraisalMapper;
import com.micro.pmo.moudle.appraisal.entity.Appraisal;
import com.micro.pmo.moudle.appraisal.service.AppraisalService;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;


@Service
public class AppraisalServiceImpl implements AppraisalService{

	@Autowired
	private AppraisalMapper appraisalMapper;
	
	@Override
	@Transactional
	public int intserAppraisal(Appraisal appraisal) {
		Integer cusId = null;
		if(CusUtils.isLogin()){
			cusId = CusUtils.getCusId();
		}
		appraisal.setCreator(cusId);
		appraisal.setCreateTime(new Date());
		appraisalMapper.intserAppraisal(appraisal);
		return 0;
	}

	@Override
	public PageInfo<Appraisal> appraisalPage(Integer pageNumKey, Integer pageSizeKey) {
		Customer cus = CusUtils.getCustomer();
		if(cus.getStoreId() == null){
			throw new PmoException(ResultState.STORE_ERROR);
		}
		//店长可以看全部的，员工各看各的
		Integer cusId = null;
		if(cus.getCusType() == 1){
			cusId = cus.getCusId();
		}
		if(pageNumKey == null) pageNumKey = 10;
		if(pageSizeKey == null) pageSizeKey = 1;
		//分页
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<Appraisal> appraisals = appraisalMapper.AppraisalByStoreIdAndCusId(cus.getStoreId(), cusId);
		PageInfo<Appraisal> info = new PageInfo<Appraisal>(appraisals);
		return info;
	}

}
