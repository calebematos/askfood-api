package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.StateModel;
import com.calebematos.askfood.api.v1.model.input.StateInput;

import java.util.List;

public interface StateControllerOpenApi {

    List<StateModel> list();

    StateModel find(Long stateId);

    StateModel add(StateInput stateInput);

    StateModel update(Long stateId, StateInput stateInput);

    void delete(Long stateId);
}
