package com.micro.pmo.moudle.third.enu;

/** 
* @author 作者:fanwenhao
* @createDate 创建时间：2019年8月13日 
*/
public enum ThirdResEnum {

	SUCCESS(0,"查询成功"),
	NO_DATA(1,"无数据"),
	IN_QUERY(2,"查询中"),
	ERROR(3,"查询失败");
	
	Integer status;
	
	String title;

	private ThirdResEnum(Integer status, String title) {
		this.status = status;
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}
	
	public static ThirdResEnum getByStatus(Integer status){
		for (ThirdResEnum item : ThirdResEnum.values()) {
			if(item.getStatus().equals(status)) {
				return item;
			}
		}
		return null;
	}
	
	
}
