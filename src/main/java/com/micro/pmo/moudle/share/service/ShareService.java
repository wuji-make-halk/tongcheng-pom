package com.micro.pmo.moudle.share.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.share.entity.Share;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.vo.ShareQuery;

/**
 * 分享Service
 */
public interface ShareService{
	
	/***
	 * 微店人员列表
	 * @return
	 */
	public List<CusStore> storeCusList(BaseQuery baseQuery);
	
	/**
	 * 分享列表
	 * @return
	 */
	public List<Share> shareList(Integer cusId);
	
	/**
	 * 分享分页(普通用户使用)
	 * @return
	 */
	public PageInfo<Share> sharePage(ShareQuery shareQuery);
	
	/**
	 * 分享详情
	 * @return
	 */
	public Map<String,Object> shareInfo(Integer shareId);
	
	/***
	 * 微店分享详情
	 * @param storeId
	 * @return
	 */
	public Store shareStoreInfo(Integer storeId,Integer cusId,String cusHead,String cusNick,String ip);
	
	/**
	 * 微店车源列表
	 * @param storeId
	 * @param carIds
	 * @return
	 */
	public List<FindCarVO> shareStoreCarList(Integer storeId,String carIds);
	
	/***
	 * 微店车辆详情
	 * @param storeId
	 * @param carId
	 * @param cusId
	 * @param cusHead
	 * @param cusNick
	 * @return
	 */
	public Map<String, Object> shareStoreCarInfo(Integer storeId,Integer carId,Integer cusId,String cusHead,String cusNick,String ip);
	
	/***
	 * 平台车源列表
	 * @param carIds
	 * @return
	 */
	public List<FindCarVO> sharePlatformCarList(String carIds);
	
	/***
	 * 平台车源详情
	 * @param carId
	 * @param cusId
	 * @param cusHead
	 * @param cusNick
	 * @return
	 */
	public Map<String, Object> sharePlatformCarInfo(Integer carId,Integer cusId,String cusHead,String cusNick,String ip);
	
}
