package com.calebematos.askfood.domain.exception;

import static java.lang.String.format;

public class ProductPhotoNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    private ProductPhotoNotFoundException(String msg) {
        super(msg);
    }

    public static ProductPhotoNotFoundException of(String msg) {
        return new ProductPhotoNotFoundException(msg);
    }

    public static ProductPhotoNotFoundException of(Long restaurantId, Long productId) {
        return new ProductPhotoNotFoundException(
                format("There is no record of product photo with code %d for the restaurant with code %d", productId, restaurantId));
    }

}
