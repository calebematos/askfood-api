package com.calebematos.askfood.domain.service;

import static java.lang.String.format;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.State;
import com.calebematos.askfood.domain.repository.StateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateService {

	private final StateRepository stateRepository;

	public State save(State cuisine) {
		return stateRepository.save(cuisine);
	}

	public void delete(Long stateId) {

		try {
			stateRepository.deleteById(stateId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format("There is no registered state with code %d", stateId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format("State code %d cannot be removed because it is in use", stateId));
		}

	}

}
