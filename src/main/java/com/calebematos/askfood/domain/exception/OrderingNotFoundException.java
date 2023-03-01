package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class OrderingNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private OrderingNotFoundException(String msg) {
        super(msg);
    }

    public static OrderingNotFoundException of(String orderingCode) {
        return new OrderingNotFoundException(format("There is no registered order with code %s", orderingCode));
    }

}
