package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderingFlowService {

    private final OrderingService orderingService;
    private final OrderingRepository orderingRepository;

    @Transactional
    public void confirm(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.confirm();
        orderingRepository.save(ordering);

    }

    @Transactional
    public void cancel(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.cancel();
        orderingRepository.save(ordering);
    }

    @Transactional
    public void deliver(String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        ordering.deliver();
    }
}
