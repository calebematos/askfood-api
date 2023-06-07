package com.calebematos.askfood.api.v1.openapi.controller;

public interface OrderingFlowControllerOpenApi {

    void confirmation(String orderingCode);

    void cancellation(String orderingCode);

    void delivery(String orderingCode);
}
