package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.RoleMapper;
import com.calebematos.askfood.api.v1.model.RoleModel;
import com.calebematos.askfood.api.v1.model.input.RoleInput;
import com.calebematos.askfood.api.v1.openapi.controller.RoleControllerOpenApi;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.CityNotFoundException;
import com.calebematos.askfood.domain.exception.RoleNotFoundException;
import com.calebematos.askfood.domain.model.Role;
import com.calebematos.askfood.domain.repository.RoleRepository;
import com.calebematos.askfood.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class RoleController implements RoleControllerOpenApi {

    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final RoleMapper mapper;

    @Override
    @GetMapping
    public List<RoleModel> list() {
        List<Role> roles = roleRepository.findAll();
        return mapper.toCollectionModel(roles);
    }

    @Override
    @GetMapping("/{roleId}")
    public RoleModel find(@PathVariable Long roleId) {
        Role role = roleService.findById(roleId);
        return mapper.toModel(role);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleModel add(@RequestBody @Valid RoleInput roleInput) {
        try {
            Role role = mapper.toDomainObject(roleInput);
            return mapper.toModel(roleService.save(role));
        } catch (RoleNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @Override
    @PutMapping("/{roleId}")
    public RoleModel update(@PathVariable Long roleId, @RequestBody @Valid RoleInput roleInput) {
        try {
            Role currentRole = roleService.findById(roleId);

            mapper.copyToDomainObject(roleInput, currentRole);

            return mapper.toModel(roleService.save(currentRole));
        } catch (RoleNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long roleId) {
        roleService.delete(roleId);
    }

}

