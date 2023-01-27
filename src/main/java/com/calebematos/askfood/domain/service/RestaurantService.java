package com.calebematos.askfood.domain.service;

import static java.lang.String.format;

import org.springframework.stereotype.Service;

import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import com.calebematos.askfood.domain.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final CuisineRepository cuisineRepository;
	
	public Restaurant save(Restaurant restaurant) {

		Long cuisineId = restaurant.getCuisine().getId();
		Cuisine cuisine = cuisineRepository.findById(cuisineId).orElseThrow(() -> 
				EntityNotFoundException.of(format("There is no registered cuisine with code %d", cuisineId)));
	
		restaurant.setCuisine(cuisine);
		return restaurantRepository.save(restaurant);
	}


}
