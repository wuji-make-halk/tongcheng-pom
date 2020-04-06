package com.micro.pmo.moudle.config.admin.configEnum;

/**
 * @author 作者:fanwenhao
 * @createDate 创建时间：2019年7月9日
 */
public enum CounterTypeEnum {

	/**
	 * 查成交价
	 */
	queryPrice("次", "查询成交价"),
	/**
	 * 收车
	 */
	collectCar("月", "预定收车"),
	/**
	 * 推广
	 */
	promotion("天", "车辆推广"),
	/**
	 * 刷新
	 */
	flush("天", "车辆刷新"),
	/**
	 * 查询车险
	 */
	insurance("次", "车险记录"),
	/**
	 * 查询4s维修
	 */
	maintenance("次", "4S维保");

	String unit;

	String configName;

	public static CounterTypeEnum getEnumByValue(String value) {
		if (value == null) {
			return null;
		}
		for (CounterTypeEnum item : CounterTypeEnum.values()) {
			if (item.name().equals(value)) {
				return item;
			}
		}
		return null;
	}

	public String getUnit() {
		return unit;
	}

	private CounterTypeEnum(String unit, String configName) {
		this.unit = unit;
		this.configName = configName;
	}

	public String getConfigName() {
		return configName;
	}

	public boolean isTime() {
		return this.getUnit().equals("天") || this.getUnit().equals("月");
	}

}
