package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.FormPaymentModel;
import com.calebematos.askfood.api.v1.model.input.FormPaymentInput;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormPaymentControllerOpenApi {

    ResponseEntity<List<FormPaymentModel>> list();

    ResponseEntity<FormPaymentModel> find(Long formPaymentId);

    FormPaymentModel add(FormPaymentInput formPaymentInput);

    FormPaymentModel update(Long formPaymentId, FormPaymentInput formPaymentInput);

    void delete(Long formPaymentId);
}
