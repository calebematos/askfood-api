package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.StateModel;
import com.calebematos.askfood.api.model.input.StateInput;
import com.calebematos.askfood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StateMapper {

    public abstract StateModel toModel(State state);

    public abstract List<StateModel> toCollectionModel(List<State> states);

    public abstract State toDomainObject(StateInput stateInput);

    public abstract void copyToDomainObject(StateInput stateInput, @MappingTarget State state);
}
