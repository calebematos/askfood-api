package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.CityModel;
import com.calebematos.askfood.api.v1.model.input.CityInput;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Cities", description = "Manager cities")
public interface CityControllerOpenApi {

    List<CityModel> list();


    CityModel find(Long cityId);


    CityModel add(CityInput cityInput);


    CityModel update(Long cityId, CityInput cityInput);


    void delete(Long cityId);
}
