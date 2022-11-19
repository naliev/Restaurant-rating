package com.naliev.restaurantrating.web.dish;

import com.naliev.restaurantrating.repository.DishRepository;
import com.naliev.restaurantrating.web.restaurant.RestaurantRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final DishRepository repository;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }
}
