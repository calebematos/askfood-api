package com.calebematos.askfood.domain.exception;

public abstract class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    protected EntityNotFoundException(String msg) {
        super(msg);
    }

}
