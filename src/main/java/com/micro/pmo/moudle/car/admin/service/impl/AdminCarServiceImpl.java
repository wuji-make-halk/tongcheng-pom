package com.micro.pmo.moudle.car.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.mapper.AdminCarMapper;
import com.micro.pmo.mapper.CarDealMapper;
import com.micro.pmo.mapper.CarExtensionMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.moudle.car.admin.service.AdminCarService;
import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.car.entity.CarDeal;
import com.micro.pmo.moudle.car.entity.CarExtension;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.utils.UserUtils;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月12日
 */
@Service
public class AdminCarServiceImpl implements AdminCarService {

	@Autowired
	private AdminCarMapper adminCarMapper;

	@Autowired
	private CarDealMapper carDealMapper;

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CarExtensionMapper carExtensionMapper;
	
	@Override
	public void deleteCarByCarId(Integer carId) {
		adminCarMapper.deleteCarByCarId(carId);
	}

	@Override
	public PageInfo<Car> carList(AdminCarQueryVO query) {

		User user = UserUtils.getUser();

		// 如果不是超级管理员 就设置当前用户区域为查询条件
		if (user.getUserType() != 0) {
			String city = user.getCity();
			query.setCityName(city);
		}
		
		if(query.getDelFlag() == null ) {
			query.setDelFlag(0);
		}

		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());

		List<Car> carList = adminCarMapper.carList(query);

		PageInfo<Car> pageInfo = new PageInfo<Car>(carList);

		return pageInfo;
	}

	@Override
	public Kv<String,Object> getCarInfoById(Integer carId) {
		Car car = adminCarMapper.getCarInfoById(carId);
		
		Customer cus = customerMapper.getCustomerById(car.getCreator());
		
		Kv<String,Object> result = Kv.by("car", car);
		//推广和刷新处理展示 
		result.set("extension", carExtensionHandle(carId));
		return result.set("creator", cus);
	}
	
	private Map<String,Object> carExtensionHandle(Integer carId){
		//0推广 1为刷新 
		Map<String,Object> map = Maps.newHashMap();
		//推广
		Integer type = 0;
		Date nowDate = new Date();
		CarExtension carExtension = carExtensionMapper.getValidExtension(carId, type);
		if(carExtension != null){
			if(carExtension.getExtensionExpirationDate().getTime() > nowDate.getTime()){
				map.put("type", type);
				map.put("expirationDate", carExtension.getExtensionExpirationDate());
				return map;
			}
		}
		//刷新 
		type = 1;
		carExtension = carExtensionMapper.getValidExtension(carId, type);
		if(carExtension != null){
			if(carExtension.getExtensionExpirationDate().getTime() > nowDate.getTime()){
				map.put("type", type);
				map.put("expirationDate", carExtension.getExtensionExpirationDate());
				return map;
			}
		}
		//都没有
		type= 2;
		map.put("type", type);
		map.put("expirationDate", "");
		return map;
		
	}
	
	
	@Override
	public void saveCarDeal(CarDeal carDeal) {
		Integer userId = UserUtils.getUser().getUserId();
		carDeal.setCreator(userId);
		carDeal.setDealType(2);
		carDeal.setCreateTime(new Date());
		carDealMapper.insertCarDeal(carDeal);
	}

	@Override
	public List<CarDeal> listCarDealByCarId(Integer carId) {
		return carDealMapper.listCarDealByCarId(carId); 
	}

}
