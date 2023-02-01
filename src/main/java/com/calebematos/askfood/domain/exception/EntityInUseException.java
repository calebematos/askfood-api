package com.calebematos.askfood.domain.exception;

public class EntityInUseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private EntityInUseException(String msg) {
		super(msg);
	}
	
	public static EntityInUseException of(String msg) {
		return new EntityInUseException(msg);
	}

}
