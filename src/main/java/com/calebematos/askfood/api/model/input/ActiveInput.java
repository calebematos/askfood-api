package com.calebematos.askfood.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActiveInput {

    @NotNull
    private Boolean active;

}
