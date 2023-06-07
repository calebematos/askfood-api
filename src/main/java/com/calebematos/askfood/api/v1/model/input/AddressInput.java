package com.calebematos.askfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String zip;

    @NotBlank
    private String street;

    @NotBlank
    private String number;
    private String complement;

    @NotBlank
    private String neighborhood;

    @Valid
    @NotNull
    private CityIdInput city;

}
