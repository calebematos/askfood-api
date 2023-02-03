package com.calebematos.askfood.api.model;

import com.calebematos.askfood.domain.model.Cuisine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CuisineModel {

    private Long id;
    private String name;

    public CuisineModel(Cuisine cuisine) {
        this.id = cuisine.getId();
        this.name = cuisine.getName();
    }
}
