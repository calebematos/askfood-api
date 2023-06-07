package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.FormPaymentMapper;
import com.calebematos.askfood.api.v1.model.FormPaymentModel;
import com.calebematos.askfood.api.v1.model.input.FormPaymentInput;
import com.calebematos.askfood.api.v1.openapi.controller.FormPaymentControllerOpenApi;
import com.calebematos.askfood.domain.model.FormPayment;
import com.calebematos.askfood.domain.repository.FormPaymentRepository;
import com.calebematos.askfood.domain.service.FormPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
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

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/formPayments")
@RequiredArgsConstructor
public class FormPaymentController implements FormPaymentControllerOpenApi {

    private final FormPaymentRepository formPaymentRepository;
    private final FormPaymentService formPaymentService;
    private final FormPaymentMapper mapper;

    @Override
    @GetMapping
    public ResponseEntity<List<FormPaymentModel>> list() {
        List<FormPayment> formPayments = formPaymentRepository.findAll();
        List<FormPaymentModel> formPaymentList = mapper.toCollectionModel(formPayments);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(formPaymentList);
    }

    @Override
    @GetMapping("/{formPaymentId}")
    public ResponseEntity<FormPaymentModel> find(@PathVariable Long formPaymentId) {
        FormPayment formPayment = formPaymentService.findById(formPaymentId);
        FormPaymentModel formPaymentModel = mapper.toModel(formPayment);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(formPaymentModel);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormPaymentModel add(@RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment formPayment = mapper.toDomainObject(formPaymentInput);
        return mapper.toModel(formPaymentService.save(formPayment));
    }

    @Override
    @PutMapping("/{formPaymentId}")
    public FormPaymentModel update(@PathVariable Long formPaymentId, @RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment currentFormPayment = formPaymentService.findById(formPaymentId);

        mapper.copyToDomainObject(formPaymentInput, currentFormPayment);

        return mapper.toModel(formPaymentService.save(currentFormPayment));
    }

    @Override
    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formPaymentId) {
        formPaymentService.delete(formPaymentId);
    }
}
