package com.micro.pmo.moudle.third.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.pmo.mapper.ThirdApiResultMapper;
import com.micro.pmo.mapper.ThirdSearchRecordMapper;
import com.micro.pmo.moudle.third.entity.ThirdApiResult;
import com.micro.pmo.moudle.third.entity.ThirdSearchRecord;
import com.micro.pmo.moudle.third.enu.ThirdResEnum;
import com.micro.pmo.moudle.third.enu.ThirdTypeEnum;
import com.micro.pmo.moudle.third.service.ThirdApiResultService;

/**
 * @Author WangQiLong
 * @Date 2019/7/24 下午2:15
 **/
@Service
public class ThirdApiResultServiceImpl implements ThirdApiResultService {

	@Autowired
	private ThirdApiResultMapper thirdApiResultMapper;

	@Autowired
	private ThirdSearchRecordMapper thirdSearchRecordMapper;

	/**
	 * 通过车架号(vin)获取有效报告
	 * 
	 * @param vin
	 * @return
	 */
	@Override
	public ThirdApiResult getLastThirdApiResult(Integer cusId, String vin, ThirdTypeEnum resultType) {
		// 构造查询参数
		ThirdApiResult openApiResult = new ThirdApiResult(cusId, vin, resultType.getCode(), resultType.getMaxDay(),ThirdResEnum.SUCCESS.getStatus());
		return thirdApiResultMapper.getThirdApiResult(openApiResult);

	}

	@Override
	public ThirdApiResult getLastThirdApiResult(String vin, ThirdTypeEnum resultType) {
		return getLastThirdApiResult(null, vin, resultType);
	}

	@Override
	public void saveApiResultAndSearchRecord(ThirdApiResult result) {
		saveApiResult(result);

		Integer resultId = result.getResultId();

		// 保存搜索记录
		ThirdSearchRecord thirdSearchRecord = new ThirdSearchRecord(resultId, result.getCusId(), new Date(),
				new Date());

		thirdSearchRecordMapper.insertThirdSearchRecord(thirdSearchRecord);
	}

	@Override
	public void saveApiResult(ThirdApiResult result) {
		thirdApiResultMapper.insertThirdApiResult(result);
	}

}
