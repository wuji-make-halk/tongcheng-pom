package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.customer.entity.CusStore;

/**
 * 用户微店表Mapper接口
 */
public interface CusStoreMapper{
	
	/**写入微店人员信息**/
	public int insertCusStore(CusStore cusStore);
	
	/**通过电话号码找用户所在微店**/
	public CusStore findCusStoreByPhone(@Param("phone")String phone);
	
	/***查找微店下的员工***/
	public List<CusStore> findCusStoreByStoreId(@Param("storeId")Integer storeId,@Param("keyword")String keyword);
	
	/**
	 *  判断用户是否是微店的员工
	 * @param cusId
	 * @param storeId
	 * @return
	 */
	public boolean isStoreCus(@Param("cusId")Integer cusId,@Param("storeId")Integer storeId);
	
	/***
	 * 通过用户id找微店
	 * @param cusId
	 * @return
	 */
	public CusStore findCusStoreByCusId(@Param("cusId")Integer cusId);
	
	/***
	 * 微店人员列表ids
	 * @param storeId
	 * @return
	 */
	public List<Integer> findeCusIdsById(@Param("storeId")Integer storeId);
	
	/**
	 * 微店id查找用户
	 * @param storeId
	 * @return
	 */
	public List<CusStore> findCusStoreByStorId(@Param("storeId")Integer storeId);
	
}
