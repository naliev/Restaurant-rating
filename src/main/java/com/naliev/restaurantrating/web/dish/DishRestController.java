package com.naliev.restaurantrating.web.dish;

import com.naliev.restaurantrating.model.Dish;
import com.naliev.restaurantrating.repository.DishRepository;
import com.naliev.restaurantrating.web.restaurant.RestaurantRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final DishRepository repository;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int dishId, int restaurantId) {
        log.info("get dish {} for restaurant {}", dishId, restaurantId);
        Optional<Dish> dish = repository.get(dishId, restaurantId);
        return dish.orElseThrow(() -> new IllegalArgumentException("Dish with id: " + dishId + " in a restaurant " + restaurantId + "not found"));
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        return repository.getAll(restaurantId);
    }
}
