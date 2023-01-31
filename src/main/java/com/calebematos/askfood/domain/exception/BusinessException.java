package com.calebematos.askfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
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
