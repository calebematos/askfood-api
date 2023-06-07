package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.ProductPhotoModel;
import com.calebematos.askfood.domain.model.ProductPhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductPhotoMapper {

    public abstract ProductPhotoModel toModel(ProductPhoto productPhoto);

}
