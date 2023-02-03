package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.assembler.RestaurantInputDisassembler;
import com.calebematos.askfood.api.assembler.RestaurantModelAssembler;
import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.CuisineNotFoundException;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import com.calebematos.askfood.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantModelAssembler modelAssemble;
    private final RestaurantInputDisassembler inputDisassembler;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantModel> list() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return modelAssemble.toCollectionModel(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);

        return modelAssemble.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = inputDisassembler.toDomainObject(restaurantInput);
            return modelAssemble.toModel(restaurantService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw BusinessException.of(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = inputDisassembler.toDomainObject(restaurantInput);

            Restaurant currentRestaurant = restaurantService.findById(restaurantId);

            BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "formsPayment",
                    "address", "registrationDate", "products");

            return modelAssemble.toModel(restaurantService.save(currentRestaurant));
        } catch (CuisineNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

}
