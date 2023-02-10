package com.calebematos.askfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String zip;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

}
