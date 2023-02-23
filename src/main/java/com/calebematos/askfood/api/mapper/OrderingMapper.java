package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.OrderingModel;
import com.calebematos.askfood.api.model.input.OrderingInput;
import com.calebematos.askfood.domain.model.Ordering;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public abstract class OrderingMapper {

    public abstract OrderingModel toModel(Ordering ordering);

    public abstract List<OrderingModel> toCollectionModel(Collection<Ordering> ordering);

    public abstract Ordering toDomainObject(OrderingInput orderingInput);

    public abstract void copyToDomainObject(OrderingInput orderingInput, @MappingTarget Ordering ordering);
}
