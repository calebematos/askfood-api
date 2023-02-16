package com.calebematos.askfood.api.mapper;

import com.calebematos.askfood.api.model.UserModel;
import com.calebematos.askfood.api.model.input.UserEditInput;
import com.calebematos.askfood.api.model.input.UserInput;
import com.calebematos.askfood.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserModel toModel(User user);

    public abstract List<UserModel> toCollectionModel(List<User> users);

    public abstract User toDomainObject(UserInput userInput);

    public abstract void copyToDomainObject(UserEditInput userInput, @MappingTarget User user);
}
