package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.FormPaymentMapper;
import com.calebematos.askfood.api.model.FormPaymentModel;
import com.calebematos.askfood.api.model.input.FormPaymentInput;
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
@RequestMapping("/formPayments")
@RequiredArgsConstructor
public class FormPaymentController {

    private final FormPaymentRepository formPaymentRepository;
    private final FormPaymentService formPaymentService;
    private final FormPaymentMapper mapper;

    @GetMapping
    public ResponseEntity<List<FormPaymentModel>> list() {
        List<FormPayment> formPayments = formPaymentRepository.findAll();
        List<FormPaymentModel> formPaymentList = mapper.toCollectionModel(formPayments);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(formPaymentList);
    }

    @GetMapping("/{formPaymentId}")
    public ResponseEntity<FormPaymentModel> find(@PathVariable Long formPaymentId) {
        FormPayment formPayment = formPaymentService.findById(formPaymentId);
        FormPaymentModel formPaymentModel = mapper.toModel(formPayment);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(formPaymentModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormPaymentModel add(@RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment formPayment = mapper.toDomainObject(formPaymentInput);
        return mapper.toModel(formPaymentService.save(formPayment));
    }

    @PutMapping("/{formPaymentId}")
    public FormPaymentModel update(@PathVariable Long formPaymentId, @RequestBody @Valid FormPaymentInput formPaymentInput) {
        FormPayment currentFormPayment = formPaymentService.findById(formPaymentId);

        mapper.copyToDomainObject(formPaymentInput, currentFormPayment);

        return mapper.toModel(formPaymentService.save(currentFormPayment));
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formPaymentId) {
        formPaymentService.delete(formPaymentId);
    }
}
