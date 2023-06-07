package com.calebematos.askfood.api.v1.model;

import com.calebematos.askfood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderingResumedModel {

    private String code;

    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;

    private OffsetDateTime registrationDate;

    private RestaurantResumedModel restaurant;
    private UserModel client;
    private OrderStatus status;

}
