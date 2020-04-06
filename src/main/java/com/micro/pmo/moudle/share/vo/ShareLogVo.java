package com.micro.pmo.moudle.share.vo;

/***
 * 分享记录返回实体
 * @author raoBo
 *
 */
public class ShareLogVo{

	/***
	 * 分享条数
	 */
	private Integer counts;
	
	/***
	 * 昵称
	 */
	private String cusNick;
	
	/***
	 * 头像
	 */
	private String cusAvatar;

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public String getCusNick() {
		return cusNick;
	}

	public void setCusNick(String cusNick) {
		this.cusNick = cusNick;
	}

	public String getCusAvatar() {
		return cusAvatar;
	}

	public void setCusAvatar(String cusAvatar) {
		this.cusAvatar = cusAvatar;
	}

	public ShareLogVo() {
		super();
	}
	
	
	
}
