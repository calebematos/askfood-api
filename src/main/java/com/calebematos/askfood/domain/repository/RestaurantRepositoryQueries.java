package com.calebematos.askfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.calebematos.askfood.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {
	
	List<Restaurant> find(String name, BigDecimal initialFhippingFee, BigDecimal finalShippingFee);

}
