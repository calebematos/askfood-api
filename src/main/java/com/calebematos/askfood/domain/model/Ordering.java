package com.calebematos.askfood.domain.model;

import com.calebematos.askfood.domain.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String code;

    private BigDecimal subtotal;

    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_payment_id", nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(name = "client_user_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Embedded
    private Address deliveryAddress;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime cancellationDate;

    private OffsetDateTime deliveryDate;

    public void calculateTotalValue() {
        getItems().forEach(OrderItem::calculateTotalPrice);

        this.subtotal = getItems().stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = this.subtotal.add(this.shippingFee);
    }

    public void setShipping() {
        setShippingFee(getRestaurant().getShippingFee());
    }

    public void assignOrderToItems() {
        getItems().forEach(item -> item.setOrdering(this));
    }

    public void confirm() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELED);
        setCancellationDate(OffsetDateTime.now());
    }

    public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());
    }

    private void setStatus(OrderStatus newStatus) {
        if (getStatus().cannotChangeTo(newStatus)) {
            throw BusinessException.of(format("Order status %s can not be changed from %s to %s",
                    getCode(), getStatus().getDescription(), newStatus.getDescription()));
        }
        this.status = newStatus;
    }

    @PrePersist
    private void generateCode(){
        setCode(UUID.randomUUID().toString());
    }

}
