package com.calebematos.askfood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    private Long id;
    private String name;
    private BigDecimal shippingFee;
    private CuisineModel cuisine;
    private Boolean active;
    private AddressModel address;

}
