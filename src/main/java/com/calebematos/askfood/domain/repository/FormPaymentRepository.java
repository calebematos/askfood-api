package com.calebematos.askfood.domain.repository;

import com.calebematos.askfood.domain.model.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long>{

}
