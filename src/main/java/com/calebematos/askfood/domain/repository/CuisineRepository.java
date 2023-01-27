package com.calebematos.askfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.Cuisine;

@Repository
public interface CuisineRepository extends CustomJpaRepository<Cuisine, Long>{

	List<Cuisine> findByNameContaining(String name);
	
}
