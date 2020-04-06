package com.micro.pmo.moudle.brand.vo;

import java.util.List;

public class BranchHttpVo {

	private String status;
	
	private List<BranchVo> info;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BranchVo> getInfo() {
		return info;
	}

	public void setInfo(List<BranchVo> info) {
		this.info = info;
	}
	
	
}
