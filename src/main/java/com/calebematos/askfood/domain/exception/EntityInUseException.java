package com.calebematos.askfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private EntityInUseException(String msg) {
		super(msg);
	}
	
	public static EntityInUseException of(String msg) {
		return new EntityInUseException(msg);
	}

}
