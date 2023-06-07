package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.OrderingModel;
import com.calebematos.askfood.api.v1.model.OrderingResumedModel;
import com.calebematos.askfood.api.v1.model.input.OrderingInput;
import com.calebematos.askfood.domain.model.Ordering;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public abstract class OrderingMapper {

    @Mapping(target = "deliveryAddress.city", source = "ordering.deliveryAddress.city.name")
    @Mapping(target = "deliveryAddress.state", source = "ordering.deliveryAddress.city.state.name")
    public abstract OrderingModel toModel(Ordering ordering);

    public abstract List<OrderingModel> toCollectionModel(Collection<Ordering> ordering);

    public abstract List<OrderingResumedModel> toCollectionResumedModel(Collection<Ordering> ordering);

    public abstract Ordering toDomainObject(OrderingInput orderingInput);

    public abstract void copyToDomainObject(OrderingInput orderingInput, @MappingTarget Ordering ordering);
}
