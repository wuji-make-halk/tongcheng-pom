package com.micro.pmo.moudle.customer.entity;

import java.io.Serializable;
import java.util.Date;

import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;

/**
 * 
 * 用户次数统计表
 * @author
 * 
 */
public class CusCounter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private Integer counterId;
	/**
	 * 类型
	 */
	private CounterTypeEnum counterType;
	/**
	 * 用户id
	 */
	private Integer cusId;
	/**
	 * 总次数
	 */
	private Integer totalCount;
	/**
	 * 剩余次数
	 */
	private Integer surplusCount;
	
	/**
	 * 使用次数
	 */
	private Integer useCount;
	
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 赠送数量
	 */
	private Integer sendCount;
	/**
	 * 购买数量
	 */
	private Integer buyCount;
  
	
	
	public CusCounter() {
		super();
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public CusCounter(Integer totalCount, Integer surplusCount) {
		super();
		this.totalCount = totalCount;
		this.surplusCount = surplusCount;
	}

	public CusCounter setCounterId(Integer counterId) {
		this.counterId = counterId;
		return this;
	}

	public Integer getCounterId() {
		return this.counterId;
	}

	public CounterTypeEnum getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = CounterTypeEnum.getEnumByValue(counterType);
	}

	public void setCounterType(CounterTypeEnum counterType) {
		this.counterType = counterType;
	}

	public CusCounter setCusId(Integer cusId) {
		this.cusId = cusId;
		return this;
	}

	public Integer getCusId() {
		return this.cusId;
	}

	public CusCounter setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public CusCounter setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
		return this;
	}

	public Integer getSurplusCount() {
		return this.surplusCount;
	}

	public CusCounter setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public CusCounter setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public CusCounter setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
		return this;
	}

	public Integer getSendCount() {
		return this.sendCount;
	}

	public CusCounter setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
		return this;
	}

	public Integer getBuyCount() {
		return this.buyCount;
	}
}