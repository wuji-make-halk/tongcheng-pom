package com.micro.pmo.moudle.third.vo;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月14日 
*/

import com.micro.pmo.commons.vo.BaseQuery;

public class ThirdQuery extends BaseQuery{

	/**
	 * 车架号
	 */
	private String vin;

	/**
	 * 查询类型
	 */
	private Integer resultType;
 
	/**
	 * 用户
	 */
	private Integer cusId;
	
	
	
	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getResultType() {
		return resultType;
	}

	public void setResultType(Integer resultType) {
		this.resultType = resultType;
	}
 
}
