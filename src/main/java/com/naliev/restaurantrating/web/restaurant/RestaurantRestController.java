package com.naliev.restaurantrating.web.restaurant;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.repository.RestaurantRepository;
import com.naliev.restaurantrating.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.*;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantRepository repository;

    public RestaurantRestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant r) {
        int userId = SecurityUtil.authUserId();
        checkNew(r);
        log.info("create restaurant {} by user {}", r, userId);
        return repository.save(r, userId);
    }

    public void update(Restaurant r) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(r, userId);
        log.info("update restaurant {} by user {}", r, userId);
        repository.update(r, userId);
    }

    public Restaurant get(int id) {
        log.info("get restaurant by id {} for user {}", id, SecurityUtil.authUserId());
        return repository.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants for user {}", SecurityUtil.authUserId());
        return repository.getAll();
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        repository.delete(id, userId);
    }
}
