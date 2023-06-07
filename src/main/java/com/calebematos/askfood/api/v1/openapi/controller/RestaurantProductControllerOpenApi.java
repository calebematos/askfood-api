package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.ProductModel;
import com.calebematos.askfood.api.v1.model.input.ProductInput;

import java.util.List;

public interface RestaurantProductControllerOpenApi {

    List<ProductModel> list(Long restaurantId, boolean addInactive);

    ProductModel find(Long restaurantId, Long productId);

    ProductModel add(Long restaurantId, ProductInput productInput);

    ProductModel update(Long restaurantId, Long productId, ProductInput productInput);
}
