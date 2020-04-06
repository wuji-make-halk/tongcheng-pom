package com.micro.pmo.moudle.store.admin.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.car.admin.vo.AdminCarQueryVO;
import com.micro.pmo.moudle.car.entity.Car;
import com.micro.pmo.moudle.customer.entity.Customer;
import com.micro.pmo.moudle.store.admin.vo.AdminStoreQueryVO;
import com.micro.pmo.moudle.store.entity.Store;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月15日 
*/
public interface AdminStoreService {

	/**
	 *  根据关键字，当前登录用户省市 分页查询 微店 
	 * @param query
	 * @return
	 */
	public PageInfo<Store> storePage(AdminStoreQueryVO query);
	
	/**
	 * 根据微店id查询微店下的所有员工
	 * @param storeId
	 * @return
	 */
	public List<Customer> cusListByStoreId(Integer storeId);
	
	/**
	 * 根据微店id获取微店详情
	 * @param storeId
	 * @return
	 */
	public Store getStoreByStoreId(Integer storeId);
	
	/**
	 * 根据微店id分页查询车辆
	 * @param query
	 * @return
	 */
	public PageInfo<Car> carPage(AdminCarQueryVO query);
	
}
