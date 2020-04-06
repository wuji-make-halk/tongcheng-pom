package com.micro.pmo.commons.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.micro.pmo.commons.utils.ResultState;

public class PmoException extends RuntimeException {

	private static final long serialVersionUID = 5299933009278910544L;

	private ResultState resultState;

	private String[] excpMessages;

	private String projectCode;

	private String month;

	public PmoException(ResultState resultState) {
		this(resultState, null, new String[] {});
	}

	public PmoException(ResultState resultState, Throwable cause) {
		this(resultState, cause, new String[] {});
	}

	public PmoException(ResultState resultState, String... messages) {
		this(resultState, null, messages);
	}

	public PmoException(ResultState resultState, Throwable cause, String... messages) {
		super(getExcepMsg(resultState, messages), cause);
		this.resultState = resultState;
		if (messages != null && cause != null) {
			this.excpMessages = ArrayUtils.add(messages, cause.getMessage());
		} else {
			this.excpMessages = messages;
		}
	}

	private static String getExcepMsg(ResultState resultState, String... messages) {
		StringBuilder sb = new StringBuilder();
		if (resultState != null) {
			sb.append(resultState.getDesc());
		}
		if (ArrayUtils.isNotEmpty(messages)) {
			sb.append(":").append(StringUtils.join(messages, ","));
		}
		return sb.toString();
	}

	public ResultState getResultState() {
		return resultState;
	}

	public String[] getExcpMessages() {
		return excpMessages;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public PmoException setProjectCode(String projectCode) {
		this.projectCode = projectCode;
		return this;
	}

	public String getMonth() {
		return month;
	}

	public PmoException setMonth(String month) {
		this.month = month;
		return this;
	}

}
