package com.micro.pmo.moudle.third.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ThirdSearchRecord 
 * @author wenhaofan 
 * 
 */
public class ThirdSearchRecord  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	*查询记录id
	*/
	private Integer  recordId;
	/**
	*第三方接口id
	*/
	private Integer  resultId;
	/**
	*客户id
	*/
	private Integer  cusId;
	/**
	*查询时间
	*/
	private Date  createDate;
	/**
	*更新时间
	*/
	private Date  updateDate;

	public ThirdSearchRecord(Integer resultId, Integer cusId, Date createDate, Date updateDate) {
		super();
		this.resultId = resultId;
		this.cusId = cusId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public ThirdSearchRecord setRecordId(Integer recordId) {
		this.recordId=recordId;
		return this;
	}

	public Integer getRecordId() {
		return this.recordId;
	}

	public ThirdSearchRecord setResultId(Integer resultId) {
		this.resultId=resultId;
		return this;
	}

	public Integer getResultId() {
		return this.resultId;
	}
 
	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public ThirdSearchRecord setCreateDate(Date createDate) {
		this.createDate=createDate;
		return this;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public ThirdSearchRecord setUpdateDate(Date updateDate) {
		this.updateDate=updateDate;
		return this;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}
}