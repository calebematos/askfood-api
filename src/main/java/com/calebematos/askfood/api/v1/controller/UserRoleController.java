package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.RoleMapper;
import com.calebematos.askfood.api.v1.model.RoleModel;
import com.calebematos.askfood.api.v1.openapi.controller.UserRoleControllerOpenApi;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.service.UserService;
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
@RequestMapping("/v1/users/{userId}/roles")
@RequiredArgsConstructor
public class UserRoleController implements UserRoleControllerOpenApi {

    private final UserService userService;
    private final RoleMapper mapper;

    @Override
    @GetMapping
    public List<RoleModel> list(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return mapper.toCollectionModel(user.getRoles());
    }


    @Override
    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.disassociateRole(userId, roleId);
    }

    @Override
    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.associateRole(userId, roleId);
    }
}

