package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.RoleModel;
import com.calebematos.askfood.api.v1.model.input.RoleInput;
import com.calebematos.askfood.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    public abstract RoleModel toModel(Role role);

    public abstract List<RoleModel> toCollectionModel(Collection<Role> roles);

    public abstract Role toDomainObject(RoleInput roleInput);

    public abstract void copyToDomainObject(RoleInput roleInput, @MappingTarget Role role);
}
