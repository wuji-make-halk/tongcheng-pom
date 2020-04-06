package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.entity.CounterRecord;

/**
 * counter_record 
 * @author 
 * @createtime 
 */
public interface CounterRecordMapper {
 
 	/**
 	* 保存counter_record
 	* @param  counterRecord
	* @return
 	**/
 	public int insertCounterRecord(CounterRecord counterRecord);
 
	/**
	* 根据ID获取counter_record
	* @param counterRecordId
	* @return
	**/
	public CounterRecord getCounterRecordById(int counterRecordId);
	
	/**
	* 查询获取counter_record
	* @return
	**/
	public List<CounterRecord> counterRecordList();
	
	/**
	 * 根据
	 * @param type
	 * @param cusId
	 * @return
	 */
	CounterRecord getFirstRecordByTypeAndCusId(@Param("type")CounterTypeEnum type ,@Param("cusId")Integer cusId);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	void deleteById(Integer id);
}