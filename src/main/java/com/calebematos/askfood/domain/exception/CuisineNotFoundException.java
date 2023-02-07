package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class CuisineNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private CuisineNotFoundException(String msg) {
        super(msg);
    }

    public static CuisineNotFoundException of(String msg) {
        return new CuisineNotFoundException(msg);
    }

    public static CuisineNotFoundException of(Long cuisineId) {
        return new CuisineNotFoundException(format("There is no registered cuisine with code %d", cuisineId));
    }

}
