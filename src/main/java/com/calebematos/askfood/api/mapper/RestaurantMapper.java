package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.RestaurantModel;
import com.calebematos.askfood.api.model.input.RestaurantInput;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RestaurantMapper {

    public static final RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "address.city", source = "restaurant.address.city.name")
    @Mapping(target = "address.state", source = "restaurant.address.city.state.name")
    public abstract RestaurantModel toModel(Restaurant restaurant);

    public abstract List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants);

    public abstract Restaurant toDomainObject(RestaurantInput restaurantInput);

    protected abstract void copyObject(RestaurantInput restaurantInput, @MappingTarget Restaurant restaurant);


    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        restaurant.setCuisine(new Cuisine());

        if(restaurant.getAddress() != null){
            restaurant.getAddress().setCity(new City());
        }

        copyObject(restaurantInput, restaurant);
    }

}
