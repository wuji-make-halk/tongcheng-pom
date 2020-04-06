package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.want.entity.WantBuy;

/**
 * 求购Mapper接口
 */
public interface WantBuyMapper{
	
	/**插入求购**/
	public int intserWantBuy(WantBuy wantBuy);
	
	/**求购List***/
	public List<WantBuy> wantBuyByStoreIdAndCusId(@Param("storeId") Integer storeId,@Param("pushCusId") Integer cusId);
}
