package com.micro.pmo.moudle.car.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.jpush.JPushUtil;
import com.micro.pmo.commons.utils.jpush.PushUtils;
import com.micro.pmo.mapper.CarDealMapper;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CusStoreMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.DepositMapper;
import com.micro.pmo.mapper.StoreCarMapper;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarDeal;
import com.micro.pmo.moudle.car.service.CarService;
import com.micro.pmo.moudle.car.vo.CarCountByStatusVO;
import com.micro.pmo.moudle.car.vo.CarInfoVo;
import com.micro.pmo.moudle.car.vo.CarLowerVo;
import com.micro.pmo.moudle.car.vo.FindCarQueryVO;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.car.vo.ReserveCarVo;
import com.micro.pmo.moudle.config.admin.entity.Deposit;
import com.micro.pmo.moudle.customer.entity.CusStore;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.store.entity.StoreCar;

@Service
public class CarServiceImpl implements CarService {

	private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private CarMapper carMapper;
	@Autowired
	private StoreCarMapper storeCarMapper;
	@Autowired
	private CarDealMapper carDealMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private DepositMapper depositMapper;
	@Autowired
	private CusStoreMapper cusStoreMapper;

	@Override
	@Transactional
	public void saveCar(Car car) {
		Customer cus = CusUtils.getCustomer();
		// 初始默认设置
		Date date = new Date();
		carImgHandle(car);
		car.setCreator(cus.getCusId());
		car.setCreateTime(date);
		car.setUpdateTime(date);
		car.setDelFlag(0);
		car.setIsPromotion(2);
		// 处理状态
		carStatusHandle(car);
		// 计算车辆
		carAgeCalculation(car);
		// 写入数据
		carMapper.insertCar(car);
		// 处理微店和车辆中间表
		storeCarHandle(cus, car);
	}

	/**
	 * 图片处理
	 * @param car
	 */
	private void carImgHandle(Car car){
		List<String> carImgs = car.getCarImgs();
		if(CollectionUtils.isEmpty(carImgs)){
			throw new PmoException(ResultState.PARAM_ERROR,"参数：carImgs 不能为空");
		}
		int size = carImgs.size();
		for (int i = 0; i < size; i++) {
			String carImg = carImgs.get(i);
			if(i == 0){
				car.setCarImg1(carImg);
				continue;
			}
			if(i == 1){
				car.setCarImg2(carImg);
				continue;
			}
			if(i == 2){
				car.setCarImg3(carImg);
			}
			
		}
	}
	/***
	 * 计算车龄
	 * 
	 * @param car
	 */
	private void carAgeCalculation(Car car) {
		long nowDate = new Date().getTime();
		long old = car.getCarOldBoadTime().getTime();
		int carAge = 0;
		// 上牌时间大于当前时间返回
		if (old >= nowDate) {
			car.setCarAge(carAge);
			return;
		}
		// 相减返回时间/天
		long cha = nowDate - old;
		long day = cha / (1000 * 60 * 60 * 24);
		try {
			carAge = (int) day;
		} catch (Exception e) {
		}
		car.setCarAge(carAge);
	}

	/**
	 * 处理车辆状态
	 * 
	 * @param car
	 */
	private void carStatusHandle(Car car) {
		if (car.getOperation() == 2) {
			if (car.getMicroShop() == 1 || car.getPlatform() == 1) {
				car.setCarStatus(1);
				// 匹配平台推送
				if (car.getPlatform() == 1) {
					pushReserve(car);
					// 新开线程处理极光推送
					/*new Thread() {
						public void run() {
							pushReserve(car);
						}
					}.start();*/
				}
			} else {
				throw new PmoException(ResultState.OPERATION_ERROR);
			}
			return;
		}
		car.setCarStatus(0);
	}

