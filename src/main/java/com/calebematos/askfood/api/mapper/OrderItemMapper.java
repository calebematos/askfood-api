package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.OrderItemModel;
import com.calebematos.askfood.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrderItemMapper {

    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "productName", source = "orderItem.product.name")
    public abstract OrderItemModel toModel(OrderItem orderItem);

}
