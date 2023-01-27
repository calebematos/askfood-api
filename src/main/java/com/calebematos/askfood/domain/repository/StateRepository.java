package com.calebematos.askfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calebematos.askfood.domain.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
