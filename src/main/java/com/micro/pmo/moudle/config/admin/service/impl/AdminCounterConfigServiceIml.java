package com.micro.pmo.moudle.config.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.config.admin.service.AdminCounterConfigService;

/**
 * CounterConfigService
 * 
 * @author
 * @createtime
 */
@Service
public class AdminCounterConfigServiceIml implements AdminCounterConfigService {

	@Autowired
	private CounterConfigMapper counterConfigMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.micro.pmo.moudle.config.admin.service.CounterConfigService1#saveCounterConfig(
	 * com.micro.pmo.moudle.config.admin.entity.CounterConfig)
	 */
	@Override
	public void saveCounterConfig(CounterConfig counterConfig) {
		counterConfig.setGmtCreate(new Date());
		counterConfigMapper.insertCounterConfig(counterConfig);
	}

 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.micro.pmo.moudle.config.admin.service.CounterConfigService1#
	 * updateCounterConfigById(com.micro.pmo.moudle.config.admin.entity.CounterConfig)
	 */
	@Override
	public void updateCounterConfigById(CounterConfig counterConfig) {
		counterConfigMapper.updateCounterConfigById(counterConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.micro.pmo.moudle.config.admin.service.CounterConfigService1#
	 * counterConfigList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<CounterConfig> listConfig(BaseQuery query) {
		return counterConfigMapper.counterConfigList();
	}
}