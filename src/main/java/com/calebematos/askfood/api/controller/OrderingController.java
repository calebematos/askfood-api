package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.api.mapper.OrderingMapper;
import com.calebematos.askfood.api.model.OrderingModel;
import com.calebematos.askfood.api.model.OrderingResumedModel;
import com.calebematos.askfood.api.model.input.OrderingInput;
import com.calebematos.askfood.core.data.PageableTranslator;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import com.calebematos.askfood.domain.repository.filter.OrderingFilter;
import com.calebematos.askfood.domain.service.OrderingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import java.util.Map;

import static com.calebematos.askfood.infrastructure.repository.specification.OrderingSpecifications.usingFilter;

@RestController
@RequestMapping("/orderings")
@RequiredArgsConstructor
public class OrderingController {

    private final OrderingRepository orderingRepository;
    private final OrderingService orderingService;
    private final OrderingMapper mapper;

    @GetMapping
    public Page<OrderingResumedModel> search(OrderingFilter filter, Pageable pageable) {
        pageable = translatePageable(pageable);
        Page<Ordering> orderingPage = orderingRepository.findAll(usingFilter(filter), pageable);

        List<OrderingResumedModel> orderingResumedModels = mapper.toCollectionResumedModel(orderingPage.getContent());
        return new PageImpl<>(orderingResumedModels, pageable, orderingPage.getTotalElements());
    }

    @GetMapping("/{orderingCode}")
    public OrderingModel find(@PathVariable String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        return mapper.toModel(ordering);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderingModel add(@RequestBody @Valid OrderingInput orderingInput) {
        Ordering ordering = mapper.toDomainObject(orderingInput);
        return mapper.toModel(orderingService.save(ordering));
    }

    private Pageable translatePageable(Pageable pageable) {
        var mapping = Map.of("code", "code",
                "restaurant.name", "restaurant.name",
                "client.name", "client.name",
                "totalValue", "totalValue",
                "subtotal", "subtotal",
                "shippingFee", "shippingFee",
                "registrationDate", "registrationDate",
                "status", "status"
        );
        return PageableTranslator.translate(pageable, mapping);
    }

}
