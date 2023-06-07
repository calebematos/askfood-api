package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.FormPaymentMapper;
import com.calebematos.askfood.api.v1.model.FormPaymentModel;
import com.calebematos.askfood.api.v1.openapi.controller.RestaurantFormPaymentControllerOpenApi;
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
@RequestMapping("/v1/restaurants/{restaurantId}/form-payment")
@RequiredArgsConstructor
public class RestaurantFormPaymentController implements RestaurantFormPaymentControllerOpenApi {

    private final RestaurantService restaurantService;
    private final FormPaymentMapper mapper;

    @Override
    @GetMapping
    public List<FormPaymentModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return mapper.toCollectionModel(restaurant.getFormsPayment());
    }

    @Override
    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.disassociateFormPayment(restaurantId, formPaymentId);
    }

    @Override
    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateFormPayment(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantService.associateFormPayment(restaurantId, formPaymentId);
    }

}
