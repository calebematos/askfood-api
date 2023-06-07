package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.CuisineModel;
import com.calebematos.askfood.api.v1.model.input.CuisineInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CuisineControllerOpenApi {

    Page<CuisineModel> list(Pageable pageable);

    CuisineModel find(Long cuisineId);

    CuisineModel add(CuisineInput cuisineInput);

    CuisineModel update(Long cuisineId, CuisineInput cuisineInput);

    void delete(Long cuisineId);
}
