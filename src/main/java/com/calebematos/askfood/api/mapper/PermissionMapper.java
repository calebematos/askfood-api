package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.PermissionModel;
import com.calebematos.askfood.domain.model.Permission;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PermissionMapper {

    public abstract PermissionModel toModel(Permission permission);

    public abstract List<PermissionModel> toCollectionModel(Collection<Permission> permissions);
  
}
