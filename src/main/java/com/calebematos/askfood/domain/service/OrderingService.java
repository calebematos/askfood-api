package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.BusinessException;
import com.calebematos.askfood.domain.exception.OrderingNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.model.FormPayment;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.model.Product;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.repository.OrderingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final OrderingRepository orderingRepository;
    private final RestaurantService restaurantService;
    private final FormPaymentService formPaymentService;
    private final UserService userService;
    private final CityService cityService;
    private final ProductService productService;


    public Ordering findById(String orderingCode) {
        return orderingRepository.findByCode(orderingCode).orElseThrow(() ->
                OrderingNotFoundException.of(orderingCode));
    }

    @Transactional
    public Ordering save(Ordering ordering) {

        validateOrdering(ordering);
        validateItems(ordering);

        ordering.setShipping();
        ordering.calculateTotalValue();

        // TODO: Change when implement security
        User user = userService.findById(1L);
        ordering.setClient(user);

        return orderingRepository.save(ordering);
    }

    private void validateItems(Ordering ordering) {
        ordering.getItems().forEach(item -> {
            Product product = productService.findById(ordering.getRestaurant().getId(), item.getProduct().getId());

            item.setOrdering(ordering);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }

    private void validateOrdering(Ordering ordering) {
        Long cityId = ordering.getDeliveryAddress().getCity().getId();
        City city = cityService.findById(cityId);

        Long restaurantId = ordering.getRestaurant().getId();
        Restaurant restaurant = restaurantService.findById(restaurantId);

        Long formPaymentId = ordering.getFormPayment().getId();
        FormPayment formPayment = formPaymentService.findById(formPaymentId);

        if (restaurant.doesNotAcceptFormPayment(formPayment)) {
            throw BusinessException.of(String.format("Form payment '%s' is not accepted by this restaurant.",
                    formPayment.getDescription()));
        }

        ordering.setRestaurant(restaurant);
        ordering.setFormPayment(formPayment);
        ordering.getDeliveryAddress().setCity(city);
    }

}
