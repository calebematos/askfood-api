package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.RestaurantNotFoundException;
import com.calebematos.askfood.domain.model.City;
import com.calebematos.askfood.domain.model.Cuisine;
import com.calebematos.askfood.domain.model.FormPayment;
import com.calebematos.askfood.domain.model.Restaurant;
import com.calebematos.askfood.domain.model.User;
import com.calebematos.askfood.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;
    private final FormPaymentService formPaymentService;
    private final CityService cityService;
    private final UserService userService;


    @Transactional
    public Restaurant save(Restaurant restaurant) {

        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineService.findById(cuisineId);
        City city = cityService.findById(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);


        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> RestaurantNotFoundException.of(restaurantId));
    }

    @Transactional
    public void activate(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.activate();
    }

    @Transactional
    public void inactivate(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.inactivate();
    }

    @Transactional
    public void disassociateFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = findById(restaurantId);
        FormPayment formPayment = formPaymentService.findById(formPaymentId);

        restaurant.removeFormPayment(formPayment);
    }

    @Transactional
    public void associateFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = findById(restaurantId);
        FormPayment formPayment = formPaymentService.findById(formPaymentId);

        restaurant.addFormPayment(formPayment);
    }

    @Transactional
    public void close(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.close();
    }

    @Transactional
    public void open(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.open();
    }

    @Transactional
    public void disassociateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        User user = userService.findById(userId);

        restaurant.removeResponsible(user);
    }

    @Transactional
    public void associateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        User user = userService.findById(userId);

        restaurant.addResponsible(user);
    }
}
