package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.UserModel;

import java.util.List;

public interface RestaurantResponsibleUserControllerOpenApi {

    List<UserModel> list(Long restaurantId);

    void disassociateResponsible(Long restaurantId, Long userId);

    void associateResponsible(Long restaurantId, Long userId);
}
