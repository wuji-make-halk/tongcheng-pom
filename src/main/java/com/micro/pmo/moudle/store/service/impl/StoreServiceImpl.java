package com.micro.pmo.moudle.store.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.StoreCarMapper;
import com.micro.pmo.mapper.StoreMapper;
import com.micro.pmo.moudle.account.service.CusAccountService;
import com.micro.pmo.moudle.car.vo.CarInfoVo;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.store.entity.StoreCar;
import com.micro.pmo.moudle.store.service.StoreService;
import com.micro.pmo.moudle.store.vo.StaffVo;
import com.micro.pmo.moudle.store.vo.StoreCarInfoVo;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private CusStoreMapper cusStoreMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private StoreCarMapper storeCarMapper;
	@Autowired
	private CarMapper carMapper;
	@Autowired
	private CusAccountService cusAccountService;
 
	@Value("${qiniuyun.img_url}")
	private String defaultImgUrl;
	
	@Override
	@Transactional
	public Store saveStore(Store store) {
		Customer cus = CusUtils.getCustomer();
		if(cus.getStoreId() != null){
			throw new PmoException(ResultState.STORE_FAIL);
		}
		//保存基本信息
		Date date = new Date();
		store.setCreator(cus.getCusId());
		store.setCreateTime(date);
		storeMapper.insertStore(store);
		//保存到cus_store
		CusStore cusStore = new CusStore(cus.getCusId(), date, cus.getCusId(), null, 0, store.getStoreId());
		cusStoreMapper.insertCusStore(cusStore);
		//更新缓存
		CacheUtils.remove(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + cus.getCusToken());
		cus.setStoreId(store.getStoreId());
		cus.setCusType(0);
		CacheUtils.put(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + cus.getCusToken(),cus);
		//微店创建好后，原发布到微店车辆中间表的处理，多辆车
		List<Integer> carIds = storeCarMapper.findCarIdsByCusId(cus.getCusId());
		//没有发布到微店的
		if(CollectionUtils.isEmpty(carIds)){
			return store;
		}
		//有做相应的处理
		List<StoreCar> storeCars = Lists.newArrayList();
		StoreCar storeCar = null;
		for (Integer carId : carIds) {
			storeCar = new StoreCar(store.getStoreId(), carId, cus.getCusId(), date);
			storeCars.add(storeCar);
		}
		//批量写入
		storeCarMapper.batchStoreCar(storeCars);
		return store;
	}

	@Override
	@Transactional
	public Store updateStore(Store store) {
		storeMapper.updateStore(store);
		return store;
	}

	@Override
	public Store storeInfo(Integer storeId) {
		//微店自身信息
		Store store = storeMapper.storeInfoById(storeId);
		
		if(store == null) {
			return null;
		}
		boolean isStoreCus = false;
		if(CusUtils.isLogin()){
			 isStoreCus = cusStoreMapper.isStoreCus(CusUtils.getCusId(), storeId);
		}
		store.setIsStoreCus(isStoreCus);
		//发布微店车辆条数
		Integer size = storeCarMapper.getStoreCarSizeById(storeId);
		if(size == null){
			size = 0;
		}
		store.setCarMun(size);
		return store;
	}

	@Override
	@Transactional
	public void storeAddCus(StaffVo staffVo) {
		Customer cus = CusUtils.getCustomer();
		//店长判断
		if(cus.getCusType() != 0){
			throw new PmoException(ResultState.STORE_STAFF);
		}
		//添加判断
		CusStore cusStore = cusStoreMapper.findCusStoreByPhone(staffVo.getPhone());
		if(cusStore != null){
			throw new PmoException(ResultState.STORE_ADD_STAFF);
		}
		Customer staff = customerMapper.findCusByPhone(staffVo.getPhone());
		//新增用户
		if(staff == null){
			String cusAvatar = defaultImgUrl + "default.png";
			staff = new Customer(staffVo.getPhone(), staffVo.getName(), cusAvatar, 0, new Date(),cus.getProvinceName(),cus.getCityName());
			customerMapper.insertCus(staff);
			
			//创建用户对应的账号记录
			cusAccountService.createCusAccount(staff.getCusId());
		}else{
			//更新这个用户缓存
			if(StringUtils.isNotBlank(staff.getCusToken())){
				CacheUtils.remove(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + staff.getCusToken());
				staff.setStoreId(cus.getStoreId());
				staff.setCusType(1);
				CacheUtils.put(CacheUtils.CUS_CACHE, CacheUtils.CUS_CACHE_ID + staff.getCusToken(),staff);
			}
			//处理微店，微店人员处理车辆 TODO
			//微店创建好后，原发布到微店车辆中间表的处理，多辆车
			List<Integer> carIds = storeCarMapper.findCarIdsByCusId(cus.getCusId());
			//没有发布到微店的
			if(CollectionUtils.isNotEmpty(carIds)){
				//有做相应的处理
				Date date = new Date();
				List<StoreCar> storeCars = Lists.newArrayList();
				StoreCar storeCar = null;
				for (Integer carId : carIds) {
					storeCar = new StoreCar(cus.getStoreId(), carId, staff.getCusId(), date);
					storeCars.add(storeCar);
				}
				//批量写入
				storeCarMapper.batchStoreCar(storeCars);
			}
		}
		//添加中间表处理
		CusStore staffCus = new CusStore(staff.getCusId(), new Date(), cus.getCusId(), cus.getCusId(), 1, cus.getStoreId());
		cusStoreMapper.insertCusStore(staffCus);
	}

	@Override
	public PageInfo<CusStore> staffList(Integer pageNumKey,Integer pageSizeKey,String keyword) {
		Customer cus = CusUtils.getCustomer();
		if(cus.getStoreId() == null){
			throw new PmoException(ResultState.STORE_ERROR);
		}
		//分页处理
		if(pageNumKey == null) pageNumKey = 1;
		if(pageSizeKey == null) pageSizeKey = 10;
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<CusStore> list = cusStoreMapper.findCusStoreByStoreId(cus.getStoreId(),keyword);
		PageInfo<CusStore> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public List<FindCarVO> storeCarList(Integer storeId,String keyword) {
		return storeCarMapper.storeCarByIdAndKey(storeId, keyword,null);
	}
	
	@Override
	public List<FindCarVO> shareStoreCarList(Integer storeId, String carIds) {
		return storeCarMapper.storeCarByIdAndKey(storeId, null,carIds);
	}
	
	
	@Override
	public Map<String, Object> storeCarInfo(Integer storeId, Integer carId) {
		// 本身车辆基础信息
		CarInfoVo carInfoVo = carMapper.showCarInfoById(carId, null, 1);
		if(carInfoVo == null){
			throw new PmoException(ResultState.CAR_INFO_ERROR);
		}
		boolean isStoreCus = false;
		if(CusUtils.isLogin()){
			isStoreCus = cusStoreMapper.isStoreCus(CusUtils.getCusId(), storeId);
		}
		//条数
		Integer carSize = storeCarMapper.getStoreCarSizeById(storeId);
		//微店部分信息
		StoreCarInfoVo vo = storeMapper.getStoreCarInfoById(storeId);
		vo.setCarSize(carSize);
		//匹配微店车辆
		List<FindCarVO> cars= carMapper.getStoreMatchedCar(storeId, carId);
		Map<String, Object> map = Maps.newHashMap();
		map.put("carInfo", carInfoVo);
		map.put("matched", cars);
		map.put("storeInfo", vo);
		map.put("isStoreCus", isStoreCus);
		return map;
	}

	/***
	 * 车辆分页
	 */
	@Override
	public PageInfo<FindCarVO> carPage(Integer storeId, Integer pageNumKey, Integer pageSizeKey) {
		if(pageNumKey == null) pageNumKey = 1;
		if(pageSizeKey == null) pageSizeKey = 10;
		//获取用户
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<FindCarVO> vos = storeCarMapper.storeCarByIdAndKey(storeId, null,null);
		PageInfo<FindCarVO> info = new PageInfo<>(vos);
		return info;
	}


	
}
