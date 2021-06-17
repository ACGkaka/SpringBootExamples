package com.demo.common.exception;

/**
 * <p> @Title BaseException
 * <p> @Description 自定义异常
 *
 * @author ACGkaka
 * @date 2021/4/30 17:27
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer errorCode = 100;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(int code, String message) {
		super(message);
		this.errorCode = code;
	}

	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = code;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
