package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.appraisal.entity.Appraisal;

/**
 * 车估价Mapper接口
 */
public interface AppraisalMapper{
	
	/**插入求购**/
	public int intserAppraisal(Appraisal appraisal);
	
	/**求购List***/
	public List<Appraisal> AppraisalByStoreIdAndCusId(@Param("storeId") Integer storeId,@Param("pushCusId") Integer cusId);
}
