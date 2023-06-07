package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.ProductPhotoModel;
import com.calebematos.askfood.api.v1.model.input.ProductPhotoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

public interface RestaurantProductPhotoControllerOpenApi {

    ProductPhotoModel updatePhoto(Long restaurantId, Long productId, ProductPhotoInput productPhotoInput) throws IOException;

    ProductPhotoModel find(Long restaurantId, Long productId);

    ResponseEntity<?> getPhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    void deletePhoto(Long restaurantId, Long productId);

}