	/***
	 * 匹配推送
	 * 
	 * @param car
	 */
	private void pushReserve(Car car) {
		try {
			Date carOldBoadTime = car.getCarOldBoadTime();
			// 时间差计算
			Calendar c1 = Calendar.getInstance(); // 当前日期
			Calendar c2 = Calendar.getInstance();
			c2.setTime(carOldBoadTime); // 设置为另一个时间
			int year = c1.get(Calendar.YEAR);
			int oldYear = c2.get(Calendar.YEAR);
			int difference = year - oldYear;
			if (difference < 0) {
				return;
			}
			// 月份比较
			int month = c1.get(Calendar.MONTH);
			int oldmonth = c2.get(Calendar.MONTH);
			if (month > oldmonth) {
				difference += 1;
			}
			// 车辆匹配处理 TODO
			List<ReserveCarVo> matchs = carMapper.getCarMatched(difference, car.getCarType(), car.getCarColor(), car.getCarMileage());
			if(CollectionUtils.isEmpty(matchs)){
				return ;
			}
			//收集设备码
			//List<JPushEntity> entitys = Lists.newArrayList();
			String title = "预订收车";
			String text = car.getCityLocation() +"成都发布" + car.getBrandSeries() + "。请尽快查看>>";
			if(car.getInterPrice() != null){
				text = "网标价："+ car.getInterPrice() +" 万元！" + text;
			}
			//开始匹配
			for (ReserveCarVo reserveCarVo : matchs) {
				//优先判断品牌车型
				if(car.getBrandSeries().indexOf(reserveCarVo.getBrandSeries()) < 0){
					continue;
				}
				//判断市的处理
				if(StringUtils.equals(car.getCityLocation(), reserveCarVo.getRegionCity()) 
						|| StringUtils.equals("全国", reserveCarVo.getRegionCity())
						|| StringUtils.equals(car.getCityLocation(), reserveCarVo.getRegion2City())
						|| StringUtils.equals("全国", reserveCarVo.getRegion2City())){
				}else{
					continue;
				}
				//使用性质 carNature
				if(StringUtils.isNotBlank(reserveCarVo.getCarNature())){
					if(!StringUtils.equals(car.getCarNature(), reserveCarVo.getCarNature())){
						continue;
					}
				}
				//变速箱 carGearbox 
				//发布车辆变速箱处理
				if(StringUtils.isBlank(car.getCarGearbox()) && StringUtils.isNotBlank(reserveCarVo.getCarGearbox()) ){
					continue;
				}
				if(StringUtils.isNotBlank(car.getCarGearbox()) && StringUtils.isNotBlank(reserveCarVo.getCarGearbox())){
					if(!StringUtils.equals(car.getCarGearbox(), reserveCarVo.getCarGearbox())){
						continue;
					}
				}
				JsonObject josn = new JsonObject();
		    	josn.addProperty("type", "reserve");
		    	josn.addProperty("id", reserveCarVo.getReserveId());
		    	//发起推送
		    	//PushtoSingle.uniPushOffLine(reserveCarVo.getDeviceCode(), title, text, josn);
				//开始添加
				List<String> aliasList = Lists.newArrayList();
				aliasList.add(reserveCarVo.getDeviceCode());
				String msg = "用户：" + reserveCarVo.getCusNick() +"，设备码" + reserveCarVo.getDeviceCode();
				int rel = JPushUtil.sendToAliasList(aliasList, title, text, text, josn.toString());
				
				if(rel == 1){
					msg += " 极光推送成功";
				}else{
					msg += " 极光推送失败";
				}
				log.info(msg);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("推送处理异常：" + ex.getMessage());
		}

	}

	/**
	 * 处理微店和车辆中间表，新建
	 * @param cus
	 * @param car
	 */
	private void storeCarHandle(Customer cus, Car car) {
		CusStore cusStore = cusStoreMapper.findCusStoreByCusId(cus.getCusId());
		// 判断是否写入微店和车辆中间表
		if (cusStore == null || car.getMicroShop() == 2) {
			return;
		}
		// 写入中间表信息 TODO
		StoreCar storeCar = new StoreCar(cusStore.getStoreId(), car.getCarId(), cus.getCusId(), new Date());
		storeCarMapper.insertStoreCar(storeCar);
	}

	/**
	 * 处理微店和车辆中间表，修改
	 * @param cus
	 * @param car
	 * @param oldCar
	 */
	private void updateStoreCarHandle(Customer cus, Car car,Car oldCar) {
		// 判断是否写入微店和车辆中间表
		CusStore cusStore = cusStoreMapper.findCusStoreByCusId(cus.getCusId());
		if (cusStore == null) {
			return;
		}
		//两个都不发布到微店，不做处理
		if(car.getMicroShop() == 2 && oldCar.getMicroShop() == 2){
			return;
		}
		//两个都是发布到微店，不做处理
		if(car.getMicroShop() == 1 && oldCar.getMicroShop() == 1){
			return;
		}
		
		//取消掉原来的关联
		if(car.getMicroShop() == 2 && oldCar.getMicroShop() == 1){
			//先进入查询是否存在
			StoreCar storeCarOld = storeCarMapper.getStoreIdByAll(car.getCarId(), cusStore.getStoreId());
			if(storeCarOld != null){
				storeCarMapper.deleteStoreCarById(storeCarOld.getStoreCarId());
			}
			return ;
		}
		//原来没有，现在有了
		if(car.getMicroShop() == 1 && oldCar.getMicroShop() == 2){
			StoreCar storeCar = new StoreCar(cus.getStoreId(), car.getCarId(), cus.getCusId(), new Date());
			storeCarMapper.insertStoreCar(storeCar);
		}
	}
	
	@Override
	@Transactional
	public void updateCar(Car car) {
		// 状态判断已售和下架不能修改信息
		Car oldCar = carMapper.carInfoById(car.getCarId());
		if (oldCar == null || oldCar.getCarStatus() == 2 || oldCar.getCarStatus() == 3) {
			throw new PmoException(ResultState.PARAM_ERROR, "只有未上架和在售可修改");
		}
		// 判断状态
		carStatusHandle(car);
		//carImgHandle(car);
		// 计算车龄
		carAgeCalculation(car);
		car.setUpdateTime(new Date());
		// 修改数据
		carMapper.updateCarById(car);
		// 处理微店和车辆中间表
		Customer cus = CusUtils.getCustomer();
		//处理中间名
		updateStoreCarHandle(cus,car,oldCar);
	}

	@Override
	public Car carInfo(Integer carId) {
		// 状态判断,不为初始状态不提供修改
		Car car = carMapper.carInfoById(carId);
		Customer cus = CusUtils.getCustomer();
		car.setIsHandle(true);
		if (cus.getCusType() == 1 && car.getCreator() != cus.getCusId()) {
			car.setIsHandle(false);
		}
		Customer createCus = customerMapper.getCustomerById(car.getCreator());
		car.setCusNick(createCus.getCusNick());
		//已下架才查询成交价 
		if(car.getCarStatus() == 2){
			CarDeal carDeal = carDealMapper.getCarDealByIdAndType(carId, 1);
			if(carDeal != null){
				car.setName(carDeal.getName());
				car.setPhone(carDeal.getPhone());
				car.setTransactionPrice(carDeal.getTransactionPrice());
				car.setSellType(carDeal.getSellType());
			}
		}
		
		
		return car;
	}

	@Override
	public Map<String, Object> showCarInfo(Integer carId) {
		// 本身车辆基础信息
		CarInfoVo carInfoVo = carMapper.showCarInfoById(carId, 1, null);
		if (carInfoVo == null) {
			throw new PmoException(ResultState.CAR_INFO_ERROR);
		}
		// 个人车辆总数
		Integer carSize = 0;
		CusStore cusStore = cusStoreMapper.findCusStoreByCusId(carInfoVo.getCreator());
		if (cusStore != null) {
			List<Integer> cusIds = cusStoreMapper.findeCusIdsById(cusStore.getStoreId());
			carSize = carMapper.carSizeByCusId(null, cusIds);
		} else {
			carSize = carMapper.carSizeByCusId(carInfoVo.getCreator(), null);
		}
		carInfoVo.setCarSize(carSize);
		// isShare 判断分享链接还是一般查看；分享进入查看是否发布到微店，微店情况，计算出车辆总数
		// 匹配车辆：匹配车辆
		List<FindCarVO> vos = carMapper.showCarInfosByName(carInfoVo.getBrandSeries(), carId);

		// 获取车辆创建者的头像和昵称
		Customer customer = customerMapper.getCustomerById(carInfoVo.getCreator());
		// 不显示手机号
		// customer.setCusPhone(null);

		Map<String, Object> map = Maps.newHashMap();
		map.put("carInfo", carInfoVo);
		map.put("matched", vos);
		map.put("creater", customer);

		// 默认为false
		boolean isStoreCus = false;

		// 只有登录后才进行以下操作
		if (CusUtils.isLogin()) {
			// 判断当前用户是否是当前车辆的微店的成员
			if(carInfoVo.getCreator() == CusUtils.getCusId()){
				isStoreCus = true;
			}
			//判断微店
			if(!isStoreCus){
				Integer storeId = storeCarMapper.getStoreIdByCarId(carId);
				if(storeId != null){
					isStoreCus = cusStoreMapper.isStoreCus(CusUtils.getCusId(), storeId);
				}
			}
			
		}

		map.put("isStoreCus", isStoreCus);

		return map;
	}

	@Override
	public PageInfo<FindCarVO> cusCarList(Integer cusId, Integer pageNumKey, Integer pageSizeKey) {
		if (pageNumKey == null)
			pageNumKey = 1;
		if (pageSizeKey == null)
			pageSizeKey = 10;
		PageHelper.startPage(pageNumKey, pageSizeKey);
		PageInfo<FindCarVO> pageInfo = new PageInfo<FindCarVO>();
		CusStore cusStore = cusStoreMapper.findCusStoreByCusId(cusId);
		if (cusStore != null) {
			List<Integer> cusIds = cusStoreMapper.findeCusIdsById(cusStore.getStoreId());
			List<FindCarVO> vos = carMapper.getCarByCusId(null, cusIds);
			pageInfo.setList(vos);
			return pageInfo;
		}

		List<FindCarVO> vos = carMapper.getCarByCusId(cusId, null);
		pageInfo.setList(vos);
		return pageInfo;
	}

	@Override
	public PageInfo<FindCarVO> findCar(FindCarQueryVO query) {

		query.setPlatform(1);
		query.setDelFlag(0);
		query.setCarStatus(1);
 
		//地区和时间处理
		carAgeDateHandle(query);

		
		// 分页处理
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());

		List<FindCarVO> carList = carMapper.findCar(query);
		
		//根据推广和刷新排序
		//sortByPromoteAndFlush(query.getSortType(), carList);

		PageInfo<FindCarVO> pageInfo = new PageInfo<FindCarVO>(carList);

		return pageInfo;
	}
	/***
	 * 地区和时间处理
	 * @param query
	 */
	private void carAgeDateHandle(FindCarQueryVO query){
		if(StringUtils.isNotBlank(query.getCityLocation()) && StringUtils.equals(query.getCityLocation(), "全国")){
			query.setCityLocation(null);
		}
		 Date date = new Date();
		 //开始时间处理,两个时间都有
		 if(query.getStartAge() != null && query.getEndAge() != null){
				//时间处理，开始时间
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(date);//设置起时间
				cal.add(Calendar.YEAR, -query.getStartAge());
				query.setEndAgeTime(cal.getTime());
				
				Calendar calend = Calendar.getInstance(); 
				 calend.setTime(date);//设置起时间
				 calend.add(Calendar.YEAR, -query.getEndAge());
				 query.setStartAgeTime(calend.getTime());
				
		 }
		 //有开始时间，没有结束时间
		 if(query.getStartAge() != null && query.getEndAge() == null){
			 if(query.getAgeBelow()){
				//时间处理，开始时间
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(date);//设置起时间
					cal.add(Calendar.YEAR, -query.getStartAge());
					query.setStartAgeTime(cal.getTime()); 
					query.setEndAgeTime(date);
			 }else{
				 Calendar cal = Calendar.getInstance(); 
					cal.setTime(date);//设置起时间
					cal.add(Calendar.YEAR, -query.getStartAge());
					query.setStartAgeTime(cal.getTime());  
			 }
		 }
		 
		
	}
	
