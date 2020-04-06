package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.pmo.moudle.share.entity.ShareBrowse;

/**
 * 分享浏览Mapper接口
 */
public interface ShareBrowseMapper{
	
	/***
	 * 保存记录
	 * @param shareBrowse
	 * @return
	 */
	public int insertShareBrowse(ShareBrowse shareBrowse);
	
	/***
	 * 查询单条记录，防止重复记录
	 * @param shareId
	 * @param cusIp
	 * @param cusNick
	 * @return
	 */
	public ShareBrowse findShareBrowseByIp(@Param("shareId")Integer shareId,@Param("cusIp")String cusIp);
	
	/***
	 * 分享人员查看列表
	 * @param shareId
	 * @return
	 */
	public List<ShareBrowse> findShareBrowseByShareId(@Param("shareId")Integer shareId);
}
