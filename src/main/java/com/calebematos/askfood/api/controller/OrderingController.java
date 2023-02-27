package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.OrderingMapper;
import com.calebematos.askfood.api.model.OrderingModel;
import com.calebematos.askfood.api.model.OrderingResumedModel;
import com.calebematos.askfood.api.model.input.OrderingInput;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import com.calebematos.askfood.domain.service.OrderingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderings")
@RequiredArgsConstructor
public class OrderingController {

    private final OrderingRepository orderingRepository;
    private final OrderingService orderingService;
    private final OrderingMapper mapper;

    @GetMapping
    public List<OrderingResumedModel> list() {
        List<Ordering> orderings = orderingRepository.findAll();
        return mapper.toCollectionResumedModel(orderings);
    }

    @GetMapping("/{orderingId}")
    public OrderingModel find(@PathVariable Long orderingId) {
        Ordering ordering = orderingService.findById(orderingId);
        return mapper.toModel(ordering);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderingModel add(@RequestBody @Valid OrderingInput orderingInput){
        Ordering ordering = mapper.toDomainObject(orderingInput);
        return mapper.toModel(orderingService.save(ordering));
    }
}
