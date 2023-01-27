package com.calebematos.askfood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository
		extends CustomJpaRepository<Restaurant, Long>, 
			RestaurantRepositoryQueries, 
			JpaSpecificationExecutor<Restaurant> {

}
