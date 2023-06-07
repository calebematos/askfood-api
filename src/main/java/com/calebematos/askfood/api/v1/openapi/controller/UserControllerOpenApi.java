package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.UserModel;
import com.calebematos.askfood.api.v1.model.input.UserEditInput;
import com.calebematos.askfood.api.v1.model.input.UserInput;
import com.calebematos.askfood.api.v1.model.input.UserPasswordInput;

import java.util.List;

public interface UserControllerOpenApi {

    List<UserModel> list();

    UserModel find(Long userId);

    UserModel add(UserInput userInput);

    UserModel update(Long userId, UserEditInput userInput);

    void updatePassword(Long userId, UserPasswordInput userInput);

    void delete(Long userId);
}
