package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.CityMapper;
import com.calebematos.askfood.api.model.CityModel;
import com.calebematos.askfood.api.model.input.CityInput;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.StateNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.repository.CityRepository;
import com.calebematos.askfood.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;
    private final CityService cityService;
    private final CityMapper mapper;

    @GetMapping
    public List<CityModel> list() {
        List<City> cities = cityRepository.findAll();
        return mapper.toCollectionModel(cities);
    }

    @GetMapping("/{cityId}")
    public CityModel find(@PathVariable Long cityId) {
        City city = cityService.findById(cityId);
        return mapper.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel add(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = mapper.toDomainObject(cityInput);
            return mapper.toModel(cityService.save(city));
        } catch (StateNotFoundException e) {
            throw BusinessException.of(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public CityModel update(@PathVariable Long cityId, @RequestBody @Valid CityInput cityInput) {
        try {
            City currentCity = cityService.findById(cityId);

            mapper.copyToDomainObject(cityInput, currentCity);

            return mapper.toModel(cityService.save(currentCity));
        } catch (StateNotFoundException e) {
            throw BusinessException.of(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityService.delete(cityId);
    }
}
