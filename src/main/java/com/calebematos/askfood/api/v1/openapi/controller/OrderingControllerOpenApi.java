package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.api.v1.model.OrderingModel;
import com.calebematos.askfood.api.v1.model.OrderingResumedModel;
import com.calebematos.askfood.api.v1.model.input.OrderingInput;
import com.calebematos.askfood.domain.filter.OrderingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderingControllerOpenApi {

    Page<OrderingResumedModel> search(OrderingFilter filter, Pageable pageable);

    OrderingModel find(String orderingCode);

    OrderingModel add(OrderingInput orderingInput);

    Pageable translatePageable(Pageable pageable);
}
