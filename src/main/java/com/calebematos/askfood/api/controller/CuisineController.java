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
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import com.calebematos.askfood.domain.service.CuisineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuisines")
@RequiredArgsConstructor
public class CuisineController {

	private final CuisineRepository cuisineRepository;
	private final CuisineService cuisineService;

	@GetMapping
	public List<Cuisine> list() {
		return cuisineRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuisine> find(@PathVariable Long id) {
		Optional<Cuisine> cuisineOptional = cuisineRepository.findById(id);
		if (cuisineOptional.isPresent()) {
			return ResponseEntity.ok(cuisineOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cuisine add(@RequestBody Cuisine cuisine) {
		return cuisineService.save(cuisine);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cuisine> update(@PathVariable Long id, @RequestBody Cuisine cuisine) {
		Optional<Cuisine> currentCuisineOption = cuisineRepository.findById(id);
		if (currentCuisineOption.isPresent()) {
			Cuisine currentCuisine = currentCuisineOption.get();
			BeanUtils.copyProperties(cuisine, currentCuisine, "id");
			currentCuisine = cuisineService.save(currentCuisine);

			return ResponseEntity.ok(currentCuisine);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cuisine> delete(@PathVariable Long id) {
		try {
			cuisineService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
