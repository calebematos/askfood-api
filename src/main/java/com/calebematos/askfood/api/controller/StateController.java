package com.calebematos.askfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.State;
import com.calebematos.askfood.domain.repository.StateRepository;
import com.calebematos.askfood.domain.service.StateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController {

	private final StateRepository stateRepository;
	private final StateService stateService;
	
	@GetMapping
	public List<State> list(){
		return stateRepository.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> find(@PathVariable Long id) {
		Optional<State> stateOptional = stateRepository.findById(id);
		if (stateOptional.isPresent()) {
			return ResponseEntity.ok(stateOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State add(@RequestBody State state) {
		return stateService.save(state);
	}

	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
		Optional<State> currentStateOption = stateRepository.findById(id);
		if (currentStateOption.isPresent()) {
			State currentState = currentStateOption.get();
			BeanUtils.copyProperties(state, currentState, "id");
			currentState = stateService.save(currentState);

			return ResponseEntity.ok(currentState);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			stateService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
