package com.micro.pmo.moudle.third.enu;

import java.io.Serializable;

public enum ThirdTypeEnum implements Serializable {

	//0:车300购买报告,1:车300保险信息,2:查博士购买报告，3：查博士维保信息
 
    CAR300_INSURANCE(0,"che300保险信息",20),
    CHA_BO_SHI(1,"查博士购买报告",10),
    CHA_BO_SHI_4s(2,"查博士维保信息",10);

    private Integer code;
    private String value;
    private Integer maxDay;

	public static ThirdTypeEnum getByCode(Integer code){
		for (ThirdTypeEnum item : ThirdTypeEnum.values()) {
			if(item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}
    
    ThirdTypeEnum(Integer code, String value,Integer maxDay) {
        this.code = code;
        this.value = value;
        this.maxDay = maxDay;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }
}
