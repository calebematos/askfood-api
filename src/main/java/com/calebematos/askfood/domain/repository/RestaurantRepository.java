package com.calebematos.askfood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository
		extends CustomJpaRepository<Restaurant, Long>, 
			RestaurantRepositoryQueries, 
			JpaSpecificationExecutor<Restaurant> {

	@Query("select distinct r from Restaurant r join r.cuisine left join fetch r.formsPayment")
	List<Restaurant> findAll();


}
