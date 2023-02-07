package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class FormPaymentNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private FormPaymentNotFoundException(String msg) {
        super(msg);
    }

    public static FormPaymentNotFoundException of(String msg) {
        return new FormPaymentNotFoundException(msg);
    }

    public static FormPaymentNotFoundException of(Long formPaymentId) {
        return new FormPaymentNotFoundException(format("There is no registered form of " +
                "payment with code %d", formPaymentId));
    }

}
