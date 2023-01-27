package com.calebematos.askfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
