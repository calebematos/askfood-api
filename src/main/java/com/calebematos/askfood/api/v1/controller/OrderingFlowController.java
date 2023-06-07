package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.openapi.controller.OrderingFlowControllerOpenApi;
import com.calebematos.askfood.domain.service.OrderingFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orderings/{orderingCode}")
@RequiredArgsConstructor
public class OrderingFlowController implements OrderingFlowControllerOpenApi {

    private final OrderingFlowService orderingFlowService;

    @Override
    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmation(@PathVariable String orderingCode) {
        orderingFlowService.confirm(orderingCode);
    }

    @Override
    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellation(@PathVariable String orderingCode) {
        orderingFlowService.cancel(orderingCode);
    }

    @Override
    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String orderingCode) {
        orderingFlowService.deliver(orderingCode);
    }


}
