package com.calebematos.askfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import com.calebematos.askfood.domain.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

	private final RestaurantRepository restaurantRepository;
	private final RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> list() {
		return restaurantRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> find(@PathVariable Long id) {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
		if (restaurantOptional.isPresent()) {
			return ResponseEntity.ok(restaurantOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			Optional<Restaurant> currentRestaurantOption = restaurantRepository.findById(id);
			if(currentRestaurantOption.isPresent()) {
				Restaurant currentRestaurant = currentRestaurantOption.get();
				BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
				currentRestaurant = restaurantService.save(currentRestaurant);
				return ResponseEntity.ok(currentRestaurant);
			}
			return ResponseEntity.notFound().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
