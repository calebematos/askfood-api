package com.calebematos.askfood.domain.exception;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private EntityNotFoundException(String msg) {
		super(msg);
	}
	
	public static EntityNotFoundException of(String msg) {
		return new EntityNotFoundException(msg);
	}

}
