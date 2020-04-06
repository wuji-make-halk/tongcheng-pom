package com.micro.pmo.moudle.extension.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Kv;
import com.micro.pmo.moudle.customer.vo.CounterInfoVO;
import com.micro.pmo.moudle.extension.entity.Extension;
import com.micro.pmo.moudle.extension.vo.ExtensionCarVO;
import com.micro.pmo.moudle.extension.vo.InputExtensionBuyVO;
import com.micro.pmo.moudle.order.vo.PayInfoVO;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月5日 
*/
public interface PromotionService {

	/**
	 * 使用车辆推广次数
	 * @param carId
	 */
	void usePromotion(Integer carId);
	/**
	 * 获取推广的使用次数
	 * @return
	 */
	CounterInfoVO getPromotionCounterInfo();
	
	/**
	 * 获取待推广的列表
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	PageInfo<ExtensionCarVO> extensionCarList(Integer pageNumKey, Integer pageSizeKey);
 
	
	/**
	 * 使用赠送刷新
	 */
	void useSendFlush(Integer carId);
	
	/**
	 * 推广支付成功后的回调
	 * @param carId
	 * @param sysExtensionId
	 */
	void promotionPaySuccess(String orderId, Integer carId ,Integer day);

	/**
	 * 刷新支付成功后的回调
	 * @param carId
	 * @param sysExtensionId
	 */
	void flushPaySuccess(String orderId, Integer carId ,Integer day);
	
	
	/**
	 * 分页查询推广配置
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	List<Extension> extensionList();
	
	/**
	 * 购买推广
	 * @param configId
	 * @param count
	 */
	PayInfoVO buyPromotion(InputExtensionBuyVO input);
	
	/**
	 * 购买刷新
	 * @param configId
	 * @param count
	 */
	PayInfoVO buyFlush(InputExtensionBuyVO input);
	
	/**
	 * 获取推广和刷新的赠送信息
	 * @return
	 */
	Kv<String,Object> getPromotionAndFlushSendInfo();
}