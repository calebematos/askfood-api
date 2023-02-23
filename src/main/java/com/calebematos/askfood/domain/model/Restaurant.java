package com.calebematos.askfood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    private Boolean active = Boolean.TRUE;

    private Boolean open;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registrationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @ManyToMany
    @JoinTable(name = "restaurant_form_payment",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private Set<FormPayment> formsPayment = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_responsible_user",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsible = new HashSet<>();

    public void activate() {
        setActive(Boolean.TRUE);
    }

    public void inactivate() {
        setActive(Boolean.FALSE);
    }

    public void open() {
        setOpen(Boolean.TRUE);
    }

    public void close() {
        setOpen(Boolean.FALSE);
    }

    public boolean removeFormPayment(FormPayment formPayment) {
        return getFormsPayment().remove(formPayment);
    }

    public boolean addFormPayment(FormPayment formPayment) {
        return getFormsPayment().add(formPayment);
    }

    public boolean removeResponsible(User user) {
        return getResponsible().remove(user);
    }

    public boolean addResponsible(User user) {
        return getResponsible().add(user);
    }

}
