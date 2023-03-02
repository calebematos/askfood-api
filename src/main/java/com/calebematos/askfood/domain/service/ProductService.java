package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.ProductNotFoundException;
import com.calebematos.askfood.domain.model.Product;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantService restaurantService;


    public List<Product> findAllProductsByRestaurant(Long restaurantId, Boolean addInactive) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        List<Product> productList = null;
        if (addInactive) {
            productList = productRepository.findByRestaurant(restaurant);
        } else {
            productList = productRepository.findActiveByRestaurant(restaurant);
        }
        return productList;
    }

    public Product findById(Long restaurantId, Long productId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        return productRepository.findByIdAndRestaurant(productId, restaurant)
                .orElseThrow(() -> ProductNotFoundException.of(productId, restaurantId));
    }

    public Product newProduct(Product product, Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        product.setRestaurant(restaurant);
        return save(product);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
