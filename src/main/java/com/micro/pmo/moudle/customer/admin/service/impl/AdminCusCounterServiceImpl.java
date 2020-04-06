package com.micro.pmo.moudle.customer.admin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.mapper.CounterRecordMapper;
import com.micro.pmo.mapper.CusCounterMapper;
import com.micro.pmo.moudle.car.service.CollectCarService;
import com.micro.pmo.moudle.config.admin.configEnum.CounterRecordTypeEnum;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.config.admin.entity.CounterConfig;
import com.micro.pmo.moudle.customer.admin.service.AdminCusCounterService;
import com.micro.pmo.moudle.customer.admin.vo.SendVO;
import com.micro.pmo.moudle.customer.entity.CounterRecord;
import com.micro.pmo.moudle.customer.entity.CusCounter;
import com.micro.pmo.moudle.user.utils.UserUtils;

/**
 * CusCounterService
 * 
 * @author
 * @createtime
 */
@Service
public class AdminCusCounterServiceImpl implements AdminCusCounterService {

	@Autowired
	private CusCounterMapper userSendMapper;

	@Autowired
	private CounterRecordMapper counterRecordMapper;

	@Autowired
	private CounterConfigMapper counterConfigMapper;

	@Autowired
	private CollectCarService collectCarService;
	
	@Transactional
	@Override
	public void send(SendVO sendVO) {

		CounterConfig config = counterConfigMapper.getCounterConfigById(sendVO.getConfigId());

		if (config == null) {
			throw new NullPointerException();
		}
		
		// 赠送记录信息
		CounterRecord sendRecord = getCounterRecord(sendVO, config);

		CusCounter cusCounter = userSendMapper.getValidCusCounterByTypeAndCusId(config.getConfigType(),
				sendVO.getCusId());

		//如果赠送的是天数单位则统一计算为一次
		Integer totalCount = config.getConfigType().isTime() ? 1 : sendVO.getSendNum();
		Integer surplusCount = config.getConfigType().isTime() ? 1 : sendVO.getSendNum();
		Integer sendCount = config.getConfigType().isTime() ? 1 : sendVO.getSendNum();
		
		if(cusCounter == null ) {
			cusCounter = new CusCounter();
			cusCounter.setBuyCount(0);
		}else {
			totalCount += cusCounter.getTotalCount();
			surplusCount += cusCounter.getSurplusCount();
			sendCount += cusCounter.getSendCount();
		}
 
		cusCounter.setTotalCount(totalCount);
		cusCounter.setSurplusCount(surplusCount);
		cusCounter.setSendCount(sendCount);
		cusCounter.setCounterType(config.getConfigType());
		cusCounter.setCusId(sendVO.getCusId());
		cusCounter.setGmtCreate(new Date());
		
		if (cusCounter.getCounterId() == null ) {
			// 保存新的赠送信息
			userSendMapper.insertCusCounter(cusCounter);
		}else {
			userSendMapper.updateCusCounterById(cusCounter);
		}

		// 保存赠送记录
		counterRecordMapper.insertCounterRecord(sendRecord);

		//如果是收车 则延长收车有效时间
		if(config.getConfigType().equals(CounterTypeEnum.collectCar)) {
			collectCarService.giveCollectCar(sendVO.getSendNum(), sendVO.getCusId());
		}
	}

	private CounterRecord getCounterRecord(SendVO sendVO, CounterConfig config) {
		CounterRecord sendRecord = new CounterRecord(UserUtils.getUser().getUserId(), sendVO.getCusId(),
				config.getConfigType(), new Date(), sendVO.getConfigId(), sendVO.getSendNum(),
				CounterRecordTypeEnum.SEND.getType());
		return sendRecord;
	}

}