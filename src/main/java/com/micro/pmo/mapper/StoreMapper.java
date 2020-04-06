package com.micro.pmo.mapper;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.vo.StoreCarInfoVo;

/**
 * 微店Mapper接口
 */
public interface StoreMapper{
	
	/***保存微店信息***/
	public int insertStore(Store store);
	
	/***微店信息修改***/
	public int updateStore(Store store);
	
	/***微店详情***/
	public Store storeInfoById(@Param("storeId")Integer storeId);
	
	/***微店详情，通过用户**/
	public Store storeInfoByCus(@Param("cusId")Integer cusId);
	
	/***微店车辆微店基本信息***/
	public StoreCarInfoVo getStoreCarInfoById(@Param("storeId")Integer storeId);
}
