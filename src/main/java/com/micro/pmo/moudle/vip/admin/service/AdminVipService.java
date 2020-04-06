package com.micro.pmo.moudle.vip.admin.service;

import java.util.List;

import com.micro.pmo.commons.vo.BaseQuery;
import com.micro.pmo.moudle.vip.entity.Vip;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月4日 
*/
public interface AdminVipService {

	/**
	 * 添加Vip
	 * @param Vip
	 * @return
	 */
	void updateVip(Vip Vip);

	/**
	 * 修改Vip
	 * @param Vip
	 * @return
	 */
	void saveVip(Vip Vip);
	
	/**
	 * 分页查询VIP配置
	 * @param pageNumKey
	 * @param pageSizeKey
	 * @return
	 */
	List<Vip> vipList(BaseQuery baseQuery);
 
	/**
	 * 赠送VIP
	 * @param vipId
	 * @param cusId
	 */
	void giveVip(Integer vipId , Integer cusId);
}