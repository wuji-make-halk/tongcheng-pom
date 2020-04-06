package com.micro.pmo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.share.entity.ShareLog;
import com.micro.pmo.moudle.share.vo.ShareLogVo;

/**
 * 分享记录Mapper接口
 */
public interface ShareLogMapper{
	
	/***
	 * 分享记录保存
	 * @param shareLog
	 * @return
	 */
	public int insertShareLong(ShareLog shareLog);
	
	/***
	 * 分享查询条数
	 * @param cusIds
	 * @param cusId
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public Integer weekCount(@Param("cusIds")List<Integer> cusIds,@Param("cusId")Integer cusId,@Param("minDate")Date minDate,@Param("maxDate")Date maxDate);
	
	/***
	 * 分享排序
	 * @param cusIds
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public List<ShareLogVo> findShareLog(@Param("cusIds")List<Integer> cusIds,@Param("minDate")Date minDate,@Param("maxDate")Date maxDate);
	
	/***
	 * 获取分享最新头像
	 * @param cusId
	 * @return
	 */
	public String getShareCusHead(@Param("cusId")Integer cusId);
}
