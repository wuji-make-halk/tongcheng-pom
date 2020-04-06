package com.micro.pmo.moudle.extension.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.DateUtil;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.mapper.CarExtensionMapper;
import com.micro.pmo.mapper.CarMapper;
import com.micro.pmo.mapper.CounterConfigMapper;
import com.micro.pmo.mapper.CounterRecordMapper;
import com.micro.pmo.mapper.ExtensionMapper;
import com.micro.pmo.moudle.car.entity.CarExtension;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;
import com.micro.pmo.moudle.customer.entity.CounterRecord;
import com.micro.pmo.moudle.customer.service.CusCounterService;
import com.micro.pmo.moudle.customer.utils.CusUtils;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.extension.entity.Extension;
import com.micro.pmo.moudle.extension.service.PromotionService;
import com.micro.pmo.moudle.extension.vo.ExtensionCarVO;
import com.micro.pmo.moudle.extension.vo.InputExtensionBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/**
 * 推广表Service
 */
@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private CarExtensionMapper carExtensionMapper;

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private ExtensionMapper extensionMapper;

	@Autowired
	private CounterConfigMapper counterConfigMapper;

	@Autowired
	private CusCounterService cusCounterService;

	@Autowired
	private CounterRecordMapper counterRecordMapper;
 
	
	@Override
	public void useSendFlush(Integer carId) {
		// 赠送刷新
		useSend(carId,CounterTypeEnum.flush);
	}

	@Override
	@Transactional
	public void usePromotion(Integer carId) {
		// 赠送推广
		useSend(carId,CounterTypeEnum.promotion);
	}

	@Override
	public CounterInfoVO getPromotionCounterInfo() {
		return cusCounterService.getCounterInfo(CounterTypeEnum.promotion);
	}

	@Override
	public PageInfo<ExtensionCarVO> extensionCarList(Integer pageNumKey, Integer pageSizeKey) {
		// 分页处理
		if (pageNumKey == null)
			pageNumKey = 1;
		if (pageSizeKey == null)
			pageSizeKey = 10;
		Integer cusId = CusUtils.getCustomer().getCusId();
		PageHelper.startPage(pageNumKey, pageSizeKey);
		List<ExtensionCarVO> list = carMapper.extensionCarList(1, cusId);
		PageInfo<ExtensionCarVO> info = new PageInfo<>(list);
		return info;
	}

	@Override
	@Transactional
	public void flushPaySuccess(String orderId, Integer carId, Integer day) {
		paySuccess(orderId, carId, day, 1);
		// 修改车辆推广状态
		updateIsPromotionByCarId(1, carId);
		//修改刷新时间 
		carMapper.updateFlushDateByCarId(carId, new Date());
	}

	private void paySuccess(String orderId, Integer carId, Integer day, Integer type) {
		if (orderId == null) {
			throw new NullPointerException();
		}
		Integer cusId = CusUtils.getCustomer().getCusId();

		CarExtension beforeCarExtension = carExtensionMapper.getValidExtension(carId, type);

		// 根据以往的推广开通记录计算新的推广到期时间
		Date endDate = getEndDate(day, beforeCarExtension);

		// 如果以前有开通记录 则更新
		if (beforeCarExtension != null) {
			beforeCarExtension.setExtensionExpirationDate(endDate);
			carExtensionMapper.updateCarExtensionById(beforeCarExtension);
		} else {
			CarExtension newCarExtensio = new CarExtension(endDate, orderId, carId, cusId, 0, new Date(), type , 0 ,day);
			// 保存推广记录
			saveCarExtension(newCarExtensio);
		}
	}

	@Override
	@Transactional
	public void promotionPaySuccess(String orderId, Integer carId, Integer day) {
		paySuccess(orderId, carId, day, 0);
		// 修改车辆推广状态
		updateIsPromotionByCarId(1, carId);
		//修改刷新时间 
		carMapper.updateFlushDateByCarId(carId, new Date());
		
	}

 
	/**
	 * 使用刷新和推广的赠送次数
	 * @param carId
	 * @param dayNum
	 * @param userCounterRecordId
	 * @param type
	 */
	public void useSend(Integer carId,CounterTypeEnum counterTypeEnum) {
		
		Integer cusId = CusUtils.getCusId();
		
		counterConfigMapper.getValidCounterConfigByType(counterTypeEnum);
		// 减少赠送次数
		cusCounterService.reduceSend(counterTypeEnum, 1);

		// 获取赠送的推广时间
		CounterRecord record = counterRecordMapper.getFirstRecordByTypeAndCusId(counterTypeEnum, cusId);
 
		Integer dayNum = record.getSendNum();

		Integer type = counterTypeEnum.equals(counterTypeEnum)?1:0;
		
		CarExtension beforeCarExtension = carExtensionMapper.getValidExtension(carId,type);

		// 根据以往的推广开通记录计算新的推广到期时间
		Date endDate = null;

		Date startDate = beforeCarExtension == null ? new Date() : beforeCarExtension.getExtensionExpirationDate();

		endDate = DateUtil.datePlusDays(startDate, dayNum);

		// 如果已有推广记录则更新
		if (beforeCarExtension != null) {
			beforeCarExtension.setExtensionExpirationDate(endDate);
			carExtensionMapper.updateCarExtensionById(beforeCarExtension);
		} else {
			CarExtension newCarExtensio = new CarExtension(endDate,"", carId, cusId, 0,
					new Date(), type , 0 ,dayNum);
			// 保存推广记录
			saveCarExtension(newCarExtensio);
		}

		// 修改车辆推广状态
		updateIsPromotionByCarId(1, carId);
	}

	private Date getEndDate(Integer day, CarExtension carExtension) {
		Date newEndDate = null;

		if (carExtension == null) {
			return newEndDate = DateUtil.datePlusDays(new Date(), day);
		}

		Date endDate = carExtension.getExtensionExpirationDate();

		// 如果未过期则在原有截止时间上加上新增的时间
		if (endDate.after(new Date())) {
			newEndDate = DateUtil.datePlusDays(endDate, day);
			return newEndDate;
		}

		return newEndDate = DateUtil.datePlusDays(new Date(), day);
	}

	public void saveCarExtension(CarExtension carExtension) {
		carExtensionMapper.insertCarExtension(carExtension);
	}

	public void deleteCarExtensionById(int carExtensionId) {
		carExtensionMapper.deleteCarExtensionById(carExtensionId);
	}

	public void updateIsPromotionByCarId(Integer isPromotion, Integer carId) {
		carMapper.updateIsPromotionByCarId(isPromotion, carId);
	}

	@Override
	public List<Extension> extensionList() {
		return extensionMapper.extensionList();
	}

	@Override
	public PayInfoVO buyPromotion(InputExtensionBuyVO input) {
		return buy(input,CounterTypeEnum.promotion);
	}

	@Override
	public PayInfoVO buyFlush(InputExtensionBuyVO input) {
		return buy(input,CounterTypeEnum.flush);
	}

	/**
	 * 购买刷新和推广
	 * @param input
	 * @param type
	 * @return
	 */
	private PayInfoVO buy(InputExtensionBuyVO input,CounterTypeEnum type) {
		
		if(input.getNum() == null || input.getNum() <= 0) {
			throw new PmoException(ResultState.PARAM_ERROR,"购买天数不能小于1");
		}
		
		PayInfoVO result = cusCounterService.buy(input, type);
 
		return result;
	}

	@Override
	public Kv<String, Object> getPromotionAndFlushSendInfo() {
		
		Integer cusId = CusUtils.getCusId();
		
		CounterInfoVO promotion = cusCounterService.getCounterInfo(CounterTypeEnum.promotion);
		
		CounterInfoVO flush = cusCounterService.getCounterInfo(CounterTypeEnum.flush);
		
		CounterRecord promotionRecord = counterRecordMapper.getFirstRecordByTypeAndCusId(CounterTypeEnum.promotion, cusId);
		
		CounterRecord flushRecord = counterRecordMapper.getFirstRecordByTypeAndCusId(CounterTypeEnum.flush, cusId);

		Kv<String,Object> result = Kv.by("promotion", promotion);
		
		return result.set("flush", flush)
				.set("promotionFirstDayNum", promotionRecord == null ? 0 : promotionRecord.getSendNum())
				.set("flushFirstDayNum", flushRecord == null ? 0 : flushRecord.getSendNum());
	}

	

}
