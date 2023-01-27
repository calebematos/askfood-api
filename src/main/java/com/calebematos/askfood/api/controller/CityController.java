package com.calebematos.askfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.repository.CityRepository;
import com.calebematos.askfood.domain.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

	private final CityRepository cityRepository;
	private final CityService cityService;
	
	@GetMapping
	public List<City> list(){
		return cityRepository.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> find(@PathVariable Long id) {
		Optional<City> cityOptional = cityRepository.findById(id);
		if (cityOptional.isPresent()) {
			return ResponseEntity.ok(cityOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City add(@RequestBody City city) {
		return cityService.save(city);
	}

	@PutMapping("/{id}")
	public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
		Optional<City> currentCityOption = cityRepository.findById(id);
		if (currentCityOption.isPresent()) {
			City currentCity = currentCityOption.get();
			BeanUtils.copyProperties(city, currentCity, "id");
			currentCity = cityService.save(currentCity);

			return ResponseEntity.ok(currentCity);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			cityService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
