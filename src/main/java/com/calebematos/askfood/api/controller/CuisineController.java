package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.CuisineMapper;
import com.calebematos.askfood.api.model.CuisineModel;
import com.calebematos.askfood.api.model.input.CuisineInput;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.CityNotFoundException;
import com.calebematos.askfood.domain.exception.CuisineNotFoundException;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import com.calebematos.askfood.domain.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/cuisines")
@RequiredArgsConstructor
public class CuisineController {

    private final CuisineRepository cuisineRepository;
    private final CuisineService cuisineService;
    private final CuisineMapper cuisineMapper;

    @GetMapping
    public Page<CuisineModel> list(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cuisine> cuisines = cuisineRepository.findAll(pageable);
        List<CuisineModel> cuisineModels = cuisineMapper.toCollectionModel(cuisines.getContent());
        return new PageImpl<>(cuisineModels, pageable, cuisines.getTotalElements());
    }

    @GetMapping("/{cuisineId}")
    public CuisineModel find(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineService.findById(cuisineId);
        return cuisineMapper.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModel add(@RequestBody @Valid CuisineInput cuisineInput) {
        try {
            Cuisine cuisine = cuisineMapper.toDomainObject(cuisineInput);
            return cuisineMapper.toModel(cuisineService.save(cuisine));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @PutMapping("/{cuisineId}")
    public CuisineModel update(@PathVariable Long cuisineId, @RequestBody @Valid CuisineInput cuisineInput) {
        try {
            Cuisine currentCuisine = cuisineService.findById(cuisineId);

            cuisineMapper.copyToDomainObject(cuisineInput, currentCuisine);

            return cuisineMapper.toModel(cuisineService.save(currentCuisine));
        } catch (CuisineNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineId) {
        cuisineService.delete(cuisineId);
    }
}
