package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.OrderingNotFoundException;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final OrderingRepository orderingRepository;

    public Ordering findById(Long orderingId) {
        return orderingRepository.findById(orderingId).orElseThrow(() ->
                OrderingNotFoundException.of(orderingId));
    }
}
