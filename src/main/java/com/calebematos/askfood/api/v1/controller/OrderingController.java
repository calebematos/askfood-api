package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.OrderingMapper;
import com.calebematos.askfood.api.v1.model.OrderingModel;
import com.calebematos.askfood.api.v1.model.OrderingResumedModel;
import com.calebematos.askfood.api.v1.model.input.OrderingInput;
import com.calebematos.askfood.api.v1.openapi.controller.OrderingControllerOpenApi;
import com.calebematos.askfood.core.data.PageableTranslator;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import com.calebematos.askfood.domain.filter.OrderingFilter;
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
@RequestMapping("/v1/orderings")
@RequiredArgsConstructor
public class OrderingController implements OrderingControllerOpenApi {

    private final OrderingRepository orderingRepository;
    private final OrderingService orderingService;
    private final OrderingMapper mapper;

    @Override
    @GetMapping
    public Page<OrderingResumedModel> search(OrderingFilter filter, Pageable pageable) {
        pageable = translatePageable(pageable);
        Page<Ordering> orderingPage = orderingRepository.findAll(usingFilter(filter), pageable);

        List<OrderingResumedModel> orderingResumedModels = mapper.toCollectionResumedModel(orderingPage.getContent());
        return new PageImpl<>(orderingResumedModels, pageable, orderingPage.getTotalElements());
    }

    @Override
    @GetMapping("/{orderingCode}")
    public OrderingModel find(@PathVariable String orderingCode) {
        Ordering ordering = orderingService.findById(orderingCode);
        return mapper.toModel(ordering);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderingModel add(@RequestBody @Valid OrderingInput orderingInput) {
        Ordering ordering = mapper.toDomainObject(orderingInput);
        return mapper.toModel(orderingService.save(ordering));
    }

    @Override
    public Pageable translatePageable(Pageable pageable) {
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
