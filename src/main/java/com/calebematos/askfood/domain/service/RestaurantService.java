package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RestaurantService {

	public static final String MSG_RESTAURANT_NOT_FOUND = "There is no registered restaurant with code %d";

	private final RestaurantRepository restaurantRepository;
	private final CuisineService cuisineService;

	
	public Restaurant save(Restaurant restaurant) {

		Long cuisineId = restaurant.getCuisine().getId();
		Cuisine cuisine = cuisineService.findById(cuisineId);
		restaurant.setCuisine(cuisine);

		return restaurantRepository.save(restaurant);
	}

    public Restaurant findById(Long restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> EntityNotFoundException.of(format(MSG_RESTAURANT_NOT_FOUND, restaurantId)));
    }
}
