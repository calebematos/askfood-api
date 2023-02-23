package com.calebematos.askfood.domain.repository;

import com.calebematos.askfood.domain.model.Product;
import com.calebematos.askfood.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CustomJpaRepository<Product, Long>{

    List<Product> findByRestaurant(Restaurant restaurant);

    Optional<Product> findByIdAndRestaurant(Long productId, Restaurant restaurant);
}
