package com.micro.pmo.moudle.appraisal.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.appraisal.entity.Appraisal;
/**
 * 车估价Service
 */
public interface AppraisalService{
	
	/***车估价信息保存***/
	public int intserAppraisal(Appraisal appraisal);
	
	/**车估价列表**/
	public PageInfo<Appraisal> appraisalPage(Integer pageNumKey,Integer pageSizeKey);
}
