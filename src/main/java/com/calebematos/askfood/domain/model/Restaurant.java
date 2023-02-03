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
import java.util.List;

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

	private Boolean active;

	private Boolean open;

	@JsonIgnore
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
	private List<FormPayment> formsPayment = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();

}
