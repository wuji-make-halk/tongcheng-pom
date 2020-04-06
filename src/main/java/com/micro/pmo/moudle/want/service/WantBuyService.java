package com.micro.pmo.moudle.want.service;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.want.entity.WantBuy;

/**
 * 求购Service
 */
public interface WantBuyService{
	
	/***求购信息保存***/
	public int intserWantBuy(WantBuy wantBuy);
	
	/**求购列表**/
	public PageInfo<WantBuy> wantBuyPage(Integer pageNumKey,Integer pageSizeKey);
	
	
}
