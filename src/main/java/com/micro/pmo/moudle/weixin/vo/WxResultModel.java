package com.micro.pmo.moudle.weixin.vo;

import java.io.Serializable;

/**
 * @author raobo
 * @create 2019-06-24 11:32
 **/
public class WxResultModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String unionid;
    private String openid;
    private String session_key;
    
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
    
    
}
