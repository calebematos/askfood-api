package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.StateMapper;
import com.calebematos.askfood.api.v1.model.StateModel;
import com.calebematos.askfood.api.v1.model.input.StateInput;
import com.calebematos.askfood.api.v1.openapi.controller.StateControllerOpenApi;
import com.calebematos.askfood.domain.model.State;
import com.calebematos.askfood.domain.repository.StateRepository;
import com.calebematos.askfood.domain.service.StateService;
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
@RequestMapping("/v1/states")
@RequiredArgsConstructor
public class StateController implements StateControllerOpenApi {

    private final StateRepository stateRepository;
    private final StateService stateService;
    private final StateMapper stateMapper;

    @Override
    @GetMapping
    public List<StateModel> list() {
        List<State> states = stateRepository.findAll();
        return stateMapper.toCollectionModel(states);
    }

    @Override
    @GetMapping("/{stateId}")
    public StateModel find(@PathVariable Long stateId) {
        State state = stateService.findById(stateId);
        return stateMapper.toModel(state);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel add(@RequestBody @Valid StateInput stateInput) {
        State state = stateMapper.toDomainObject(stateInput);
        return stateMapper.toModel(stateService.save(state));
    }

    @Override
    @PutMapping("/{stateId}")
    public StateModel update(@PathVariable Long stateId, @RequestBody @Valid StateInput stateInput) {
        State currentState = stateService.findById(stateId);

        stateMapper.copyToDomainObject(stateInput, currentState);

        return stateMapper.toModel(stateService.save(currentState));
    }

    @Override
    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        stateService.delete(stateId);
    }
}
