package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.UserMapper;
import com.calebematos.askfood.api.model.UserModel;
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
@RequestMapping("/restaurants/{restaurantId}/responsible")
@RequiredArgsConstructor
public class RestaurantResponsibleUserController {

    private final RestaurantService restaurantService;
    private final UserMapper mapper;

    @GetMapping
    public List<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return mapper.toCollectionModel(restaurant.getResponsible());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateResponsible(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

}

