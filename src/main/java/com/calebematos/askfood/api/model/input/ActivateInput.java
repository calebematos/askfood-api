package com.calebematos.askfood.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivateInput {

    @NotNull
    private Boolean active;

}
