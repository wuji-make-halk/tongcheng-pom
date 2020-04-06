package com.micro.pmo.moudle.customer.admin.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.customer.entity.CounterRecord;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月9日 
*/
public interface AdminCounterRecordService {
 
	
	/**
	 * 分页查询赠送记录
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	PageInfo<CounterRecord> counterRecordPage(Integer pageNumKey, Integer pageSizeKey);

}