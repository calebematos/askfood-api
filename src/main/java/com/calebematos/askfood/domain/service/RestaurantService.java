package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.RestaurantNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final CuisineService cuisineService;

	@Transactional
	public Restaurant save(Restaurant restaurant) {

		Long cuisineId = restaurant.getCuisine().getId();
		Cuisine cuisine = cuisineService.findById(cuisineId);
		restaurant.setCuisine(cuisine);

		return restaurantRepository.save(restaurant);
	}

    public Restaurant findById(Long restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> RestaurantNotFoundException.of(restaurantId));
    }
}
