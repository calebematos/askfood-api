package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.RoleModel;
import com.calebematos.askfood.api.v1.model.input.RoleInput;

import java.util.List;

public interface RoleControllerOpenApi {

    List<RoleModel> list();

    RoleModel find(Long roleId);

    RoleModel add(RoleInput roleInput);

    RoleModel update(Long roleId, RoleInput roleInput);

    void delete(Long roleId);
}
