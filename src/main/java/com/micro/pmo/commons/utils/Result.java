package com.micro.pmo.commons.utils;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 编码
	private long code;
	// 描述
	private String msg;
	// 返回数据
	private Object data;

	public Result() {
		
	}

	public Result(ResultState resultState) {
		this(resultState, resultState.getDesc());
	}

	public Result(ResultState resultState, String describe) {
		this.code = resultState.getCode();
		if(StringUtils.isEmpty(describe)){
			this.msg = resultState.getDesc();
		}else{
			this.msg = describe;
		}
		this.data = new Object();
	}
	
	
	public Result(ResultState resultState, String msg, Object data) {
		super();
		this.code = resultState.getCode();
		if(data != null){
			this.data = data;
		}else{
			this.data = new Object();
		}
		if(StringUtils.isEmpty(msg)){
			this.msg = resultState.getDesc();
		}else{
			this.msg = msg;
		}
		
		
		
	}

	public long getCode() {
		return code;
	}

	public void setCode(long recode) {
		this.code = recode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/*public final boolean isSuccess() {
		return this.getCode() == ResultState.SUCCEED.getCode();
	}
	
	public final boolean isFailure() {
		return this.getCode() != ResultState.SUCCEED.getCode();
	}*/
	
	public static final Result success() {
		//Object data = new Object();
		return success(null);
	}
	
	public static final Result success(Object data){
		return new Result(ResultState.SUCCEED,null,data);
	}
	//系统级失败
	public static final Result failure() {
		return new Result(ResultState.SYS_ERROR);
	}

	public static final Result failure(String describe) {
		return new Result(ResultState.SYS_ERROR, describe);
	}
	public static final Result failure(ResultState resultState){
		return new Result(resultState, null);
	}
	//失败带描述
	public static final Result failure(ResultState resultState, String describe) {
		return new Result(resultState, describe);
	}
	//失败带返回参数
	public static final Result failure(ResultState resultState, String describe,Object data) {
		return new Result(resultState, describe,data);
	}

}
