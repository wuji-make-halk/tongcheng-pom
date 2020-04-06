package com.micro.pmo.moudle.share.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.mapper.ShareLogMapper;
import com.micro.pmo.mapper.ShareMapper;
import com.micro.pmo.mapper.StoreCarMapper;
import com.micro.pmo.mapper.StoreMapper;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.share.entity.Share;
import com.micro.pmo.moudle.share.entity.ShareLog;
import com.micro.pmo.moudle.share.service.ShareLogService;
import com.micro.pmo.moudle.share.vo.ShareLogVo;
import com.micro.pmo.moudle.store.entity.Store;

@Service
public class ShareLogServiceImpl implements ShareLogService{

	@Autowired
	private ShareLogMapper shareLogMapper;
	
	@Autowired
	private CusStoreMapper cusStoreMapper;
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private StoreCarMapper storeCarMapper;
	
	/***
	 * 保存分享记录
	 */
	@Override
	@Transactional
	public void saveShareLog(ShareLog shareLog) {
		//记录处理
		Date nowDate = new Date();
		Integer cusId = CusUtils.getCusId();
		shareLog.setShareTime(nowDate);
		shareLog.setCusId(cusId);
		shareLogMapper.insertShareLong(shareLog);
		if(shareLog.getShareType() == 1){
			if(shareLog.getStoreId() == null){
				throw new PmoException(ResultState.PARAM_ERROR,"参数错误：[storeId] 微店id不能为空");
			}
			Share share = new Share(shareLog.getShareType(), nowDate, cusId);
			//
			Store store = storeMapper.storeInfoById(shareLog.getStoreId());
			if(store != null){
				share.setShareName(store.getStoreName());
				share.setSharePhoto(store.getStorePhoto());
			}
			share.setStoreId(shareLog.getStoreId());
			List<Share> shares = Lists.newArrayList();
			shares.add(share);
			shareMapper.batchShare(shares);
			return ;
		
		}
		if(StringUtils.isBlank(shareLog.getShareIds())){
			throw new PmoException(ResultState.PARAM_ERROR,"参数错误：[shareIds] 分享ids不能为空");
		}
		String [] shareIds = shareLog.getShareIds().split(",");
		//没有id
		if(shareIds == null || shareIds.length == 0){
			return;
		}
		//对应链接处理
		List<Share> shares = Lists.newArrayList();
		Integer storeId = null;
		//微店处理
		for (String shareIda : shareIds) {
			Integer shareId = Integer.parseInt(shareIda);
			Share share = new Share(shareLog.getShareType(), nowDate, cusId);
			//车辆处理
			Car car = carMapper.carInfoById(shareId);
			//车辆处理
			if (car != null) {
				share.setShareName(car.getBrandSeries());
				share.setSharePhoto(car.getCarImg1());
				share.setCarId(car.getCarId());
			}
			//平台车源处理
			if(shareLog.getShareType() == 3){
				shares.add(share);
				continue;
			}
			//微店车源处理
			if(shareLog.getShareType() == 2){
				if(storeId == null){
					storeId = storeCarMapper.getStoreIdByCarId(shareId);
				}
				share.setStoreId(storeId);
				shares.add(share);
			}
		}
		//没有就直接返回
		if(CollectionUtils.isEmpty(shares)){
			return ;
		}
		//批量写入分享记录 
		shareMapper.batchShare(shares);
		
	}
	/***
	 * 周分享统计
	 */
	@Override
	public Map<String,Object> weekCounts() {
		Customer cus = CusUtils.getCustomer();
		//微店人员列表
		Date minDate = getTimesWeekmorning();
		Date maxDate = getTimesWeeknight();
		Integer counts = null;
		if(cus.getCusType() == 0 && cus.getStoreId() != null){
			//处理微店处理
			List<Integer> cusIds = cusStoreMapper.findeCusIdsById(cus.getStoreId());
			counts = shareLogMapper.weekCount(cusIds, null, minDate, maxDate);
		}else{
			counts = shareLogMapper.weekCount(null, cus.getCusId(), minDate, maxDate);
		}
		if(counts == null){
			counts = 0;
		}
		//最新查看头像和昵称
		 String newestHead = shareLogMapper.getShareCusHead(cus.getCusId());
		 Map<String,Object> map = Maps.newHashMap();
		 map.put("counts", counts);
		 map.put("newestHead", newestHead);
		//Integer count = shareLogMapper
		return map;
	}

	// 获得本周一0点时间
	private  Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
 
	// 获得本周日24点时间
	private  Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}


	/***
	 * 分页查询排名
	 */
	@Override
	public List<ShareLogVo> findShareLog() {
		Customer cus = CusUtils.getCustomer();
		//分页
		//PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		//PageInfo<ShareLogVo> info = new PageInfo<ShareLogVo>();;
		if(cus.getCusType() != 2 || cus.getStoreId() != null){
			//微店人员列表
			Date minDate = getTimesWeekmorning();
			Date maxDate = getTimesWeeknight();
			List<Integer> cusIds = cusStoreMapper.findeCusIdsById(cus.getStoreId());
			List<ShareLogVo> vos = shareLogMapper.findShareLog(cusIds, minDate, maxDate);
			return vos;
		}
		return Lists.newArrayList();
	}


		
}
