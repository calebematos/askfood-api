package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.UserMapper;
import com.calebematos.askfood.api.v1.model.UserModel;
import com.calebematos.askfood.api.v1.openapi.controller.RestaurantResponsibleUserControllerOpenApi;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/responsible")
@RequiredArgsConstructor
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    private final RestaurantService restaurantService;
    private final UserMapper mapper;

    @Override
    @GetMapping
    public List<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return mapper.toCollectionModel(restaurant.getResponsible());
    }

    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

    @Override
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

}

