package com.micro.pmo.moudle.store.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.vo.StaffVo;

/**
 * 微店Service
 */
public interface StoreService{
	
	/**微店信息保存***/
	public Store saveStore(Store store);
	
	/**微店信息修改***/
	public Store updateStore(Store store);
	
	/**微店详情***/
	public Store storeInfo(Integer storeId);
	
	/***员工添加**/
	public void storeAddCus(StaffVo staffVo);
	
	/**员工列表***/
	public PageInfo<CusStore> staffList(Integer pageNumKey,Integer pageSizeKey,String keyword);
	
	/***微店车辆列表***/
	public List<FindCarVO> storeCarList(Integer storeId,String keyword);
	
	/***
	 * 分享微店车源列表
	 * @param storeId
	 * @param carIds
	 * @return
	 */
	public List<FindCarVO> shareStoreCarList(Integer storeId,String carIds);
	
	/**微店车辆详情****/
	public Map<String,Object> storeCarInfo(Integer storeId,Integer carId);
	
	/***
	 * 微店车辆分页列表
	 * @param storeId
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	public PageInfo<FindCarVO> carPage(Integer storeId,Integer pageNumKey,Integer pageSizeKey);
}
