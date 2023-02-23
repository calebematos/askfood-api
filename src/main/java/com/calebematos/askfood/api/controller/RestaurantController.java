package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.RestaurantMapper;
import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.api.model.input.ActivateInput;
import com.calebematos.askfood.api.model.input.ActivateManyInput;
import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.CuisineNotFoundException;
import com.calebematos.askfood.domain.exception.RestaurantNotFoundException;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import com.calebematos.askfood.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
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

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public List<RestaurantModel> list() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurantMapper.toCollectionModel(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);

        return restaurantMapper.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantMapper.toDomainObject(restaurantInput);
            return restaurantMapper.toModel(restaurantService.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw BusinessException.of(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant currentRestaurant = restaurantService.findById(restaurantId);

            restaurantMapper.copyToDomainObject(restaurantInput, currentRestaurant);

            return restaurantMapper.toModel(restaurantService.save(currentRestaurant));
        } catch (CuisineNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateOrInactivate(@PathVariable Long restaurantId, @RequestBody @Valid ActivateInput activateInput) {
        if (activateInput.getActive()) {
            restaurantService.activate(restaurantId);
        } else {
            restaurantService.inactivate(restaurantId);
        }
    }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateOrInactivateMany(@RequestBody @Valid ActivateManyInput activateManyInput) {
        try {
            if (activateManyInput.getActive()) {
                restaurantService.activate(activateManyInput.getIds());
            } else {
                restaurantService.inactivate(activateManyInput.getIds());
            }
        } catch (RestaurantNotFoundException e) {
            throw BusinessException.of(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
    }

    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
    }
}
