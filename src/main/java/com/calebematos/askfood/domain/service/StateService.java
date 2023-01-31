package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.State;
import com.calebematos.askfood.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StateService {

	public static final String MSG_STATE_NOT_FOUND = "There is no registered state with code %d";
	public static final String MSG_STATE_IN_USE = "State code %d cannot be removed because it is in use";

	private final StateRepository stateRepository;

	public State save(State cuisine) {
		return stateRepository.save(cuisine);
	}

	public void delete(Long stateId) {

		try {
			stateRepository.deleteById(stateId);

		} catch (EmptyResultDataAccessException e) {
			throw EntityNotFoundException.of(format(MSG_STATE_NOT_FOUND, stateId));
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_STATE_IN_USE, stateId));
		}
	}

    public State findById(Long stateId) {
		return stateRepository.findById(stateId)
				.orElseThrow(() -> EntityNotFoundException.of(format(MSG_STATE_NOT_FOUND, stateId)));
    }
}
