package com.naliev.restaurantrating.web.restaurant;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.assureIdConsistent;
import static com.naliev.restaurantrating.util.ValidationUtil.checkNew;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantRepository repository;

    public RestaurantRestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant r) {
        log.info("create {} ", r);
        checkNew(r);
        return repository.save(r);
    }

    public void update(Restaurant r, int id) {
        log.info("update {} ", r);
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant with id: " + id + "not found"));
        assureIdConsistent(r, id);
        repository.save(r);
    }

    public Restaurant get(int id) {
        log.info("get by id {}", id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant with id: " + id + "not found"));
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public void delete(int id) {
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant with id: " + id + "not found"));
        repository.deleteById(id);
    }
}
