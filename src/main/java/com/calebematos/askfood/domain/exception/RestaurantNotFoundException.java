package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class RestaurantNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private RestaurantNotFoundException(String msg) {
        super(msg);
    }

    public static RestaurantNotFoundException of(String msg) {
        return new RestaurantNotFoundException(msg);
    }

    public static RestaurantNotFoundException of(Long restaurantId) {
        return new RestaurantNotFoundException(format("There is no registered restaurant with code %d", restaurantId));
    }

}
