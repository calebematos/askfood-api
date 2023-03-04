package com.calebematos.askfood.domain.repository;

import com.calebematos.askfood.domain.model.Ordering;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderingRepository extends CustomJpaRepository<Ordering, Long>, JpaSpecificationExecutor<Ordering> {

    Optional<Ordering> findByCode(String code);

    @Query("from Ordering o join fetch o.client join fetch o.restaurant r join fetch r.cuisine")
    List<Ordering> findAll();
}
