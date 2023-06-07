package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.CuisineModel;
import com.calebematos.askfood.api.v1.model.input.CuisineInput;
import com.calebematos.askfood.domain.model.Cuisine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CuisineMapper {

    public abstract CuisineModel toModel(Cuisine cuisine);

    public abstract List<CuisineModel> toCollectionModel(List<Cuisine> cuisines);

    public abstract Cuisine toDomainObject(CuisineInput cuisineInput);

    public abstract void copyToDomainObject(CuisineInput cuisineInput, @MappingTarget Cuisine cuisine);
}
