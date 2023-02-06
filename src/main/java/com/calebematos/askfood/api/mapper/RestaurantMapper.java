package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RestaurantMapper {

    public static final RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    public abstract RestaurantModel toModel(Restaurant restaurant);

    public abstract List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants);

    public abstract Restaurant toDomainObject(RestaurantInput restaurantInput);

    protected abstract void copyObject(RestaurantInput restaurantInput, @MappingTarget Restaurant restaurant);


    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        restaurant.setCuisine(new Cuisine());
        copyObject(restaurantInput, restaurant);
    }

}
