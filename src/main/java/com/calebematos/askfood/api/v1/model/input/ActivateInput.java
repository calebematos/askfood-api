package com.calebematos.askfood.api.v1.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivateInput {

    @NotNull
    private Boolean active;

}
