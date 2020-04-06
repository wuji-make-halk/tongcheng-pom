package com.micro.pmo.moudle.config.admin.configEnum;
/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月11日 
*/
public enum CounterRecordTypeEnum {

	BUY(1),
	SEND(0),
	USE(2);
	
	private Integer type;

	private CounterRecordTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
 
	
}
