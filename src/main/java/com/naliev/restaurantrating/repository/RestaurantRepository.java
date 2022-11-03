package com.naliev.restaurantrating.repository;

import com.naliev.restaurantrating.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant r, int userId);

    Restaurant update(Restaurant r, int userId);

    Restaurant get(int id);

    List<Restaurant> getAll();

    boolean delete(int id);
}
