package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.OrderItemModel;
import com.calebematos.askfood.api.v1.model.input.OrderItemInput;
import com.calebematos.askfood.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderItemMapper {

    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "productName", source = "orderItem.product.name")
    public abstract OrderItemModel toModel(OrderItem orderItem);

    @Mapping(target = "product.id", source = "orderItem.productId")
    public abstract OrderItem toDomainObject(OrderItemInput orderItem);
}
