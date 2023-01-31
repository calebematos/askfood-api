package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private StateNotFoundException(String msg) {
        super(msg);
    }

    public static StateNotFoundException of(String msg) {
        return new StateNotFoundException(msg);
    }

    public static StateNotFoundException of(Long stateId) {
        return new StateNotFoundException(format("There is no registered state with code %d", stateId));
    }

}
