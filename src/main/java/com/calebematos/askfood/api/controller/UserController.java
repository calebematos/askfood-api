package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.UserMapper;
import com.calebematos.askfood.api.model.UserModel;
import com.calebematos.askfood.api.model.input.UserEditInput;
import com.calebematos.askfood.api.model.input.UserInput;
import com.calebematos.askfood.api.model.input.UserPasswordInput;
import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.CityNotFoundException;
import com.calebematos.askfood.domain.exception.UserNotFoundException;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.repository.UserRepository;
import com.calebematos.askfood.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping
    public List<UserModel> list() {
        List<User> users = userRepository.findAll();
        return mapper.toCollectionModel(users);
    }

    @GetMapping("/{userId}")
    public UserModel find(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return mapper.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@RequestBody @Valid UserInput userInput) {
        try {
            User user = mapper.toDomainObject(userInput);
            return mapper.toModel(userService.save(user));
        } catch (UserNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId, @RequestBody @Valid UserEditInput userInput) {
        try {
            User currentUser = userService.findById(userId);

            mapper.copyToDomainObject(userInput, currentUser);

            return mapper.toModel(userService.save(currentUser));
        } catch (UserNotFoundException | CityNotFoundException e) {
            throw BusinessException.of(e.getMessage());
        }
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long userId, @RequestBody @Valid UserPasswordInput userInput) {
        userService.updatePassword(userId, userInput.getCurrentPassword(), userInput.getNewPassword());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

}

