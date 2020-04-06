package com.micro.pmo.moudle.account.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CusAccountLogMapper;
import com.micro.pmo.moudle.account.entity.CusAccountLog;
import com.micro.pmo.moudle.account.service.CusAccountLogService;
import com.micro.pmo.moudle.customer.utils.CusUtils;

/**
 * CusAccountLogService
 * @author wenhaofan 
 * @createtime 
 */
@Service
public class CusAccountLogServiceImpl implements CusAccountLogService {

	@Autowired
	private CusAccountLogMapper cusAccountLogMapper;
 
 	@Override
	public void saveCusAccountLog(CusAccountLog cusAccountLog) {
 		//设置流水号
 		Date now = new Date();
 		cusAccountLog.setLogId(UUIDUtils.createSerialNum());
 		cusAccountLog.setGmtCreate(now);
 		cusAccountLog.setGmtModify(now);
		cusAccountLogMapper.insertCusAccountLog(cusAccountLog);
	}
	
 	@Override
	public CusAccountLog getCusAccountLogById(Integer id){
 		return cusAccountLogMapper.getCusAccountLogById(id);
 	}
 
 	@Override
	public PageInfo<CusAccountLog> cusAccountLogPage(BaseQuery query) {
		//分页处理
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		
		//用户id
		Integer cusId = CusUtils.getCusId();
		
		List<CusAccountLog> list = cusAccountLogMapper.cusAccountLogList(cusId);
		PageInfo<CusAccountLog> info = new PageInfo<>(list);
		
		return info;
	}
 

	@Override
	public void updateAccountByOrderId(CusAccountLog log) {
		cusAccountLogMapper.updateAccountByOrderId(log);
	}
	
}