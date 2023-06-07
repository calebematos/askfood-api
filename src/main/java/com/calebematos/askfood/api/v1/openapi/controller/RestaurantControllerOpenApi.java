package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.RestaurantModel;
import com.calebematos.askfood.api.v1.model.input.ActivateInput;
import com.calebematos.askfood.api.v1.model.input.ActivateManyInput;
import com.calebematos.askfood.api.v1.model.input.RestaurantInput;

import java.util.List;

public interface RestaurantControllerOpenApi {

    List<RestaurantModel> list();

    RestaurantModel find(Long restaurantId);

    RestaurantModel add(RestaurantInput restaurantInput);

    RestaurantModel update(Long restaurantId, RestaurantInput restaurantInput);

    void activateOrInactivate(Long restaurantId, ActivateInput activateInput);

    void activateOrInactivateMany(ActivateManyInput activateManyInput);

    void close(Long restaurantId);

    void open(Long restaurantId);
}
