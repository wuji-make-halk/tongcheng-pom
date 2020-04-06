package com.micro.pmo.commons.dict;

public enum YesOrNo {

	YES("YES"),
	NO("NO");
	
	private String value;

	private YesOrNo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
	
}
