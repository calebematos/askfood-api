package com.calebematos.askfood.infrastructure.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.calebematos.askfood.domain.model.Restaurant;

public class RestaurantSpecifications {

	public static Specification<Restaurant> withFreeShippingFee() {
		return (root, query, builder) -> 
			builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
	}

	public static Specification<Restaurant> withNameLike(String name) {
		return (root, query, builder) -> 
			builder.like(root.get("name"), "%" + name + "%");
	}

}
