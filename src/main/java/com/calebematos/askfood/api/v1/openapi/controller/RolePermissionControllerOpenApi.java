package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.PermissionModel;

import java.util.List;

public interface RolePermissionControllerOpenApi {

    List<PermissionModel> list(Long roleId);

    void disassociate(Long roleId, Long permissionId);

    void associate(Long roleId, Long permissionId);
}
