package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private ProductNotFoundException(String msg) {
        super(msg);
    }

    public static ProductNotFoundException of(String msg) {
        return new ProductNotFoundException(msg);
    }

    public static ProductNotFoundException of(Long productId, Long restaurantId) {
        return new ProductNotFoundException(format("There is no product record with code %d for restaurant code %d",
                productId, restaurantId));
    }

}
