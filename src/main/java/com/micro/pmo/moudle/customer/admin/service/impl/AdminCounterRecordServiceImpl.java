package com.micro.pmo.moudle.customer.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.mapper.CounterRecordMapper;
import com.micro.pmo.moudle.customer.admin.service.AdminCounterRecordService;
import com.micro.pmo.moudle.customer.entity.CounterRecord;

/**
 * CounterRecordService
 * @author  
 * @createtime 
 */
@Service
public class AdminCounterRecordServiceImpl implements AdminCounterRecordService {

	@Autowired
	private CounterRecordMapper counterRecordMapper;

 	@Override
	public PageInfo<CounterRecord> counterRecordPage(Integer pageNumKey, Integer pageSizeKey){
		//分页处理
		if(pageNumKey == null) pageNumKey = 1;
		if(pageSizeKey == null) pageSizeKey = 10;
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<CounterRecord> list = counterRecordMapper.counterRecordList();
		PageInfo<CounterRecord> info = new PageInfo<>(list);
		return info;
	}
}