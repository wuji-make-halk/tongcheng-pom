package com.micro.pmo.mapper;

import java.util.List;

import com.micro.pmo.moudle.third.entity.ThirdSearchRecord;

/**
 * ThirdSearchRecord 
 * @author wenhaofan
 * @createtime 
 */
public interface ThirdSearchRecordMapper {
 
 	/**
 	* 保存ThirdSearchRecord
 	* @param  thirdSearchRecord
	* @return
 	**/
 	public int insertThirdSearchRecord(ThirdSearchRecord thirdSearchRecord);
 	
    /**
       * 根据ID删除ThirdSearchRecord，假删除
    * @param thirdSearchRecordId
	* @return
    **/
	public int deleteThirdSearchRecordById(Integer thirdSearchRecordId);
	
	/**
	* 根据ID更新ThirdSearchRecord
	* @param thirdSearchRecord
	* @return
	**/
	public int updateThirdSearchRecordById(ThirdSearchRecord thirdSearchRecord);
	
	/**
	* 根据ID获取ThirdSearchRecord
	* @param thirdSearchRecordId
	* @return
	**/
	public ThirdSearchRecord getThirdSearchRecordById(int thirdSearchRecordId);
	
	/**
	* 查询获取ThirdSearchRecord
	* @return
	**/
	public List<ThirdSearchRecord> thirdSearchRecordList();
}