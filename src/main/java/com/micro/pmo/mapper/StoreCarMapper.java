package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.car.vo.FindCarVO;
import com.micro.pmo.moudle.store.entity.StoreCar;

/**
 * 微店和车辆中间表Mapper接口
 */
public interface StoreCarMapper{
	
	/***中间表信息写入**/
	public int insertStoreCar(StoreCar storeCar);
	
	/**通过微店id和车id查找中间表信息***/
	public List<Integer> findCarIdsByCusId(@Param("cusId")Integer cusId);
	
	/***批量写入***/
	@Insert({"<script>"
			+"INSERT INTO store_car (store_id,car_id,creator,create_time) VALUES "
			+ "<foreach collection='storeCars' item='item' separator=','>"
			+    "(#{item.storeId}, #{item.carId}, #{item.creator}, #{item.createTime})"
			+"</foreach>"
		+ "</script>"})
	public void batchStoreCar(@Param("storeCars")List<StoreCar> storeCars);
	
	
	/**通过微店和关键字查找车辆列表,carIds 查询具体车辆**/
	public List<FindCarVO> storeCarByIdAndKey(@Param("storeId")Integer storeId,@Param("keyword")String keyword,@Param("carIds") String carIds);
	
	/***查询微店车辆条数***/
	public Integer getStoreCarSizeById(@Param("storeId")Integer storeId);
	
	/**
	 * 根据车辆id获取微店
	 * @param carId
	 * @return
	 */
	public Integer getStoreIdByCarId(@Param("carId")Integer carId);
	
	/***
	 * 检查是否已写入
	 * @param carId
	 * @param storeId
	 * @return
	 */
	public StoreCar getStoreIdByAll(@Param("carId")Integer carId,@Param("storeId")Integer storeId);
	
	/***
	 * 删除车辆和微店中间表
	 * @param storeCarId
	 * @return
	 */
	@Delete("DELETE FROM store_car WHERE store_car_id = #{storeCarId}")
	public int deleteStoreCarById(@Param("storeCarId")Integer storeCarId);
}
