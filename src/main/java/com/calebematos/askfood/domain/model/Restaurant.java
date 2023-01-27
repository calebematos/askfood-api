package com.calebematos.askfood.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
	
	@ManyToOne
	@JoinColumn(name = "cuisine_id")
	private Cuisine cuisine;

	@ManyToMany
	@JoinTable(name = "restaurant_form_payment",
				joinColumns = @JoinColumn(name = "restaurant_id"),
				inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
	private List<FormPayment> formsPayment = new ArrayList<>();
	private Boolean active;
	private Boolean open;

}
