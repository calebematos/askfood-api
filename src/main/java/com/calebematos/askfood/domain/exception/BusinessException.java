package com.calebematos.askfood.domain.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	protected BusinessException(String msg) {
		super(msg);
	}

	protected BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public static BusinessException of(String msg) {
		return new BusinessException(msg);
	}

	public static BusinessException of(String msg, Throwable cause) {
		return new BusinessException(msg, cause);
	}

}
