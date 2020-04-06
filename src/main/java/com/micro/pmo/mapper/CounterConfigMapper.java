package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
 

/**
 * CounterConfig 
 * @author 
 * @createtime 
 */
public interface CounterConfigMapper {
 
 	/**
 	* 保存CounterConfig
 	* @param  counterConfig
	* @return
 	**/
 	public int insertCounterConfig(CounterConfig counterConfig);
	
	/**
	* 根据ID更新CounterConfig
	* @param counterConfig
	* @return
	**/
	public int updateCounterConfigById(CounterConfig counterConfig);
	
	/**
	* 根据ID获取CounterConfig
	* @param sysCounterConfigId
	* @return
	**/
	public CounterConfig getCounterConfigById(int sysCounterConfigId);
	
	/**
	* 查询获取CounterConfig
	* @return
	**/
	public List<CounterConfig> counterConfigList();
	
	/**
	 * 根据类型获取有效的配置
	 * @param type
	 * @return
	 */
	public CounterConfig getValidCounterConfigByType(CounterTypeEnum type);
	
	/**
	 * 根据类型获取购买信息
	 * @param type
	 * @return
	 */
	public CounterConfig getBuyInfoByType(CounterTypeEnum type);
}