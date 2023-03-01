package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.domain.service.OrderingFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderings/{orderingCode}")
@RequiredArgsConstructor
public class OrderingFlowController {

    private final OrderingFlowService orderingFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmation(@PathVariable String orderingCode) {
        orderingFlowService.confirm(orderingCode);
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellation(@PathVariable String orderingCode) {
        orderingFlowService.cancel(orderingCode);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String orderingCode) {
        orderingFlowService.deliver(orderingCode);
    }


}