	/*private void sortByPromoteAndFlush(String sortTypeStr, List<FindCarVO> carList) {
		
		Integer sortType = 0;
		
		if ("create_time".equals(sortTypeStr)) {
			sortType = 1;
		} else if ("age".equals(sortTypeStr)) {
			sortType = 2;
		} else if ("price".equals(sortTypeStr)) {
			sortType = 0;
		}

		final Integer finalSortType = sortType;

		carList.sort((a, b) -> {

			//如果双方都不是推广和刷新则不改变
			if(a.getExtensionType() == null && b.getExtensionType() == null ) {
				return 0;
			}
			
			//如果是根据价格排序且价格不相等 则不改变
			if (finalSortType == 0) {
				if(a.getInterPrice() ==null || b.getInterPrice() == null) {
					return 0;
				} else if(a.getInterPrice() !=null && b.getInterPrice() != null){
					boolean eq = a.getInterPrice().equals(b.getInterPrice());
					if (!eq) {
						return 0;
					}
				}
			}else  if (finalSortType == 1) {
				//如果是根据时间排序且时间不相等 则不改变
				boolean eq = a.getCreateTime().equals(b.getCreateTime());
				if (!eq) {
					return 0;
				}
			}else if(finalSortType == 2) {
				if(a.getCarAge() ==null || b.getCarAge() == null) {
					return 0;
				} else if(a.getCarAge() !=null && b.getCarAge() != null){
					boolean eq = a.getCarAge().equals(b.getCarAge());
					if (!eq) {
						return 0;
					}
				}
			}

			if(a.getExtensionType() != null && b.getExtensionType() ==null) {
				//如果a为推广或刷新 b都不是,则a优先于b
				return -1;
			}else if(a.getExtensionType() == null && b.getExtensionType() != null){
				//否则b优先于a
				return 1;
			}
			 
			boolean levelType = a.getExtensionType().equals(b.getExtensionType());

			if(levelType) {
				//如果同是推广或同是刷新 则根据最后推广刷新或时间进行排序
				return a.getLastExtensionDate().after(b.getLastExtensionDate()) ? -1 : 1;
			}
			
			//如果a为推广 则a优先于b
			if(a.getExtensionType().equals(0)) {
				return -1;
			}

			//否则b为推广 b优先于a
			return 1;
		});
	}*/

