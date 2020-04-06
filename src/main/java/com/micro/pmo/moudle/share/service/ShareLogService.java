package com.micro.pmo.moudle.share.service;

import java.util.List;
import java.util.Map;

import com.micro.pmo.moudle.share.entity.ShareLog;
import com.micro.pmo.moudle.share.vo.ShareLogVo;

/**
 * 分享记录Service
 */
public interface ShareLogService{
	
	/***
	 * 分享保存
	 * @param shareLog
	 */
	public void saveShareLog(ShareLog shareLog);
	
	/***
	 * 本周分享初始
	 * @return
	 */
	public Map<String,Object> weekCounts();
	
	/***
	 * 分享记录排名
	 * @return
	 */
	public List<ShareLogVo> findShareLog();
}
