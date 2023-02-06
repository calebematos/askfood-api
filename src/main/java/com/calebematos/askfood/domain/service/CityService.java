package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.CityNotFoundException;
import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.model.State;
import com.calebematos.askfood.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CityService {

    public static final String MSG_CITY_IN_USE = "City code %d cannot be removed because it is in use";

    private final CityRepository cityRepository;
    private final StateService stateService;

    @Transactional
    public City save(City city) {

        Long stateId = city.getState().getId();
        State state = stateService.findById(stateId);
        city.setState(state);

        return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long cityId) {

        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw CityNotFoundException.of(cityId);
        } catch (DataIntegrityViolationException e) {
            throw EntityInUseException.of(format(MSG_CITY_IN_USE, cityId));
        }
    }

    public City findById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> CityNotFoundException.of(cityId));
    }
}
