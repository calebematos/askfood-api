package com.calebematos.askfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserIdInput {

    @NotNull
    private Long id;
}
