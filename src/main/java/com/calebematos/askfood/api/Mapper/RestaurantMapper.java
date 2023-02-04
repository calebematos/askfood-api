package com.calebematos.askfood.api.Mapper;

import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper
public interface RestaurantMapper {

    RestaurantModel toModel(Restaurant restaurant);

}