	/***
	 * 车辆管理分页
	 */
	@Override
	public PageInfo<FindCarVO> carList(Integer carStatus, String keyword, Integer pageNumKey, Integer pageSizeKey) {
		if (pageNumKey == null)
			pageNumKey = 1;
		if (pageSizeKey == null)
			pageSizeKey = 10;
		// 获取用户
		PageHelper.startPage(pageNumKey, pageSizeKey);
		Customer cus = CusUtils.getCustomer();
		List<FindCarVO> vos = null;
		if (cus.getCusType() != 2) {
			List<Integer> cusIds = cusStoreMapper.findeCusIdsById(cus.getStoreId());
			vos = carMapper.getCarListByStatusAndId(cusIds, null, carStatus, keyword);
			// 店主不管,店员做处理
			if (CollectionUtils.isNotEmpty(vos)) {
				for (FindCarVO vo : vos) {
					vo.setStoreId(cus.getStoreId());
					if(cus.getCusType() == 0){
						vo.setIsHandle(true);
						continue;
					}
					if (cus.getCusId() != vo.getCreator()) {
						vo.setIsHandle(false);
					}
				}
			}
		} else {
			vos = carMapper.getCarListByStatusAndId(null, cus.getCusId(), carStatus, keyword);
		}

		PageInfo<FindCarVO> info = new PageInfo<>(vos);
		return info;
	}

