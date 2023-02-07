package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.EntityInUseException;
import com.calebematos.askfood.domain.exception.FormPaymentNotFoundException;
import com.calebematos.askfood.domain.exception.StateNotFoundException;
import com.calebematos.askfood.domain.model.FormPayment;
import com.calebematos.askfood.domain.repository.FormPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FormPaymentService {

	public static final String MSG_STATE_IN_USE = "Form of payment code %d cannot be removed because it is in use";

	private final FormPaymentRepository formPaymentRepository;

	@Transactional
	public FormPayment save(FormPayment formPayment) {
		return formPaymentRepository.save(formPayment);
	}

	@Transactional
	public void delete(Long formPaymentId) {

		try {
			formPaymentRepository.deleteById(formPaymentId);
			formPaymentRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw StateNotFoundException.of(formPaymentId);
		} catch (DataIntegrityViolationException e) {
			throw EntityInUseException.of(format(MSG_STATE_IN_USE, formPaymentId));
		}
	}

    public FormPayment findById(Long formPaymentId) {
		return formPaymentRepository.findById(formPaymentId)
				.orElseThrow(() -> FormPaymentNotFoundException.of(formPaymentId));
    }
}
