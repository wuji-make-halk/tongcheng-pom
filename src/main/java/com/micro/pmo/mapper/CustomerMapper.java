package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.customer.admin.vo.AdminCusQuery;
import com.micro.pmo.moudle.customer.entity.Customer;

/**
 * app用户Mapper接口
 */
public interface CustomerMapper{
	
	/***通过电话号码查找用户**/
	public Customer findCusByPhone(@Param("cusPhone") String cusPhone);
	
	/***插入数据**/
	public int insertCus(Customer customer);
	
	/**修改token***/
	@Update("UPDATE customer SET cus_token = #{cusToken} WHERE cus_id =#{cusId}")
	public void updateCusToken(@Param("cusId") Integer cusId,@Param("cusToken") String cusToken);
	
	/**修改修改设备号***/
	@Update("UPDATE customer SET device_code = #{deviceCode} ,device_type = #{deviceType} WHERE cus_id = #{cusId}")
	public void updateCusDeviceCode(@Param("cusId") Integer cusId,@Param("deviceCode") String deviceCode,@Param("deviceType") Integer deviceType);
	
	/***记录openId****/
	@Update("UPDATE customer SET union_id = #{unionId},open_id = #{openId} WHERE cus_id = #{cusId}")
	public void updateCusOpenId(@Param("cusId") Integer cusId,@Param("openId") String openId,@Param("unionId") String unionId);
	/***记录地区和openId**/
	@Update("UPDATE customer SET union_id = #{unionId},open_id = #{openId},province_name = #{provinceName} ,city_name=#{cityName} WHERE cus_id = #{cusId}")
	public void updateCusOpenIdAndLoc(@Param("cusId") Integer cusId,@Param("openId") String openId,@Param("unionId") String unionId,@Param("provinceName") String provinceName,@Param("cityName") String cityName);
	
	/**通过token查找cus数据，带有微店的信息**/
	public Customer findCusByToken(@Param("cusToken") String cusToken);
	
	/**
	 * Description 查询所有用户列表
	 * @author dsn
	 * @return
	 */
	public List<Customer> cusList(AdminCusQuery query);
	
	/**
	 * 修改用户信息
	 * @param cusId
	 * @param cusStatus
	 * @return
	 */
	public int updateCus(Customer customer);
	
	/**
	 * 根据id获取用户信息
	 * @param cusId
	 * @return
	 */
	public Customer getCustomerById(Integer cusId);
	
	/**
	 * 获取用户的所有信息
	 * @param cusId
	 * @return
	 */
	public Customer getCusAllById(Integer cusId);
	
	/**
	 * 获取微店下的所有员工
	 * @param storeId
	 * @return
	 */
	public List<Customer> cusListByStoreId(@Param("storeId")Integer storeId);
	
	/**
	 *  根据用户id获取头像 
	 * @param cusId
	 * @return
	 */
	public Customer getAvatarById(Integer cusId);
	
	/**
	 * 通过unionId查找用户 
	 * @param unionId
	 * @return
	 */
	public List<Customer> getCusByOpenIdAndUnionId(@Param("openId") String openId,@Param("unionId") String unionId);
	
	/**
	 * 根据用户id获取openId
	 * @param cusId
	 * @return
	 */
	public String getOpenIdByCusId(Integer cusId);
}
