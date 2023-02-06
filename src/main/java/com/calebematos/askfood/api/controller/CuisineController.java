package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.CuisineMapper;
import com.calebematos.askfood.api.model.CuisineModel;
import com.calebematos.askfood.api.model.input.CuisineInput;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import com.calebematos.askfood.domain.service.CuisineService;
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

import java.util.List;

@RestController
@RequestMapping("/cuisines")
@RequiredArgsConstructor
public class CuisineController {

    private final CuisineRepository cuisineRepository;
    private final CuisineService cuisineService;
    private final CuisineMapper cuisineMapper;

    @GetMapping
    public List<CuisineModel> list() {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        return cuisineMapper.toCollectionModel(cuisines);
    }

    @GetMapping("/{cuisineId}")
    public CuisineModel find(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineService.findById(cuisineId);
        return cuisineMapper.toModel(cuisine);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineModel add(@RequestBody CuisineInput cuisineInput) {
        Cuisine cuisine = cuisineMapper.toDomainObject(cuisineInput);
        return cuisineMapper.toModel(cuisineService.save(cuisine));
    }

    @PutMapping("/{cuisineId}")
    public CuisineModel update(@PathVariable Long cuisineId, @RequestBody CuisineInput cuisineInput) {
        Cuisine currentCuisine = cuisineService.findById(cuisineId);

         cuisineMapper.copyToDomainObject(cuisineInput, currentCuisine);

        return cuisineMapper.toModel(cuisineService.save(currentCuisine));
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineId) {
        cuisineService.delete(cuisineId);
    }
}
