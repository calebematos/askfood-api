package com.calebematos.askfood.api.assembler;

import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantModelAssembler {

    private final ModelMapper modelMapper;

    public RestaurantModel toModel(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantModel.class);
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());
    }
}
