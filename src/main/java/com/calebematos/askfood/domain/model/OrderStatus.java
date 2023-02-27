package com.calebematos.askfood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELED("Canceled", CREATED);

    private String description;
    private List<OrderStatus> previousStatuses;

    OrderStatus(String description, OrderStatus... previousStatuses) {
        this.description = description;
        this.previousStatuses = Arrays.asList(previousStatuses);
    }

    public String getDescription() {
        return description;
    }

    public boolean cannotChangeTo(OrderStatus newStatus){
        return !newStatus.previousStatuses.contains(this);
    }

}
