package com.calebematos.askfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserEditInput {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

}
