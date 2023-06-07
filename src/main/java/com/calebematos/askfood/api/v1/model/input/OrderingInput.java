package com.calebematos.askfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderingInput {

    @NotNull
    @Valid
    private RestaurantIdInput restaurant;

    @NotNull
    @Valid
    private FormPaymentIdInput formPayment;

    @NotNull
    @Valid
    private AddressInput deliveryAddress;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<OrderItemInput> items;

}
