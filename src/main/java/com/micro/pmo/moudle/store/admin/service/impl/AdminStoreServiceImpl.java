package com.micro.pmo.moudle.store.admin.service.impl;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月15日 
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.mapper.AdminCarMapper;
import com.micro.pmo.mapper.AdminStoreMapper;
import com.micro.pmo.mapper.CustomerMapper;
import com.micro.pmo.mapper.StoreMapper;
import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.store.admin.service.AdminStoreService;
import com.micro.pmo.moudle.store.admin.vo.AdminStoreQueryVO;
import com.micro.pmo.moudle.store.entity.Store;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.utils.UserUtils;

@Service
public class AdminStoreServiceImpl implements AdminStoreService {

	@Autowired
	private AdminCarMapper adminCarMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private AdminStoreMapper adminStoreMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Override
	public PageInfo<Store> storePage(AdminStoreQueryVO query) {

		User user = UserUtils.getUser();

		// 如果不是超级管理员 就设置当前用户区域为查询条件
		if (user.getUserType() != 0) {
			String city = user.getCity();
			//String province = user.getProvince();

			query.setCityName(city);
			//query.setProvinceName(province);
		}

		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());

		List<Store> storeList = adminStoreMapper.storeList(query);

		PageInfo<Store> storePage = new PageInfo<Store>(storeList);

		return storePage;
	}

	@Override
	public List<Customer> cusListByStoreId(Integer storeId) {
		return customerMapper.cusListByStoreId(storeId);
	}

	@Override
	public Store getStoreByStoreId(Integer storeId) {
		Store store = storeMapper.storeInfoById(storeId);
		//初始化微店员工列表
		store.setCusList(customerMapper.cusListByStoreId(storeId));
		
		AdminCarQueryVO query = new AdminCarQueryVO();
		query.setStoreId(storeId);
		
		List<Car> carList = adminCarMapper.carList(query);
		
		store.setCarList(carList);
		return store;
	}

	@Override
	public PageInfo<Car> carPage(AdminCarQueryVO query) {
		User user = UserUtils.getUser();

		// 如果不是超级管理员 就设置当前用户区域为查询条件
		if (user.getUserType() != 0) {
			String city = user.getCity();
			String province = user.getProvince();

			query.setCityName(city);
			query.setProvinceName(province);
		}

		PageHelper.startPage(query.getPageNumKey(), query.getPageSizeKey());

		List<Car> carList = adminCarMapper.carList(query);

		PageInfo<Car> pageInfo = new PageInfo<Car>(carList);

		return pageInfo;
	}

}
