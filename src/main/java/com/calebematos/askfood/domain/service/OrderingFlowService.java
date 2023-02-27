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
    public void confirm(Long orderingId) {
        Ordering ordering = orderingService.findById(orderingId);
        ordering.confirm();
    }

    @Transactional
    public void cancel(Long orderingId) {
        Ordering ordering = orderingService.findById(orderingId);
        ordering.cancel();
    }

    @Transactional
    public void deliver(Long orderingId) {
        Ordering ordering = orderingService.findById(orderingId);
        ordering.deliver();
    }
}
