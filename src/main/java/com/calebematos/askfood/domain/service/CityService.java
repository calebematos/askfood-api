package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.repository.CityRepository;
import com.calebematos.askfood.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CityService {

	public static final String MSG_CITY_NOT_FOUND = "There is no registered city with code %d";
	public static final String MSG_STATE_NOT_FOUND = "There is no registered state with code %d";
	public static final String MSG_CITY_IN_USE = "City code %d cannot be removed because it is in use";
	private final CityRepository cityRepository;
	private final StateRepository stateRepository;

	public City save(City city) {
		
		Long stateId = city.getState().getId();
		stateRepository.findById(stateId).orElseThrow(() -> 
					EntityNotFoundException.of(format(MSG_STATE_NOT_FOUND, stateId)));
		
		return cityRepository.save(city);
	}

	public void delete(Long cityId) {

		try {
			cityRepository.deleteById(cityId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format(MSG_CITY_NOT_FOUND, cityId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_CITY_IN_USE, cityId));
		}
	}

    public City findById(Long cityId) {
		return cityRepository.findById(cityId)
				.orElseThrow(() -> EntityNotFoundException.of(format(MSG_CITY_NOT_FOUND, cityId)));
    }
}
