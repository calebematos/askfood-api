package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.OrderingMapper;
import com.calebematos.askfood.api.model.OrderingModel;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import com.calebematos.askfood.domain.service.OrderingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderings")
@RequiredArgsConstructor
public class OrderingController {

    private final OrderingRepository orderingRepository;
    private final OrderingService orderingService;
    private final OrderingMapper mapper;

    @GetMapping
    public List<OrderingModel> list() {
        List<Ordering> orderings = orderingRepository.findAll();
        return mapper.toCollectionModel(orderings);
    }

    @GetMapping("/{orderingId}")
    public OrderingModel find(@PathVariable Long orderingId) {
        Ordering ordering = orderingService.findById(orderingId);
        return mapper.toModel(ordering);
    }
}
