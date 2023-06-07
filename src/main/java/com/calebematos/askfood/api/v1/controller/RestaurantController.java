package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.RestaurantMapper;
import com.calebematos.askfood.api.v1.model.RestaurantModel;
import com.calebematos.askfood.api.v1.model.input.ActivateInput;
import com.calebematos.askfood.api.v1.model.input.ActivateManyInput;
import com.calebematos.askfood.api.v1.model.input.RestaurantInput;
import com.calebematos.askfood.api.v1.openapi.controller.RestaurantControllerOpenApi;
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
@RequestMapping("/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerOpenApi {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @Override
    @GetMapping
    public List<RestaurantModel> list() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurantMapper.toCollectionModel(restaurants);
    }

    @Override
    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);

        return restaurantMapper.toModel(restaurant);
    }

    @Override
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

    @Override
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

    @Override
    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateOrInactivate(@PathVariable Long restaurantId, @RequestBody @Valid ActivateInput activateInput) {
        if (activateInput.getActive()) {
            restaurantService.activate(restaurantId);
        } else {
            restaurantService.inactivate(restaurantId);
        }
    }

    @Override
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

    @Override
    @PutMapping("/{restaurantId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId) {
        restaurantService.close(restaurantId);
    }

    @Override
    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId) {
        restaurantService.open(restaurantId);
    }
}
