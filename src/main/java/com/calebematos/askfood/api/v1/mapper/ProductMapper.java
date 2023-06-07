package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.ProductModel;
import com.calebematos.askfood.api.v1.model.input.ProductInput;
import com.calebematos.askfood.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductModel toModel(Product product);

    public abstract List<ProductModel> toCollectionModel(Collection<Product> products);

    public abstract Product toDomainObject(ProductInput productInput);

    public abstract void copyToDomainObject(ProductInput productInput, @MappingTarget Product product);
}
