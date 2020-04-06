package com.micro.pmo.moudle.account.accountenum;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年7月17日 
*/
public enum AccountLogEnum {
	/**
	 * 充值
	 */
	DEPOSIT(0,0),
	/**
	 * 退款
	 */
	REFUND(1,0),
	/**
	 * 支付
	 */
	PAY(2,1),
	/**
	 * 充值中
	 */
	DEPOSITING(3,2),
	/**
	 * 充值失败
	 */
	DEPOSIT_ERRO(4,2);
	
	public static AccountLogEnum getEnumByFlowType(Integer value) {
		if (value == null) {
			return null;
		}
		for (AccountLogEnum item : AccountLogEnum.values()) {
			if (item.getFlowType().equals(value)) {
				return item;
			}
		}
		return null;
	}
	
	private AccountLogEnum(Integer flowType, Integer operateType) {
		this.flowType = flowType;
		this.operateType = operateType;
	}
	
	private Integer flowType;
	
	//1为减少，0为增加,2为不进行金额操作
	private Integer operateType;

	public Integer getFlowType() {
		return flowType;
	}

	public Integer getOperateType() {
		return operateType;
	}

}
