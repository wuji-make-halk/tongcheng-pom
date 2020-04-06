package com.micro.pmo.moudle.share.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.mapper.ShareBrowseMapper;
import com.micro.pmo.mapper.ShareMapper;
import com.micro.pmo.moudle.car.service.CarService;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.share.entity.Share;
import com.micro.pmo.moudle.share.entity.ShareBrowse;
import com.micro.pmo.moudle.share.service.ShareService;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.service.StoreService;
import com.micro.pmo.moudle.store.vo.ShareQuery;

/**
 * 分享
 * @author raoBo
 *
 */
@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	private CusStoreMapper cusStoreMapper;
	
	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private ShareBrowseMapper shareBrowseMapper;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CarService carService;
	
	
	@Override
	public List<CusStore> storeCusList(BaseQuery baseQuery) {
		//PageHelper.startPage(baseQuery.getPageNumKey(), baseQuery.getPageSizeKey());
		//PageInfo<CusStore> info = new PageInfo<>();
		Customer cus = CusUtils.getCustomer();
		//平台用户没有微店处理
		if(cus.getCusType() == 2 || cus.getCusType() == 1){
			return Lists.newArrayList();
		}
		List<CusStore> cusStores= cusStoreMapper.findCusStoreByStorId(cus.getStoreId());
		return cusStores;
	}

	@Override
	public List<Share> shareList(Integer cusId) {
		List<Share> shares = shareMapper.findSharesByCusId(cusId);
		if(CollectionUtils.isEmpty(shares)){
			return shares;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (Share share : shares) {
			share.setCreateTime(formatter.format(share.getShareTime()));
			List<ShareBrowse> shareBrowseList = shareBrowseMapper.findShareBrowseByShareId(share.getShareId());
			if(CollectionUtils.isEmpty(shareBrowseList)){
				share.setBrowserCount(0);
			}else{
				share.setBrowserCount(shareBrowseList.size());
				share.setBrowserUrl(shareBrowseList.get(0).getCusHead());
			}
			
		}
		return shares;
	}
	
	@Override
	public PageInfo<Share> sharePage(ShareQuery shareQuery) {
		Integer cusId = shareQuery.getCusId();
		if(shareQuery.getCusId() == null){
			cusId = CusUtils.getCusId();
		}
		PageHelper.startPage(shareQuery.getPageNumKey(), shareQuery.getPageSizeKey());
		List<Share> shares = this.shareList(cusId);
		PageInfo<Share> info = new PageInfo<>(shares);
		return info;
	}

	@Override
	public Map<String, Object> shareInfo(Integer shareId) {
		Share share  = shareMapper.getShareById(shareId);
		Map<String, Object> map = Maps.newHashMap();
		if(share == null){
			return map;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		share.setCreateTime(formatter.format(share.getShareTime()));
		//人员列表
		List<ShareBrowse> shareBrowseList = shareBrowseMapper.findShareBrowseByShareId(share.getShareId());
		
		map.put("share", share);
		map.put("browsers", shareBrowseList);
		return map;
	}

	/***
	 * 人员分享处理
	 */
	@Transactional
	private void shareBrowseHandle(Integer shareType, Integer carId,Integer storeId,Integer cusId, String cusHead, String cusNick,String cusIp){
		try {
			String undefined = "undefined";
			if(StringUtils.isBlank(cusHead) || StringUtils.isBlank(cusNick) ||
					StringUtils.equals(cusHead, undefined) || StringUtils.equals(cusNick, undefined)){
				return ;
			}
			Share share = shareMapper.getShareByIdAndType(shareType, carId, storeId, cusId);
			//未找到分享记录
			if(share == null){
				return ;
			}
			ShareBrowse shareBrowse = shareBrowseMapper.findShareBrowseByIp(share.getShareId(), cusIp);
			//已记录过
			if(shareBrowse != null){
				return ;
			}
			//开始记录用户浏览
			shareBrowse = new ShareBrowse(share.getShareId(), cusHead, cusNick, cusIp, new Date());
			shareBrowseMapper.insertShareBrowse(shareBrowse);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
	}
	
	/***
	 * 分享微店详情
	 */
	@Override
	public Store shareStoreInfo(Integer storeId, Integer cusId, String cusHead, String cusNick,String ip) {
		//微店分享记录
		shareBrowseHandle(1, null, storeId, cusId, cusHead, cusNick, ip);
		return storeService.storeInfo(storeId);
	}

	/***
	 * 微店车源列表
	 */
	@Override
	public List<FindCarVO> shareStoreCarList(Integer storeId, String carIds) {
		return storeService.shareStoreCarList(storeId, carIds);
	}

	/***
	 * 微信车源详情
	 */
	@Override
	public Map<String, Object> shareStoreCarInfo(Integer storeId, Integer carId, Integer cusId, String cusHead,
			String cusNick,String ip) {
		//微店车辆详情
		shareBrowseHandle(2, carId, storeId, cusId, cusHead, cusNick, ip);
		
		return storeService.storeCarInfo(storeId, carId);
	}

	/***
	 * 平台车源列表
	 */
	@Override
	public List<FindCarVO> sharePlatformCarList(String carIds) {
		return carService.shareCarListByCarIds(carIds);
	}

	/***
	 * 分享平台车辆详情
	 */
	@Override
	public Map<String, Object> sharePlatformCarInfo(Integer carId, Integer cusId, String cusHead, String cusNick,String ip) {
		//分享车辆详情
		shareBrowseHandle(3, carId, null, cusId, cusHead, cusNick, ip);
		
		return carService.showCarInfo(carId);
	}

}
