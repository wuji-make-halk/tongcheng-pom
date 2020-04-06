package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.share.entity.Share;

/**
 * 分享Mapper接口
 */
public interface ShareMapper{
	
	/***
	 * 批量写入
	 * @param shares
	 */
	@Insert({"<script>"
			+"INSERT INTO `share` (share_type,share_name,car_id,store_id,share_photo,cus_id,share_time) VALUES "
			+ "<foreach collection='shares' item='item' separator=','>"
			+    "(#{item.shareType}, #{item.shareName},#{item.carId},#{item.storeId},#{item.sharePhoto},#{item.cusId},#{item.shareTime})"
			+"</foreach>"
		+ "</script>"})
	public void batchShare(@Param("shares") List<Share> shares);
	
	/***
	 * 分享查询记录
	 * @param shareType
	 * @param carId
	 * @param storeId
	 * @param cusId
	 * @return
	 */
	public Share getShareByIdAndType(@Param("shareType") Integer shareType,@Param("carId") Integer carId,@Param("storeId") Integer storeId,@Param("cusId") Integer cusId);
	
	/**
	 * 用户所有分享记录
	 * @param cusId
	 * @return
	 */
	public List<Share> findSharesByCusId(@Param("cusId") Integer cusId);
	
	/***
	 * 获取分享信息
	 * @param shareId
	 * @return
	 */
	public Share getShareById(@Param("shareId")Integer shareId);
	
}
