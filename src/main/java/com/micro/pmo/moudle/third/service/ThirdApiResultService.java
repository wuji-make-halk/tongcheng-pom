package com.micro.pmo.moudle.third.service;

import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.enu.ThirdTypeEnum;

public interface ThirdApiResultService {

	/**
	 * 获取指定用户最后一条信息
	 * @param vin
	 * @param resultType
	 * @return
	 */
	ThirdApiResult getLastThirdApiResult(Integer cusId,String vin, ThirdTypeEnum resultType);
 
	/**
	 * 获取车辆的有效记录
	 * @param vin
	 * @param resultType
	 * @return
	 */
	ThirdApiResult getLastThirdApiResult(String vin, ThirdTypeEnum resultType);
	
	/**
	 * 保存一条查询记录和搜索记录
	 * @param result
	 */
	void saveApiResultAndSearchRecord(ThirdApiResult result);
	
	/**
	 * 保存一条查询记录
	 * @param result
	 */
	void saveApiResult(ThirdApiResult result);
}
