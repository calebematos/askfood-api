package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.CityModel;
import com.calebematos.askfood.api.v1.model.input.CityInput;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    public abstract CityModel toModel(City city);

    public abstract List<CityModel> toCollectionModel(List<City> cities);

    public abstract City toDomainObject(CityInput cityInput);

    protected abstract void copyObject(CityInput cityInput, @MappingTarget City city);

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        copyObject(cityInput, city);
    }

    ;
}
