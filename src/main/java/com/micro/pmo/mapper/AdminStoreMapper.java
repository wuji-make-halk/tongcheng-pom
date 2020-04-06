package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.store.admin.vo.AdminStoreQueryVO;
import com.micro.pmo.moudle.store.entity.Store;

/**
 * 微店Mapper接口
 */
public interface AdminStoreMapper{
	
	/**
	 * 查询微店列表
	 * @param store
	 * @return
	 */
	public List<Store> storeList(AdminStoreQueryVO  query);
	
	 
}
