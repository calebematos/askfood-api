package com.calebematos.askfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResumedModel {

    private Long id;
    private String name;
    private StateModel state;
}
