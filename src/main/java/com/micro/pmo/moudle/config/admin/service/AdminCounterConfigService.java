package com.micro.pmo.moudle.config.admin.service;

import java.util.List;

import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月9日 
*/
public interface AdminCounterConfigService {

	/**
	 * 保存
	 * @param CounterConfig
	 */
	void saveCounterConfig(CounterConfig counterConfig);
	
	/**
	 * 根据id更新配置
	 * @param counterConfig
	 */
	void updateCounterConfigById(CounterConfig counterConfig);

	/**
	 * 配置分页
	 * @param query
	 * @return
	 */
	List<CounterConfig> listConfig(BaseQuery query);

}