	@Override
	@Transactional
	public void saveCarDeal(CarLowerVo lowerVo) {
		Car car = carMapper.carInfoById(lowerVo.getCarId());
		Customer cus = CusUtils.getCustomer();
		// 判断是不是在售,是不是她本人操作
		if (car == null || car.getCarStatus() != 1) {
			throw new PmoException(ResultState.CAR_STATUS_ERROR);
		}
		// 是在售修改状态为已售
		carMapper.updateCarStatusById(lowerVo.getCarId(), 2);
		// 写入成交价表
		CarDeal deal = new CarDeal(car.getCarId(), lowerVo.getTransactionPrice(), 1, lowerVo.getSellType(),
				lowerVo.getName(), lowerVo.getPhone(), cus.getCusId(), new Date());
		carDealMapper.insertCarDeal(deal);
	}

	@Override
	public Map<String, Object> depositCarInfo(Integer carId) {
		Car car = carMapper.getDepositCarById(carId);
		// 车辆为空
		if (car == null) {
			throw new PmoException(ResultState.CAR_INFO_ERROR);
		}
		// 车辆已有订单加身
		Customer cus = CusUtils.getCustomer();
		// 自己不能给自己下订金
		if (cus.getCusId() == car.getCreator()) {
			throw new PmoException(ResultState.CUS_DEPOSIT_ERROR);
		}
		// 买方
		Customer buyer = new Customer(cus.getCusId(), cus.getCusPhone(), cus.getCusNick(), cus.getCusAvatar());
		// 买方
		Customer seller = customerMapper.getCustomerById(car.getCreator());
		// 订金基本信息
		Deposit deposit = depositMapper.getDeposit();
		Map<String, Object> map = Maps.newHashMap();
		map.put("buyer", buyer);
		map.put("seller", seller);
		map.put("deposit", deposit);
		map.put("car", car);
		return map;
	}

	@Override
	public List<FindCarVO> favoriteCarList() {
		Integer cusId = null;
		if(CusUtils.isLogin()){
			cusId = CusUtils.getCusId();
		}
		return carMapper.favoriteCarList(cusId);
	}

	@Override
	public CarCountByStatusVO carCountByStatus() {
		Customer customer = CusUtils.getCustomer();
		List<Integer> cusIds = Lists.newArrayList();
		//不是普通用户
		if(customer.getCusType() != 2){
			 cusIds = cusStoreMapper.findeCusIdsById(customer.getStoreId());
		}else{
			cusIds.add(customer.getCusId());
		}
		return carMapper.carCountByStatus(cusIds);
	}

	@Override
	public List<FindCarVO> shareCarListByCarIds(String carIds) {

		return carMapper.shareCarListByCarIds(carIds);
	}

}
