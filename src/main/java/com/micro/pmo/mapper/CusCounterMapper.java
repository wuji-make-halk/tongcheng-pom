package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.entity.CusCounter;

/**
 * counter_record 
 * @author 
 * @createtime 
 */
public interface CusCounterMapper {
 
 	/**
 	* 保存counter_record
 	* @param  cusCounter
	* @return
 	**/
 	public int insertCusCounter(CusCounter counterRecord);
 	
    /**
       * 根据ID删除cusCounter，假删除
    * @param cusCounterId
	* @return
    **/
	public int deleteCusCounterById(Integer cusCounterId);
	
	/**
	* 根据ID更新cusCounter
	* @param cusCounter
	* @return
	**/
	public int updateCusCounterById(CusCounter cusCounter);
	
	/**
	* 根据ID获取cusCounter
	* @param cusCounterId
	* @return
	**/
	public CusCounter getCusCounterById(int cusCounterId);
	
	/**
	* 查询获取cusCounter
	* @return
	**/
	public List<CusCounter> CusCounterList();
	
	/**
	 * 根据类型 以及前台用户id获取
	 * @param type
	 * @return
	 */
	CusCounter getValidCusCounterByTypeAndCusId(@Param("counterType")CounterTypeEnum type,@Param("cusId")Integer cusId);
 
}