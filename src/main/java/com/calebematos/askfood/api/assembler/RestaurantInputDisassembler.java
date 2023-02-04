package com.calebematos.askfood.api.assembler;

import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

//@Component
@RequiredArgsConstructor
public class RestaurantInputDisassembler {

    private final ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput){
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        // to avoid JpaSystemException: identifier of an instance of com.calebematos.askfood.domain.model.Cuisine was altered from 1 to 2
        restaurant.setCuisine(new Cuisine());
        modelMapper.map(restaurantInput, restaurant);
    }
}
