package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.vo.ThirdQuery;

/**
 * ThirdApiResult 
 * @author wenhaofan
 * @createtime 
 */
public interface ThirdApiResultMapper {
 
 	/**
 	* 保存ThirdApiResult
 	* @param  thirdApiResult
	* @return
 	**/
 	public int insertThirdApiResult(ThirdApiResult thirdApiResult);
 
	
	/**
	* 根据ID更新ThirdApiResult
	* @param thirdApiResult
	* @return
	**/
	public int updateThirdApiResultById(ThirdApiResult thirdApiResult);
 
	/**
	 * 根据id去获取
	 * @param id
	 * @return
	 */
	ThirdApiResult getThirdApiResultById(Integer id);
	
	/**
	* 查询获取ThirdApiResult
	* @return
	**/
	public List<ThirdApiResult> thirdApiResultList(ThirdQuery query);
	
	/**
	 * 获取一条有效的记录
	 * @param thirdApiResult
	 * @return
	 */
	public ThirdApiResult getThirdApiResult(ThirdApiResult thirdApiResult);
}