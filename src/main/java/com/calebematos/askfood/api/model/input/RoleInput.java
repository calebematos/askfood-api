package com.calebematos.askfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoleInput {

    @NotBlank
    private String name;
}
