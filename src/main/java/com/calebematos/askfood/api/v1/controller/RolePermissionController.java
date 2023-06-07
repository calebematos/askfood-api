package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.PermissionMapper;
import com.calebematos.askfood.api.v1.model.PermissionModel;
import com.calebematos.askfood.api.v1.openapi.controller.RolePermissionControllerOpenApi;
import com.calebematos.askfood.domain.model.Role;
import com.calebematos.askfood.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/roles/{roleId}/permissions")
@RequiredArgsConstructor
public class RolePermissionController implements RolePermissionControllerOpenApi {

    private final RoleService roleService;
    private final PermissionMapper mapper;

    @Override
    @GetMapping
    public List<PermissionModel> list(@PathVariable Long roleId) {
      Role role = roleService.findById(roleId);
      return mapper.toCollectionModel(role.getPermissions());
    }

    @Override
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.disassociatePermission(roleId, permissionId);
    }

    @Override
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.associatePermission(roleId, permissionId);
    }

}

