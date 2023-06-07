package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.FormPaymentModel;

import java.util.List;

public interface RestaurantFormPaymentControllerOpenApi {

    List<FormPaymentModel> list(Long restaurantId);


    void disassociateFormPayment(Long restaurantId, Long formPaymentId);


    void associateFormPayment( Long restaurantId, Long formPaymentId);
}
