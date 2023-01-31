package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CuisineService {

	public static final String MSG_CUISINE_NOT_FOUND = "There is no registered cuisine with code %d";
	public static final String MSG_CUISINE_IN_USE = "Cuisine code %d cannot be removed because it is in use";
	private final CuisineRepository cuisineRepository;

	public Cuisine save(Cuisine cuisine) {
		return cuisineRepository.save(cuisine);
	}

	public void delete(Long cuisineId) {

		try {
			cuisineRepository.deleteById(cuisineId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format(MSG_CUISINE_NOT_FOUND, cuisineId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_CUISINE_IN_USE, cuisineId));
		}
	}
	
	public Cuisine findById(Long cuisineId){
		return cuisineRepository.findById(cuisineId).orElseThrow(() -> 
				EntityNotFoundException.of(format(MSG_CUISINE_NOT_FOUND, cuisineId)));
	}

}
