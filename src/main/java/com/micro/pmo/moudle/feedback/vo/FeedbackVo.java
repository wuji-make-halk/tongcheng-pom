package com.micro.pmo.moudle.feedback.vo;

import com.micro.pmo.commons.vo.BaseQuery;

/***
 * 反馈查询入参
 * @author raoBo
 *
 */
public class FeedbackVo extends BaseQuery{

	/***
	 * 用户昵称
	 */
	private String cusNick;
	
	/***
	 * 用户电话
	 */
	private String cusPhone;

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	
	
}
