package com.micro.pmo.commons.utils;

import java.text.MessageFormat;

/**
 * Service层公用的Exception, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String pattern, Object ... arguments) {
		super(MessageFormat.format(pattern, arguments));
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
