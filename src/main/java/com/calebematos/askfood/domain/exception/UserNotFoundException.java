package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private UserNotFoundException(String msg) {
        super(msg);
    }

    public static UserNotFoundException of(String msg) {
        return new UserNotFoundException(msg);
    }

    public static UserNotFoundException of(Long userId) {
        return new UserNotFoundException(format("There is no registered user with code %d", userId));
    }

}
