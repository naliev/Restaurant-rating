package com.naliev.restaurantrating.controller;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class RestaurantController {
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant r, int userId) {
        Assert.notNull(r, "restaurant must not be null");
        return repository.save(r, userId);
    }

    public Restaurant update(Restaurant r, int userId) {
        Assert.notNull(r, "restaurant must not be null");
        return checkNotFoundWithId(repository.update(r, userId), r.getId());
    }

    public Restaurant get(int id) {
        return repository.get(id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
