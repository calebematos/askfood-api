package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.RoleModel;

import java.util.List;

public interface UserRoleControllerOpenApi {

    List<RoleModel> list(Long userId);

    void disassociate(Long userId, Long roleId);

    void associate(Long userId, Long roleId);
}
