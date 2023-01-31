package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class CityNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private CityNotFoundException(String msg) {
        super(msg);
    }

    public static CityNotFoundException of(String msg) {
        return new CityNotFoundException(msg);
    }

    public static CityNotFoundException of(Long stateId) {
        return new CityNotFoundException(format("There is no registered city with code %d", stateId));
    }

}
