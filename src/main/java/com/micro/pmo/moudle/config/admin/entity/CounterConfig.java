package com.micro.pmo.moudle.config.admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.pmo.moudle.config.admin.configEnum.CounterTypeEnum;

/**
 * SysCounterConfig
 * 
 * @author
 * 
 */
public class CounterConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private Integer configId;
	/**
	 * 类型
	 */
	@JSONField(serialize = false)
	private CounterTypeEnum configType;
	
	/**
	 * 是否删除
	 */
    @JSONField(serialize = false)
	private Boolean isDeleted;
	/**
	 * 数量
	 */
    @JSONField(serialize = false)
	private Integer configNum;
	/**
	 * 创建时间
	 */
    @JSONField(serialize = false)
	private Date gmtCreate;

	/**
	 * 购买价格
	 */
	private BigDecimal configPrice;

	public BigDecimal getConfigPrice() {
		return configPrice;
	}

	public void setConfigPrice(BigDecimal configPrice) {
		this.configPrice = configPrice;
	}

	public void setConfigType(String value) {
		this.setConfigType(CounterTypeEnum.getEnumByValue(value));
	}

	public void setConfigType(CounterTypeEnum configType) {
		this.configType = configType;
	}

	public CounterConfig setConfigId(Integer configId) {
		this.configId = configId;
		return this;
	}

	public Integer getConfigId() {
		return this.configId;
	}

	public CounterTypeEnum getConfigType() {
		return this.configType;
	}

	public CounterConfig setConfigNum(Integer configNum) {
		this.configNum = configNum;
		return this;
	}

	public Integer getConfigNum() {
		return this.configNum;
	}

	public CounterConfig setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public CounterConfig setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}


	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * 配置名称
	 * @return
	 */
	public String getConfigName() {
		return getConfigType() == null ? null:getConfigType().getConfigName();
	}
	
	/**
	 * 获取配置单位
	 * @return
	 */
	public String getConfigUnit() {
		if(getConfigType() == null) {
			return null;
		}
		return getConfigType().getUnit();
	}
}