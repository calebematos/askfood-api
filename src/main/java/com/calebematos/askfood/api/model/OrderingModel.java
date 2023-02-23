package com.calebematos.askfood.api.model;

import com.calebematos.askfood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderingModel {

    private Long id;

    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalValue;

    private OffsetDateTime registrationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    private RestaurantResumedModel restaurant;
    private FormPaymentModel formPayment;
    private UserModel client;
    private OrderStatus status;
    private List<OrderItemModel> items;

}
