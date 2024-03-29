package com.calebematos.askfood.api.v1.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ActivateManyInput {

    @NotNull
    private Boolean active;

    @NotNull
    private List<Long> ids;

}
