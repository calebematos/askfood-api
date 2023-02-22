package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.FormPaymentMapper;
import com.calebematos.askfood.api.model.FormPaymentModel;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/form-payment")
@RequiredArgsConstructor
public class RestaurantFormPaymentController {

    private final RestaurantService restaurantService;
    private final FormPaymentMapper mapper;

    @GetMapping
    public List<FormPaymentModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return mapper.toCollectionModel(restaurant.getFormsPayment());
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.disassociateFormPayment(restaurantId, formPaymentId);
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.associateFormPayment(restaurantId, formPaymentId);
    }

}
