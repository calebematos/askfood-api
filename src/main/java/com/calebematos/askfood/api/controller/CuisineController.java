package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.repository.CuisineRepository;
import com.calebematos.askfood.domain.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.findAll();
    }

    @GetMapping("/{cuisineId}")
    public Cuisine find(@PathVariable Long cuisineId) {
        return cuisineService.findById(cuisineId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine) {
        return cuisineService.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public Cuisine update(@PathVariable Long cuisineId, @RequestBody Cuisine cuisine) {
        Cuisine currentCuisine = cuisineService.findById(cuisineId);

        BeanUtils.copyProperties(cuisine, currentCuisine, "cuisineId");

        return cuisineService.save(currentCuisine);
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cuisineId) {
        cuisineService.delete(cuisineId);
    }
}
