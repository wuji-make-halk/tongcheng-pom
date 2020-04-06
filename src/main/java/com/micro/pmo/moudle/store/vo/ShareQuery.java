package com.micro.pmo.moudle.store.vo;

import com.micro.pmo.commons.vo.BaseQuery;

/***
 *  分享查询实体
 * @author 
 *
 */
public class ShareQuery  extends BaseQuery{

	private Integer cusId;

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public ShareQuery() {
		super();
	}
	
	
}
