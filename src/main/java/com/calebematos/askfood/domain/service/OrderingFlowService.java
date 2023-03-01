package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.model.Ordering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderingFlowService {

    private final OrderingService orderingService;

    @Transactional
    public void confirm(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.confirm();
    }

    @Transactional
    public void cancel(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.cancel();
    }

    @Transactional
    public void deliver(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.deliver();
    }
}
