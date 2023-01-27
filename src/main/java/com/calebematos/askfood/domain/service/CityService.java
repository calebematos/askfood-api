package com.calebematos.askfood.domain.service;

import static java.lang.String.format;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.repository.CityRepository;
import com.calebematos.askfood.domain.repository.StateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

	private final CityRepository cityRepository;
	private final StateRepository stateRepository;

	public City save(City city) {
		
		Long stateId = city.getState().getId();
		stateRepository.findById(stateId).orElseThrow(() -> 
					EntityNotFoundException.of(format("There is no registered state with code %d", stateId)));
		
		return cityRepository.save(city);
	}

	public void delete(Long cityId) {

		try {
			cityRepository.deleteById(cityId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format("There is no registered city with code %d", cityId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format("City code %d cannot be removed because it is in use", cityId));
		}

	}

}
