package com.calebematos.askfood.domain.service;

import static java.lang.String.format;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuisineService {

	private final CuisineRepository cuisineRepository;

	public Cuisine save(Cuisine cuisine) {
		return cuisineRepository.save(cuisine);
	}

	public void delete(Long cuisineId) {

		try {
			cuisineRepository.deleteById(cuisineId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format("There is no registered cuisine with code %d", cuisineId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format("Cuisine code %d cannot be removed because it is in use", cuisineId));
		}

	}

}
