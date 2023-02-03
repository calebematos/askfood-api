package com.calebematos.askfood.api.assembler;

import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantInputDisassembler {

    private final ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput){
        return modelMapper.map(restaurantInput, Restaurant.class);
    }
}
