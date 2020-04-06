package com.micro.pmo.moudle.car.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CollectCarMapper;
import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.mapper.ReserveCarMapper;
import com.micro.pmo.moudle.car.entity.CollectCar;
import com.micro.pmo.moudle.car.entity.ReserveCar;
import com.micro.pmo.moudle.car.service.ReserveCarService;
import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.InputCusCounterBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

@Service
public class ReserveCarServiceImpl implements ReserveCarService {

	@Autowired
	private ReserveCarMapper reserveCarMapper;

	@Autowired
	private CusCounterService cusCounterService;
	
	@Autowired
	private CollectCarMapper collectCarMapper;
	
	@Autowired
	private CounterConfigMapper counterConfigMapper;
	/***
	 * 用户失效时间
	 */
	@Override
	public Map<String, Object> reserveUseTime() {
		CollectCar collectCar = collectCarMapper.getCollectCarByCusId(CusUtils.getCusId());
		Boolean isReserve = false;
		String expirationDate = null;
		if(collectCar != null && collectCar.getExpirationDate() != null && collectCar.getExpirationDate().getTime() > new Date().getTime()){
			isReserve = true;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			expirationDate = formatter.format(collectCar.getExpirationDate());
		}
		//预订收车单位和价格
		CounterConfig counterConfig = counterConfigMapper.getValidCounterConfigByType(CounterTypeEnum.collectCar);
		Map<String,Object> map = Maps.newHashMap();
		map.put("isReserve", isReserve);
		map.put("expirationDate", expirationDate);
		map.put("configId", counterConfig.getConfigId());
		map.put("configName", counterConfig.getConfigName());
		map.put("configPrice", counterConfig.getConfigPrice());
		map.put("configUnit", counterConfig.getConfigUnit());
		return map;
	}
	/**
	 * 
	 * @param cusId
	 * @return
	 */
	private Boolean isReserveAdd(Integer cusId){
		CollectCar collectCar = collectCarMapper.getCollectCarByCusId(CusUtils.getCusId());
		Boolean isReserve = false;
		if(collectCar != null && collectCar.getExpirationDate() != null && collectCar.getExpirationDate().getTime() > new Date().getTime()){
			isReserve = true;
		}
		return isReserve;
	}
	
	/**
	 * 添加预定收车心愿
	 * 
	 * @param reserveCar
	 * @return
	 */
	@Override
	@Transactional
	public void insertReserveCar(ReserveCar reserveCar) {
		Customer cus = CusUtils.getCustomer();
		if(!isReserveAdd(cus.getCusId())){
			throw new PmoException(ResultState.RESERVE_USE_TIME);
		}
		reserveCar.setReserveStatus(0);
		reserveCar.setCreator(cus.getCusId());
		reserveCar.setCreateTime(new Date());
		int size = reserveCarMapper.getReserverByCusId(reserveCar.getCreator(), reserveCar.getReserveStatus());
		// 如果大于12条 直接返回false
		if (size >= 12) {
			throw new PmoException(ResultState.RESERVE_STATUS_NUMBER);
		}
		reserveCarMapper.insertReserve(reserveCar);
	}

	/**
	 * 根据创建人ID获取list数据
	 * 
	 * @param reserveStatus
	 * @return
	 */
	@Override
	public List<ReserveCar> findReserveByIdList() {
		Customer cus = CusUtils.getCustomer();
		Integer cusId = cus.getCusId();
		List<ReserveCar> list = reserveCarMapper.getReserveList(cusId, 0);
		return list;
	}

	/**
	 * 根据收车Id更新状态
	 * 
	 * @param reserveId
	 * @param reserveStatus
	 * @return
	 */
	@Override
	@Transactional
	public void updateReserveStatus(Integer reserveId) {
		ReserveCar reserveCar = reserveCarMapper.getReserver(reserveId);
		if (reserveCar.getReserveStatus() != 0) {
			throw new PmoException(ResultState.RESERVE_UPDATE_STATUS);
		}
		reserveCarMapper.updateReserveStatus(reserveId, 1, new Date());
	}

	@Override
	public ReserveCar reserveCarInfo(Integer reserveId) {

		return reserveCarMapper.getReserveCarById(reserveId);
	}

	@Override
	@Transactional
	public void updateReserveCar(ReserveCar reserveCar) {
		if (reserveCar.getReserveId() == null) {
			throw new PmoException(ResultState.PARAM_ERROR, "参数错误：[reserveId]心愿id不能为空");
		}
		//判断状态
		ReserveCar oldReserveCar = reserveCarMapper.getReserveCarById(reserveCar.getReserveId());
		if(oldReserveCar == null || oldReserveCar.getReserveStatus() != 0){
			throw new PmoException(ResultState.PARAM_ERROR, "心愿单只有在初始状态下可修改");
		}
		if(!isReserveAdd(oldReserveCar.getCreator())){
			throw new PmoException(ResultState.RESERVE_USE_TIME);
		}
		
		reserveCarMapper.updateReserveCar(reserveCar);
	}

	@Override
	public Map<String, Object> reserveMatchedInfo(Integer reserveId) {
		// 预订收车信息获取
		ReserveCar reserveCar = reserveCarMapper.getReserveCarById(reserveId);
		if (reserveCar == null) {
			throw new PmoException(ResultState.PARAM_ERROR, "心愿id错误");
		}
		//市的处理
		String regionCity = reserveCar.getRegionCity();
		if(StringUtils.isNotBlank(reserveCar.getRegion2City())){
			regionCity += "," + reserveCar.getRegion2City();
		}
		Integer carOldBoadTime = reserveCar.getCarOldBoadTime();
		//为空处理
		if(carOldBoadTime == null){
			carOldBoadTime = 0;
		}
		Date maxDate = new Date();
		Date minDate = null;
		if(carOldBoadTime != 0){
			if(carOldBoadTime == 7){
				carOldBoadTime = 6;
			}
			//上牌时间处理
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(maxDate);//设置起时间
		    cal.add(Calendar.YEAR, -carOldBoadTime);//减少几年
		    minDate = cal.getTime();
		}
		reserveCar.setMaxDate(maxDate);
		reserveCar.setMinDate(minDate);
		//设置查询的市
		reserveCar.setRegionProvince(regionCity);
		// 匹配车辆
		List<FindCarVO> vos = reserveCarMapper.reserveMatchingCar(reserveCar);
		Map<String, Object> map = Maps.newHashMap();
		map.put("reserveCarInfo", reserveCar);
		map.put("matched", vos);
		return map;
	}

	@Override
	public PayInfoVO buyReserve(InputCusCounterBuyVO input) {
		return cusCounterService.buy(input, CounterTypeEnum.collectCar);
	}


}
