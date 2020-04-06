package com.micro.pmo.moudle.customer.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CollectCarMapper;
import com.micro.pmo.mapper.CusCounterMapper;
import com.micro.pmo.mapper.CusVipMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.car.entity.CollectCar;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.admin.service.AdminCusService;
import com.micro.pmo.moudle.customer.admin.vo.AdminCusQuery;
import com.micro.pmo.moudle.customer.admin.vo.CusLocationVo;
import com.micro.pmo.moudle.customer.entity.CusCounter;
import com.micro.pmo.moudle.customer.entity.CusVip;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.user.utils.UserUtils;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月16日 
*/
@Service
public class AdminCusServiceImpl implements AdminCusService{

	@Autowired
	private CustomerMapper customerMapper;
	
	
	@Autowired
	private CusVipMapper cusVipMapper;
	
	@Autowired
	private CollectCarMapper collectCarMapper;

	
	@Autowired
	private CusCounterMapper cusCounterMapper;
	
	@Override
	public PageInfo<Customer> cusList(AdminCusQuery query) {
		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());
		//用户判断
		String city = UserUtils.getUserCity();
		if(StringUtils.isNotBlank(city)){
			query.setCity(city);
		}
		List<Customer> cusList = customerMapper.cusList(query);
		
		PageInfo<Customer> page = new PageInfo<Customer>(cusList);
		
		return page;
	}

	@Override
	public void updateCus(CusLocationVo cusLocationVo) {
		Customer cus = customerMapper.getCusAllById(cusLocationVo.getCusId());
		if(cus == null){
			throw new PmoException(ResultState.PARAM_ERROR, "用户未找到");
		}
		cus.setCityName(cusLocationVo.getCityName());
		customerMapper.updateCus(cus);
	}

	@Override
	public Map<String,Object> getCusByCusId(Integer cusId) {
		Map<String,Object> map = Maps.newHashMap();
		//装入用户信息
		Customer cus = customerMapper.getCusAllById(cusId);
		map.put("cus", cus);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//vip 时间
		Date vipDate = cusVipMapper.getEndDate(cusId);
		Map<String, Object> vipMap = Maps.newHashMap(); 
		if(vipDate == null || vipDate.getTime() < new Date().getTime()){
			vipMap.put("isVip", false);
			vipMap.put("failureTime", null);
		}else{
			vipMap.put("isVip", true);
			vipMap.put("failureTime", formatter.format(vipDate));
		}
		map.put("vip", vipMap);
		//预定收车时间，心愿单时间
		Map<String,Object> reserveMap = Maps.newHashMap();
		CollectCar collectCar = collectCarMapper.getCollectCarByCusId(cusId);
		Boolean isReserve = false;
		String expirationDate = null;
		if(collectCar != null && collectCar.getExpirationDate() != null && collectCar.getExpirationDate().getTime() > new Date().getTime()){
			isReserve = true;
			expirationDate = formatter.format(collectCar.getExpirationDate());
		}
		reserveMap.put("isReserve", isReserve);
		reserveMap.put("expirationDate", expirationDate);
		map.put("reserve", reserveMap);
		//查成交价次数，使用次数 和剩余次数
		CusCounter cusDeal = cusCounterMapper.getValidCusCounterByTypeAndCusId(CounterTypeEnum.queryPrice, cusId);
		cusDeal = frequencyHandle(cusDeal,CounterTypeEnum.queryPrice);
		map.put("cusDeal", cusDeal);
		//4S维保次数，使用次数 和剩余次数
		CusCounter cus4s = cusCounterMapper.getValidCusCounterByTypeAndCusId(CounterTypeEnum.maintenance, cusId);
		cus4s = frequencyHandle(cus4s,CounterTypeEnum.maintenance);
		map.put("cus4s", cus4s);
		//保险记录 使用次数 和剩余次数
		CusCounter cusCar = cusCounterMapper.getValidCusCounterByTypeAndCusId(CounterTypeEnum.insurance, cusId);
		cusCar = frequencyHandle(cusCar,CounterTypeEnum.insurance);
		map.put("cusCar", cusCar);
		//推广
		CusCounter cusPromotion = cusCounterMapper.getValidCusCounterByTypeAndCusId(CounterTypeEnum.promotion, cusId);
		cusPromotion = frequencyHandle(cusPromotion, CounterTypeEnum.promotion);
		map.put("cusPromotion", cusPromotion);
		//刷新
		CusCounter cusFlush = cusCounterMapper.getValidCusCounterByTypeAndCusId(CounterTypeEnum.flush, cusId);
		cusFlush = frequencyHandle(cusFlush, CounterTypeEnum.promotion);
		map.put("cusFlush", cusFlush);
		
		return map;
	}
	/***
	 * 次数处理
	 * @param cusCounter
	 * @return
	 */
	private CusCounter frequencyHandle(CusCounter cusCounter,CounterTypeEnum counterTypeEnum){
		if(cusCounter != null){
			if(cusCounter.getUseCount() == null){
				cusCounter.setUseCount(0);
			}
			return cusCounter;
		}
		cusCounter = new CusCounter();
		cusCounter.setTotalCount(0);
		cusCounter.setSurplusCount(0);
		cusCounter.setUseCount(0);
		cusCounter.setSendCount(0);
		cusCounter.setBuyCount(0);
		cusCounter.setCounterType(counterTypeEnum);
		return cusCounter;
	}
	
	
	@Override
	public void giveVip(Integer cusId, Integer dayNum) {
		
		//获取用户有效
		CusVip	cusVip = cusVipMapper.getValidCusVip(cusId);
		
		//获取vip新的结束时间
		Date endDate = getVipExpiredEndDate(dayNum, cusVip);
		
		CusVip newCusvip = new CusVip(endDate, null, cusId,new Date(),0, cusId);
		
		//如果有vip记录则更新vip有效时间
		if(cusVip!=null) {
			cusVip.setVipExpirationDate(endDate);
			cusVipMapper.updateVipExpirationDateById(cusVip);
		}else {
			//新增vip记录
			cusVipMapper.insertCusVip(newCusvip);
		}
		
		//更新缓存,先找到用户token
		Customer cus = customerMapper.getCustomerById(cusId);
		//不存在直接返回
		if(cus == null || StringUtils.isBlank(cus.getCusToken())){
			return ;
		}
		CusUtils.removeCusCache(cus.getCusToken());
		CusUtils.putCusCache(cus.getCusToken());
	}

	/**
	 *  计算一个新的vip结束
	 * @param dayNum
	 * @param cusVip
	 * @return
	 */
	private Date getVipExpiredEndDate(Integer dayNum, CusVip cusVip) {
		Date date = cusVip.getVipExpirationDate();
		
		if(date ==null||date.before(new Date())) {
			date=new Date();
		}
		
		Date endDate=DateUtil.datePlusDays(date, dayNum);
		
		return endDate;
	}
}
